package rocks.friedrich.jwinf.tasks.conditionals.sea;

import rocks.friedrich.jwinf.platform.Solver;

public class TaskSolver extends Solver<Robot> {

  public TaskSolver() {
    super("19-DE-12-stay-on-the-road");
  }

  @Override
  public void easy(Robot robot) {
    for (int i = 0; i < 17; i++) {
      robot.go();
      if (robot.isInFrontOfObstacle()) {
        robot.rotateLeft();
      }
    }
  }

  @Override
  public void medium(Robot robot) {
    for (int i = 0; i < 20; i++) {
      robot.go();
      if (robot.isInFrontOfObstacle()) {
        robot.rotateRight();
        if (robot.isInFrontOfObstacle()) {
          robot.rotateLeft();
          robot.rotateLeft();
        }
      }
    }
  }

  @Override
  public void hard(Robot robot) {
    for (int i = 0; i < 20; i++) {
      robot.go();
      if (robot.isInFrontOfObstacle()) {
        robot.rotateRight();
        if (robot.isInFrontOfObstacle()) {
          robot.rotateLeft();
          robot.rotateLeft();
        }
      } else {
        robot.rotateLeft();
        if (!robot.isInFrontOfObstacle()) {
          robot.go();
        } else {
          robot.rotateRight();
        }
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
