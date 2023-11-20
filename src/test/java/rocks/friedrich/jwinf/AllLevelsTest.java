package rocks.friedrich.jwinf;

import ea.Scene;
import ea.Vector;
import rocks.friedrich.jwinf.platform.Controller;
import rocks.friedrich.jwinf.platform.engine_alpha.scenes.AllLevelsScene;
import rocks.friedrich.jwinf.platform.task.Task;

public class AllLevelsTest extends Scene {

  public static void main(String[] args) {
    new AllLevelsTest();
  }

  public AllLevelsTest() {
    var task = Task.loadById("17-FR-07-platforms-marbles");
    var levels = new AllLevelsScene(task);
    levels.getCamera().setPosition(new Vector(15, 5));
    Controller.launchScene(1200, 600, levels);
  }
}
