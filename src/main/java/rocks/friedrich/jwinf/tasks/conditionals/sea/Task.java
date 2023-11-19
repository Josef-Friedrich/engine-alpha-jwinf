package rocks.friedrich.jwinf.tasks.conditionals.sea;

import rocks.friedrich.jwinf.engine.scenes.LevelScene;

public class Task {

  String taskId = "19-DE-12-stay-on-the-road";

  public void actOnEasy(Robot robot) {

  }

  public void actOnMedium(Robot robot) {

  }

  public void actOnHard(Robot robot) {

  }

  public void actOnAll(Robot robot) {

  }

  public static void main(String[] args) {
    LevelScene.launch("19-DE-12-stay-on-the-road", (r, level) -> {
      Robot robot = (Robot) r;
      robot.rotateRight();
      robot.go(1);
      robot.rotateLeft();
      robot.go(1);
    });
  }
}
