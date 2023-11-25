package rocks.friedrich.jwinf.platform.logic.robot;

import rocks.friedrich.jwinf.platform.logic.map.DirectionalPoint;

public class Movement {
  private VirtualRobot robot;

  public DirectionalPoint from;

  public DirectionalPoint to;

  public String name;

  /**
   * Gibt an, ob die Bewegung stattgefunden hat oder nicht.
   */
  public boolean successful;

  public Movement(String name, VirtualRobot robot) {
    this.name = name;
    from = new DirectionalPoint(robot.row, robot.col, robot.dir);
    this.robot = robot;
  }

  public Movement setTo() {
    to = new DirectionalPoint(robot.row, robot.col, robot.dir);
    this.successful = robot.movementSuccessful;
    return this;
  }
}
