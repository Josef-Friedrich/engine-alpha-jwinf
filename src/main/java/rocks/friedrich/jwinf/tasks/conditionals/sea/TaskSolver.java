package rocks.friedrich.jwinf.tasks.conditionals.sea;

import rocks.friedrich.jwinf.engine.Solver;

public class TaskSolver extends Solver<Robot> {

  public TaskSolver() {
    super("19-DE-12-stay-on-the-road");
  }

  @Override
  public void easy(Robot robot) {
    robot.rotateRight();
    robot.go();
  }

  @Override
  public void medium(Robot robot) {
    robot.rotateRight();
    robot.go();
    robot.go();
  }

  @Override
  public void hard(Robot robot) {
    robot.rotateRight();
    robot.go();
    robot.go();
    robot.go();
  }

  @Override
  public void all(Robot robot) {

  }

  public static void main(String[] args) {
    new TaskSolver().solve();

  }
}
