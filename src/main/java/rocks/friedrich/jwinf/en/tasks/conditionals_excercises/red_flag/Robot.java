package rocks.friedrich.jwinf.en.tasks.conditionals_excercises.red_flag;

import rocks.friedrich.jwinf.platform.logic.robot.RobotWrapper;

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
    actor.forward();
  }

  public Boolean reachedRedFlag() {
    return actor.isOnExit();
  }

}
