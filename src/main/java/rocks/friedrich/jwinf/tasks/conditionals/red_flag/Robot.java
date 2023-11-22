package rocks.friedrich.jwinf.tasks.conditionals.red_flag;

import rocks.friedrich.jwinf.platform.gui.robot.RobotWrapper;

public class Robot extends RobotWrapper {

  public boolean isInFrontOfObstacle() {
    return actor.obstacleInFront();
  }

  public void rotateLeft() {
    actor.turnLeft();
  }

  public void rotateRight() {
    actor.turnRight();
  }

  public void go() {
    actor.go();
  }

  public Boolean reachedRedFlag() {
    return actor.isOnExit();
  }

}
