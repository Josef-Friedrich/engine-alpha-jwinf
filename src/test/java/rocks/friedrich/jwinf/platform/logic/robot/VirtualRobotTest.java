package rocks.friedrich.jwinf.platform.logic.robot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static rocks.friedrich.jwinf.TestHelper.loadVirtualRobot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rocks.friedrich.jwinf.platform.logic.CompassDirection;

public class VirtualRobotTest {

  VirtualRobot robot;

  @BeforeEach
  public void setUp() {
    robot = loadVirtualRobot("19-DE-12-stay-on-the-road");
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
    assertEquals(robot.dir, CompassDirection.EAST);
  }

  @Test
  public void cantMoveInFrontOfObstacles() {
    robot.east();
    assertTrue(robot.lastMovementSuccessful);

    robot.north();
    assertFalse(robot.lastMovementSuccessful);
  }

  @Test
  public void cantMoveOnTheEdge() {
    robot.west();
    assertTrue(robot.lastMovementSuccessful);

    robot.west();
    assertFalse(robot.lastMovementSuccessful);
  }

}
