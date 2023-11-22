package rocks.friedrich.jwinf.tasks.conditionals.sea;

import rocks.friedrich.jwinf.platform.gui.robot.RobotWrapper;

public class Robot extends RobotWrapper {

  public boolean obstacleInFront() {
    return actor.obstacleInFront();
  }

  public void turnLeft() {
    actor.turnLeft();
  }

  public void turnRight() {
    actor.turnRight();
  }

  public void go() {
    actor.go();
  }

}
