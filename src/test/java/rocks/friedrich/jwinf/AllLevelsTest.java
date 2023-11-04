package rocks.friedrich.jwinf;

import ea.Scene;
import ea.Vector;
import rocks.friedrich.jwinf.engine.Controller;
import rocks.friedrich.jwinf.engine.scenes.AllLevels;
import rocks.friedrich.jwinf.engine.task.Task;

public class AllLevelsTest extends Scene {

  public static void main(String[] args) {
    new AllLevelsTest();
  }

  public AllLevelsTest() {
    var task = new Task("data/sea.json");
    var levels = new AllLevels(task);
    levels.getCamera().setPosition(new Vector(15, 5));
    Controller.launchScene(1200, 600, levels);
  }
}
