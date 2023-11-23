package rocks.friedrich.jwinf.en.tasks.conditionals_excercises.red_flag;

import rocks.friedrich.jwinf.platform.Solver;

public class TaskSolver extends Solver<Robot> {

  public TaskSolver() {
    super("17-FR-07-platforms-marbles");
  }

  @Override
  public void easy(Robot robot) {
    while (!robot.reachedRedFlag()) {
      robot.rotateLeft();
      if (robot.isInFrontOfObstacle()) {
        robot.rotateRight();
      }
      robot.go();
    }
  }

  @Override
  public void medium(Robot robot) {
    while (!robot.reachedRedFlag()) {
      robot.rotateLeft();
      if (robot.isInFrontOfObstacle()) {
        robot.rotateRight();
        robot.rotateRight();
        if (robot.isInFrontOfObstacle()) {
          robot.rotateLeft();
        }
      }
      robot.go();
    }
  }

  @Override
  public void hard(Robot robot) {
    while (!robot.reachedRedFlag()) {
      robot.rotateRight();
      if (robot.isInFrontOfObstacle()) {
        robot.rotateLeft();
        if (!robot.isInFrontOfObstacle()) {
          robot.go();
        } else {
          robot.rotateLeft();
        }
      } else {
        robot.go();
      }
    }
  }

  @Override
  public void all(Robot robot) {

  }

  public static void main(String[] args) {
    new TaskSolver().solve();
  }
}
