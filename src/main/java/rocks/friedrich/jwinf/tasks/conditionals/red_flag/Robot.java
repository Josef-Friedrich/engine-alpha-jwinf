package rocks.friedrich.jwinf.tasks.conditionals.red_flag;

import rocks.friedrich.jwinf.platform.gui.robot.RobotWrapper;

public class Robot extends RobotWrapper {

  public boolean isInFrontOfObstacle() {
    return actor.isInFrontOfObstacle();
  }

  public void rotateLeft() {
    actor.rotateLeft();
  }

  public void rotateRight() {
    actor.rotateRight();
  }

  public void go() {
    actor.go();
  }

  public Boolean reachedRedFlag() {
    return actor.isOnExit();
  }

}
