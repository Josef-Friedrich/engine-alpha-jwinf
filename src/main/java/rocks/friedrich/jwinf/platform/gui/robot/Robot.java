package rocks.friedrich.jwinf.platform.gui.robot;

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
import rocks.friedrich.jwinf.platform.State;
import rocks.friedrich.jwinf.platform.data.model.TileData;
import rocks.friedrich.jwinf.platform.logic.level.LevelMap;
import rocks.friedrich.jwinf.platform.logic.map.Point;

public class Robot extends Image {

  private List<MovementListener> movementListeners = new ArrayList<>();

  /**
   * Damit keine neue Bewegung gestartet werden kann, bevor nicht die alte
   * fertig abgelaufen ist.
   */
  private boolean inMotion = false;

  private LevelMap map;

  protected float speed = 1;

  /**
   * Behälter in dem Objekte eingesammelt (withdraw) werden können.
   *
   * https://github.com/France-ioi/bebras-modules/blob/ec1baf055c7f1c383ce8dfa5d27998463ef5be59/pemFioi/blocklyRobot_lib-1.1.js#L2458-L2478
   */
  public List<TileData> bag = new ArrayList<>();

  public Robot(String filepath, LevelMap map) {
    super(filepath, 1, 1);
    this.map = map;
  }

  public void addMovementListener(MovementListener listener) {
    movementListeners.add(listener);
  }

  public void addGridEdgesMovementListener() {
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
  }

  protected boolean isInFrontOfObstacle(int row, int col, Direction direction) {
    int rowMovement = 0;
    int colMovement = 0;

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

    return map.isObstacle(row + rowMovement, col + colMovement);
  }

  public boolean isInFrontOfObstacle() {
    return isInFrontOfObstacle(row(), col(), getDirection());
  }

  public void addObstaclesMovementListener() {
    addMovementListener((int row, int col, Direction direction) -> {
      return !isInFrontOfObstacle(row, col, direction);
    });
  }

  public boolean isOnExit() {
    var tile = map.get(row(), col());
    return tile != null && tile.isExit;
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

  protected void go(double meter) {
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
    // setCenter(Math.round(getX()), Math.round(getY()));
    inMotion = false;
  }

  public boolean canMove(Direction direction) {
    for (MovementListener listener : this.movementListeners) {
      if (!listener.allowMovement(row(), col(), direction)) {
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

  public Direction getDirection() {
    int rotation = (int) getRotation();
    if (rotation == 0) {
      return Direction.RIGHT;
    } else if (rotation == 90) {
      return Direction.UP;
    } else if (rotation == 180) {
      return Direction.LEFT;
    } else {
      return Direction.DOWN;
    }
  }

  /**
   * Gehe einen Pixelmeter in Richtung der aktuellen Rotation.
   */
  public void go() {
    go(getDirection());
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

  public Point point() {
    return map.translateToPoint(getCenter());
  }

  /**
   * Die Spalte, in der sich die Figur im Kachelgitter befindet.
   */
  public int col() {
    return point().col;
  }

  /**
   * Die Reihe, in der sich die Figur im Kachelgitter befindet.
   */
  public int row() {
    return point().row;
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
   * Drehe um 90 Grad nach links.
   */
  public void rotateLeft() {
    rotateByAnimated(90);
  }

  /**
   * Drehe um 90 Grad nach rechts.
   */
  public void rotateRight() {
    rotateByAnimated(-90);
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
