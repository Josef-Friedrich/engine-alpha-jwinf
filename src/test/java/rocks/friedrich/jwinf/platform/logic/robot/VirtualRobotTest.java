package rocks.friedrich.jwinf.platform.logic.robot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static rocks.friedrich.jwinf.TestHelper.loadVirtualRobot;
import static rocks.friedrich.jwinf.platform.logic.Compass.EAST;
import static rocks.friedrich.jwinf.platform.logic.Compass.WEST;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rocks.friedrich.jwinf.platform.logic.Compass;

/**
 * https://jwinf.de/task/1158
 */
public class VirtualRobotTest {

  VirtualRobot robot;

  @BeforeEach
  public void setUp() {
    robot = loadVirtualRobot("19-DE-12-stay-on-the-road");
  }

  private void assertMovement(Movement movement, int row, int col, Compass dir, boolean successful) {
    assertEquals(movement.to.row, row);
    assertEquals(movement.to.col, col);
    assertEquals(movement.to.dir, dir);
    assertEquals(movement.successful, successful);
  }

  @Test
  public void attributeRow() {
    assertEquals(robot.row, 8);
  }

  @Test
  public void attributeCol() {
    assertEquals(robot.col, 1);
  }

  @Test
  public void attributeDir() {
    assertEquals(robot.dir, EAST);
  }

  @Test
  public void methodEast() {
    assertMovement(robot.east(), 8, 2, EAST, true);
    assertEquals(robot.row, 8);
    assertEquals(robot.col, 2);
  }

  @Test
  public void methodSouth() {
    assertMovement(robot.south(), 8, 1, EAST, false);
  }

  @Test
  public void methodWest() {
    assertMovement(robot.west(), 8, 0, WEST, true);
    assertEquals(robot.row, 8);
    assertEquals(robot.col, 0);
  }

  @Test
  public void methodNorth() {
    assertMovement(robot.north(), 8, 1, EAST, false);
  }

  @Test
  public void cantMoveInFrontOfObstacles() {
    robot.east();
    assertTrue(robot.movementSuccessful);

    robot.north();
    assertFalse(robot.movementSuccessful);
  }

  @Test
  public void cantMoveOnTheEdge() {
    robot.west();
    assertTrue(robot.movementSuccessful);

    robot.west();
    assertFalse(robot.movementSuccessful);
  }

  @Test
  public void methodObstacleInFront() {
    assertFalse(robot.obstacleInFront());
    robot.turnLeft();
    assertEquals(robot.col, 1);
    assertEquals(robot.row, 8);
    assertTrue(robot.obstacleInFront());
    robot.turnLeft();
    assertFalse(robot.obstacleInFront());
    robot.turnLeft();
    assertTrue(robot.obstacleInFront());
    assertEquals(robot.col, 1);
    assertEquals(robot.row, 8);
  }

}
