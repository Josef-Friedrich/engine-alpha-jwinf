package rocks.friedrich.jwinf.en.tasks.conditionals_excercises.find_the_destination;

import rocks.friedrich.jwinf.platform.logic.robot.RobotWrapper;

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

  public void forward() {
    actor.forward();
  }

  public Boolean reachedRedFlag() {
    return actor.isOnExit();
  }

}
