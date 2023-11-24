package rocks.friedrich.jwinf.platform.gui.robot;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

import ea.Vector;
import ea.actor.Image;
import ea.animation.Interpolator;
import ea.animation.ValueAnimator;
import ea.animation.interpolation.SinusFloat;
import rocks.friedrich.jwinf.platform.State;
import rocks.friedrich.jwinf.platform.data.model.ItemData;
import rocks.friedrich.jwinf.platform.logic.Compass;
import rocks.friedrich.jwinf.platform.logic.map.Movement;
import rocks.friedrich.jwinf.platform.logic.map.Point;
import rocks.friedrich.jwinf.platform.logic.robot.Robot;
import rocks.friedrich.jwinf.platform.logic.robot.VirtualRobot;

public class ImageRobot extends Image implements Robot {

  private VirtualRobot virtual;

  /**
   * Damit keine neue Bewegung gestartet werden kann, bevor nicht die alte
   * fertig abgelaufen ist.
   */
  private boolean inMotion = false;

  // private LevelMap map;

  protected float speed = 1;

  /**
   * Behälter in dem Objekte eingesammelt (withdraw) werden können.
   *
   * https://github.com/France-ioi/bebras-modules/blob/ec1baf055c7f1c383ce8dfa5d27998463ef5be59/pemFioi/blocklyRobot_lib-1.1.js#L2458-L2478
   */
  public List<ItemData> bag = new ArrayList<>();

  public ImageRobot(String filepath, VirtualRobot virtual) {
    super(filepath, 1, 1);
    this.virtual = virtual;
  }

  public Point getPoint() {
    return virtual.getPoint();
  }

  public boolean isOnExit() {
    var tile = virtual.map.get(row(), col());
    return tile != null && tile.isExit;
  }

  public boolean obstacleInFront() {
    return virtual.obstacleInFront();
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
    inMotion = false;
  }

  public boolean canMoveAnimated(Compass direction) {
    boolean result = virtual.tryToBeOn(direction);
    if (!result) {
      wiggle();
    }
    return result;
  }

  /**
   * Gehe einen Pixelmeter in Richtung der aktuellen Rotation.
   */
  public Movement forward() {
    var movement = virtual.forward();
    go(virtual.dir);
    return movement;
  }

  public void go(Compass direction) {
    float degree = 90;

    switch (direction) {
      case EAST:
        degree = 0;
        break;

      case NORTH:
        degree = 90;
        break;

      case WEST:
        degree = 180;
        break;

      case SOUTH:
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

  public Movement east() {
    var movement = virtual.east();
    go(movement.dir);
    return movement;
  }

  public void eastNonBlocking() {
    new Thread(this::east).start();
  }

  public Movement south() {
    var movement = virtual.south();
    go(movement.dir);
    return movement;
  }

  public void southNonBlocking() {
    new Thread(this::south).start();
  }

  public Movement west() {
    var movement = virtual.west();
    go(movement.dir);
    return movement;
  }

  public void westNonBlocking() {
    new Thread(this::west).start();
  }

  public Movement north() {
    var movement = virtual.north();
    go(movement.dir);
    return movement;
  }

  public void northNonBlocking() {
    new Thread(this::north).start();
  }

  public Point point() {
    return virtual.map.translateToPoint(getCenter());
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
  public Movement turnLeft() {
    var movement = virtual.turnLeft();
    rotateByAnimated(90);
    return movement;
  }

  /**
   * Drehe um 90 Grad nach rechts.
   */
  public Movement turnRight() {
    var movement = virtual.turnRight();
    rotateByAnimated(-90);
    return movement;
  }

  public Movement turnAround() {
    var movement = virtual.turnAround();
    rotateByAnimated(180);
    return movement;
  }

  protected void animate(float duration, Consumer<Float> setter, Interpolator<Float> interpolator) {
    CompletableFuture<Void> future = new CompletableFuture<>();

    ValueAnimator<Float> animator = new ValueAnimator<>(duration, setter, interpolator, this);

    animator.addCompletionListener(value -> {
      setter.accept(value);
      future.complete(null);
    });

    addFrameUpdateListener(animator);

    try {
      future.get();
    } catch (InterruptedException | ExecutionException e) {
      throw new RuntimeException(e);
    }
  }

  protected void animate(float duration, Consumer<Float> setter) {
    animate(duration, setter, State.interpolator);
  }

  public void placeInMap(int x, int y) {
    if (y > 0) {
      y = y * -1;
    }
    setCenter(x, y);
  }
}
