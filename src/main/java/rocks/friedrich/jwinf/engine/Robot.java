package rocks.friedrich.jwinf.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

import ea.Direction;
import ea.Vector;
import ea.actor.Image;
import ea.animation.Interpolator;
import ea.animation.ValueAnimator;
import ea.animation.interpolation.SinusFloat;
import rocks.friedrich.jwinf.engine.task.LevelMap;

interface MovementListener {
  /**
   * @param row       Die aktuelle Zeile (y), in der sich die Figur im
   *                  Kachel-Gitter befindet.
   * @param col       Die aktuelle Spalte (x), in der sich die Figur im
   *                  Kachel-Gitter befindet.
   * @param direction Die Richtung, in der sich die Figur bewegen will.
   *
   * @return Wahr, wenn sich die Figur bewegen darf, sonst falsch.
   */
  boolean allowMovement(int row, int col, Direction direction);
}

public class Robot extends Image {

  private List<MovementListener> movementListeners = new ArrayList<>();

  /**
   * Damit keine neue Bewegung gestartet werden kann, bevor nicht die alte
   * fertig abgelaufen ist.
   */
  private boolean inMotion = false;

  private LevelMap map;

  protected float speed = 1;

  public Robot(String filepath, LevelMap map) {
    super(filepath, 1, 1);
    this.map = map;

    // grid edges
    addMovementListener((int row, int col, Direction direction) -> {
      switch (direction) {
        case RIGHT:
          return col < map.cols - 1;

        case UP:
          return row > 0;

        case LEFT:
          return col > 0;

        case DOWN:
          return row < map.rows - 1;

        default:
          return true;
      }
    });

    // Obstacles
    addMovementListener((int row, int col, Direction direction) -> {
      int colMovement = 0;
      int rowMovement = 0;

      switch (direction) {
        case RIGHT:
          colMovement = 1;
          break;

        case UP:
          rowMovement = -1;
          break;

        case LEFT:
          colMovement = -1;
          break;

        case DOWN:
          rowMovement = 1;
          break;

        default:
      }

      return !map.isObstacle(row + rowMovement, col + colMovement);
    });
  }

  public void addMovementListener(MovementListener listener) {
    movementListeners.add(listener);
  }

  public void setSpeed(float speed) {
    this.speed = speed;
  }

  public void wait(double seconds) {
    try {
      Thread.sleep((long) (1000 * seconds));
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  public void go(double meter) {
    if (inMotion) {
      return;
    }
    inMotion = true;
    Vector move = Vector.ofAngle(getRotation()).multiply((float) meter);
    Vector initial = getCenter();

    float duration = (float) meter / speed / 2;

    animate(duration, progress -> {
      setCenter(initial.add(move.multiply(progress)));
    });
    // Falls die animierte Navigation nicht zu einem exakten Punkt im Kachelgitter
    // führt, wird die Figur auf die nächst gelegene exakte Koordinate gezwungen.
    // Möglicherweiße sprint die Figur dann.
    setCenter(col(), -1 * row());
    inMotion = false;
  }

  public boolean canMove(Direction direction) {
    for (MovementListener listener : this.movementListeners) {
      if (!listener.allowMovement(col(), row(), direction)) {
        return false;
      }
    }
    return true;
  }

  public boolean canMoveAnimated(Direction direction) {
    boolean result = canMove(direction);
    if (!result) {
      wiggle();
    }
    return result;
  }

  public void go(Direction direction) {
    float degree = 90;

    switch (direction) {
      case RIGHT:
        degree = 0;
        break;

      case UP:
        degree = 90;
        break;

      case LEFT:
        degree = 180;
        break;

      case DOWN:
        degree = 270;
        break;

      default:
        System.out.println("Not supported direction");
    }

    if (!canMoveAnimated(direction)) {
      return;
    }

    rotateAnimated(degree);
    go(1);
  }

  public void goRight() {
    go(Direction.RIGHT);
  }

  public void goRightNonBlocking() {
    new Thread(this::goRight).start();
  }

  public void goUp() {
    go(Direction.UP);
  }

  public void goUpNonBlocking() {
    new Thread(this::goUp).start();
  }

  public void goLeft() {
    go(Direction.LEFT);
  }

  public void goLeftNonBlocking() {
    new Thread(this::goLeft).start();
  }

  public void goDown() {
    go(Direction.DOWN);
  }

  public void goDownNonBlocking() {
    new Thread(this::goDown).start();
  }

  private int translateXToCol(int x) {
    return x - map.x;
  }

  /**
   * Die Spalte, in der sich die Figur im Kachelgitter befindet.
   */
  public int col() {
    return translateXToCol(Math.round(getCenter().getX()));
  }

  private int translateYToRow(int y) {
    // y-Koordinate des linken oberen Ecks des Kachelgitters (Ursprung des Kachelgitter)
    int yMap0 = map.rows - 1 + y;
    return y - yMap0;
  }

  /**
   * Die Reihe, in der sich die Figur im Kachelgitter befindet.
   */
  public int row() {
    return translateYToRow(Math.round(getCenter().getY()));
  }

  public void wiggle() {
    this.wiggle(0.3f);
  }

  public void wiggle(float duration) {
    if (inMotion) {
      return;
    }
    inMotion = true;
    float rotation = getRotation();
    Vector center = getCenter();
    wait(0.1);
    animate(duration, progress -> {
      setRotation(rotation + progress);
      setCenter(center);
    }, new SinusFloat(0, 45));
    wait(0.1);
    inMotion = false;
  }

  public void rotateByAnimated(double degree) {
    if (inMotion) {
      return;
    }
    inMotion = true;
    Vector center = getCenter();

    // To avoid high rotation numbers
    float start = getRotation() % 360;
    setRotation(start);
    float duration = 1 / speed / 4;

    animate(duration, progress -> {
      setRotation(start + progress * (float) degree);
      setCenter(center);
    });
    inMotion = false;
  }

  public void rotateAnimated(double dest) {
    float src = getRotation();
    Vector center = getCenter();

    float diff = (float) dest - src;

    diff %= 360;

    if (diff == 0) {
      return;
    }
    // for example: dest 0 src 270
    if (diff < -180) {
      diff += 360;
    }

    if (diff > 180) {
      diff -= 360;
    }

    rotateByAnimated(diff);
    setCenter(center);
  }

  /**
   *
   * @param duration
   * @param setter
   * @param interpolator
   * @param block        Wenn wahr, dann blockiere diese Methode
   *                     solange, bis die Animation vollendet ist.
   */
  protected void animate(float duration, Consumer<Float> setter, Interpolator<Float> interpolator, boolean block,
      Runnable onCompletion) {
    CompletableFuture<Void> future = new CompletableFuture<>();

    ValueAnimator<Float> animator = new ValueAnimator<>(duration, setter, interpolator, this);

    animator.addCompletionListener(value -> {
      setter.accept(value);
      if (onCompletion != null && block) {
        onCompletion.run();
        future.complete(null);
      } else if (onCompletion == null && block) {
        future.complete(null);
      }
    });

    if (!block) {
      future.complete(null);
    }

    addFrameUpdateListener(animator);

    try {
      future.get();
    } catch (InterruptedException | ExecutionException e) {
      throw new RuntimeException(e);
    }
  }

  protected void animate(float duration, Consumer<Float> setter, Interpolator<Float> interpolator) {
    animate(duration, setter, interpolator, true, null);
  }

  protected void animate(float duration, Consumer<Float> setter, boolean block) {
    animate(duration, setter, State.interpolator, block, null);
  }

  protected void animate(float duration, Consumer<Float> setter) {
    animate(duration, setter, State.interpolator, true, null);
  }

  public void placeInMap(int x, int y) {
    if (y > 0) {
      y = y * -1;
    }
    setCenter(x, y);
  }
}
