package rocks.friedrich.jwinf.platform.logic.robot;

import static rocks.friedrich.jwinf.platform.logic.CompassDirection.EAST;
import static rocks.friedrich.jwinf.platform.logic.CompassDirection.NORTH;
import static rocks.friedrich.jwinf.platform.logic.CompassDirection.SOUTH;
import static rocks.friedrich.jwinf.platform.logic.CompassDirection.WEST;

import java.util.ArrayList;
import java.util.List;

import rocks.friedrich.jwinf.platform.data.model.ItemData;
import rocks.friedrich.jwinf.platform.logic.CompassDirection;
import rocks.friedrich.jwinf.platform.logic.level.LevelMap;
import rocks.friedrich.jwinf.platform.logic.map.DirectionalPoint;
import rocks.friedrich.jwinf.platform.logic.map.Movement;

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

  public CompassDirection dir;

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
    dir = CompassDirection.fromNo(init.dir);

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
    addMovementListener((CompassDirection direction) -> {
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

  protected boolean isInFrontOfObstacle(CompassDirection direction) {
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
    addMovementListener((CompassDirection direction) -> {
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
  public boolean tryToBeOn(CompassDirection direction) {
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
   * Gib den aktuellen gerichteten Punkt, an dem sich der Robot aktuell befindet.
   */
  public Movement reportMovement() {
    return new Movement(row, col, dir, movementSuccessful);
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
