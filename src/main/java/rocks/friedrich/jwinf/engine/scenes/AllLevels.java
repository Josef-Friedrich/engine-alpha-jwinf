package rocks.friedrich.jwinf.engine.scenes;

import ea.Scene;
import rocks.friedrich.jwinf.engine.task.Task;

public class AllLevels extends Scene {

  public Task task;

  int x = 0;

  public AllLevels(Task task) {
    this.task = task;
    task.getLevels().forEach((difficulty, levels) -> {
      levels.forEach((level) -> {
        level.paintMapInScene(this, x, 0);
        x += level.width + 1;
      });
    });
  }
}
