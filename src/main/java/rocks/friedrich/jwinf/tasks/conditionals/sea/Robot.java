package rocks.friedrich.jwinf.tasks.conditionals.sea;

import rocks.friedrich.jwinf.engine.RobotWrapper;

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

}
