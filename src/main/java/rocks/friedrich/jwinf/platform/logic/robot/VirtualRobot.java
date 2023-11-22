package rocks.friedrich.jwinf.platform.logic.robot;

import static rocks.friedrich.jwinf.platform.logic.Compass.EAST;
import static rocks.friedrich.jwinf.platform.logic.Compass.NORTH;
import static rocks.friedrich.jwinf.platform.logic.Compass.SOUTH;
import static rocks.friedrich.jwinf.platform.logic.Compass.WEST;

import java.util.ArrayList;
import java.util.List;

import rocks.friedrich.jwinf.platform.data.model.ItemData;
import rocks.friedrich.jwinf.platform.logic.Compass;
import rocks.friedrich.jwinf.platform.logic.level.LevelMap;
import rocks.friedrich.jwinf.platform.logic.map.DirectionalPoint;
import rocks.friedrich.jwinf.platform.logic.map.Movement;
import rocks.friedrich.jwinf.platform.logic.map.Point;

/**
 * Ein Roboter der nicht grafisch dargestellt ist, sondern der sich nur im
 * Speicher befindet. Er kann durch Unit-Tests getestet werden.
 */
public class VirtualRobot implements Robot {
  private List<MovementListener> movementListeners = new ArrayList<>();

  public LevelMap map;

  /**
   * Die Zeile, in der sich der Roboter aktuell befindet.
   */
  public int row;

  /**
   * Die Spalte, in der sich der Roboter aktuell befindet.
   */
  public int col;

  public Compass dir;

  public DirectionalPoint initPosition;

  /**
   * @see <a href=
   *      "https://github.com/France-ioi/bebras-modules/blob/ec1baf055c7f1c383ce8dfa5d27998463ef5be59/pemFioi/blocklyRobot_lib-1.1.js#L2456">blocklyRobot_lib-1.1.js
   *      L2456</a>
   */
  public int numberOfMovements;

  /**
   * Gibt an, ob die letzte Bewegung erfolgreich war.
   */
  public boolean movementSuccessful;

  public VirtualRobot(LevelMap map) {
    this.map = map;
  }

  public void setInitPosition(ItemData init) {
    row = init.row;
    col = init.col;
    dir = Compass.fromNumber(init.dir);

    initPosition = new DirectionalPoint(row, col, dir);
  }

  public void resetInitPosition() {
    row = initPosition.row;
    col = initPosition.col;
    dir = initPosition.dir;
  }

  public void addMovementListener(MovementListener listener) {
    movementListeners.add(listener);
  }

  public void addGridEdgesMovementListener() {
    addMovementListener((Compass direction) -> {
      switch (direction) {
        case EAST:
          return col < map.cols - 1;

        case SOUTH:
          return row < map.rows - 1;

        case WEST:
          return col > 0;

        case NORTH:
          return row > 0;

        default:
          return true;
      }
    });
  }

  protected boolean isInFrontOfObstacle(Compass direction) {
    int rowMovement = 0;
    int colMovement = 0;

    switch (direction) {
      case EAST:
        colMovement = 1;
        break;

      case SOUTH:
        rowMovement = 1;
        break;

      case WEST:
        colMovement = -1;
        break;

      case NORTH:
        rowMovement = -1;
        break;

      default:
    }

    return map.isObstacle(row + rowMovement, col + colMovement);
  }

  public boolean isInFrontOfObstacle() {
    return isInFrontOfObstacle(dir);
  }

  public void addObstaclesMovementListener() {
    addMovementListener((Compass direction) -> {
      return !isInFrontOfObstacle(direction);
    });
  }

  public void addDefaultMovementListener() {
    addGridEdgesMovementListener();
    addObstaclesMovementListener();
  }

  /**
   * @see <a href=
   *      "https://github.com/France-ioi/bebras-modules/blob/ec1baf055c7f1c383ce8dfa5d27998463ef5be59/pemFioi/blocklyRobot_lib-1.1.js#L2923-L2944">blocklyRobot_lib-1.1.js
   *      L2923-L2944</a>
   */
  public boolean tryToBeOn(Compass direction) {
    boolean result = true;
    for (MovementListener listener : this.movementListeners) {
      if (!listener.allowMovement(direction)) {
        result = false;
        break;
      }
    }
    movementSuccessful = result;
    return result;
  }

  /**
   * @param dir  Die Himmelsrichtung
   * @param mult Multiplikation
   *
   * @see <a href=
   *      "https://github.com/France-ioi/bebras-modules/blob/ec1baf055c7f1c383ce8dfa5d27998463ef5be59/pemFioi/blocklyRobot_lib-1.1.js#L2946-L2958">blocklyRobot_lib-1.1.js
   *      L2946-L2958</a>
   */
  public Point coordsInFront(Compass dir, int mult) {
    int[][] delta = new int[][] {
        new int[] { 0, 1 },
        new int[] { 1, 0 },
        new int[] { 0, -1 },
        new int[] { -1, 0 }
    };
    return new Point(row + delta[dir.getNumber()][0] * mult, col + delta[dir.getNumber()][1] * mult);
  }

  public Point coordsInFront(Compass dir) {
    return coordsInFront(dir, 1);
  }

  /**
   * @see <a href=
   *      "https://github.com/France-ioi/bebras-modules/blob/ec1baf055c7f1c383ce8dfa5d27998463ef5be59/pemFioi/blocklyRobot_lib-1.1.js#L3374-L3376">blocklyRobot_lib-1.1.js
   *      L3374-L3376</a>
   */
  public boolean obstacleInFront() {
    ItemData item = map.get(coordsInFront(dir));
    return item.isObstacle;
  }

  public Movement turnLeft() {
    dir = dir.rotate(3);
    return reportMovement();
  }

  public Movement turnRight() {
    dir = dir.rotate(1);
    return reportMovement();
  }

  public Movement turnAround() {
    dir = dir.rotate(2);
    return reportMovement();
  }

  /**
   * Gib den aktuellen gerichteten Punkt, an dem sich der Robot aktuell befindet.
   */
  public Movement reportMovement() {
    return new Movement(row, col, dir, movementSuccessful);
  }

  // https://github.com/France-ioi/bebras-modules/blob/ec1baf055c7f1c383ce8dfa5d27998463ef5be59/pemFioi/blocklyRobot_lib-1.1.js#L3280-L3297

  public Movement forward() {
    if (tryToBeOn(dir)) {
      Point point = coordsInFront(dir);
      row = point.row;
      col = point.col;
      numberOfMovements++;
    }
    return reportMovement();
  }

  public Movement east() {
    if (tryToBeOn(EAST)) {
      col++;
      dir = EAST;
      numberOfMovements++;
    }
    return reportMovement();
  }

  public Movement north() {
    if (tryToBeOn(NORTH)) {
      row--;
      dir = NORTH;
      numberOfMovements++;
    }
    return reportMovement();
  }

  public Movement west() {
    if (tryToBeOn(WEST)) {
      col--;
      dir = WEST;
      numberOfMovements++;
    }
    return reportMovement();
  }

  public Movement south() {
    if (tryToBeOn(SOUTH)) {
      row++;
      dir = SOUTH;
      numberOfMovements++;
    }
    return reportMovement();
  }

}
