package rocks.friedrich.jwinf.platform.logic.robot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static rocks.friedrich.jwinf.TestHelper.loadVirtualRobot;

import org.junit.jupiter.api.Test;

public class VirtualRobotTest {
  VirtualRobot robot = loadVirtualRobot("19-DE-12-stay-on-the-road");

  @Test
  public void attributeRow() {
    assertEquals(robot.row, 0);
  }

  @Test
  public void attributeCol() {
    assertEquals(robot.col, 0);
  }

}
