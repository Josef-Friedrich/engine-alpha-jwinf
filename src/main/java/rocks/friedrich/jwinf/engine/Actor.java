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

interface MovementListener {
  /**
   * @param x         Die aktuelle x-Position der Figur im Kachel-Gitter.
   * @param y         Die aktuelle y-Position der Figur im Kachel-Gitter.
   * @param direction Die Richtung, in der sich die Figur bewegen will.
   *
   * @return Wahr, wenn sich die Figur bewegen darf, sonst falsch.
   */
  boolean allowMovement(int x, int y, Direction direction);
}

public class Actor extends Image {

  private List<MovementListener> movementListeners = new ArrayList<>();

  /**
   * Damit keine neue Bewegung gestartet werden kann, bevor nicht die alte
   * fertig abgelaufen ist.
   */
  private boolean inMotion = false;

  protected float speed = 1;

  public Actor(String filepath) {
    super(filepath, 1, 1);

    // grid edges
    addMovementListener((int x, int y, Direction direction) -> {
      switch (direction) {
        case RIGHT:
          return x < State.map.width - 1;

        case UP:
          return y < State.map.height - 1;

        case LEFT:
          return x > 0;

        case DOWN:
          return y > 0;

        default:
          return true;
      }
    });

    // Obstacles
    addMovementListener((int x, int y, Direction direction) -> {
      int xMovement = 0;
      int yMovement = 0;

      switch (direction) {
        case RIGHT:
          xMovement = 1;
          break;

        case UP:
          yMovement = 1;
          break;

        case LEFT:
          xMovement = -1;
          break;

        case DOWN:
          yMovement = -1;
          break;

        default:
      }

      return !State.map.isObstacle(x + xMovement, y + yMovement);
    });
  }

  public void addMovementListener(MovementListener listener) {
    movementListeners.add(listener);
  }

  /**
   * Gib den Buchstaben der Kachel zurück, auf dem sich das Objekt gerade
   * befindet.
   */
  public char getTile() {
    return State.map.getLetter(getGridX(), getGridY());
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
    setCenter(getGridX(), getGridY());
    inMotion = false;
  }

  public boolean canMove(Direction direction) {
    for (MovementListener listener : this.movementListeners) {
      if (!listener.allowMovement(getGridX(), getGridY(), direction)) {
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

  /**
   * Der gerundete x-Wert der Koordinate, an der sich die Figur im
   * Kachelgitter befindet.
   */
  public int getGridX() {
    return Math.round(getCenter().getX());
  }

  /**
   * Der gerundete y-Wert der Koordinate, an der sich die Figur im
   * Kachelgitter befindet.
   */
  public int getGridY() {
    return Math.round(getCenter().getY());
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

}
