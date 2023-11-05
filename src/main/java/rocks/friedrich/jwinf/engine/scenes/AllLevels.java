package rocks.friedrich.jwinf.engine.scenes;

import ea.Scene;
import rocks.friedrich.jwinf.engine.task.Task;

public class AllLevels extends Scene {

  public Task task;

  /**
   * aktuelle x-Position
   */
  private int x = 0;

  /**
   * aktuelle y-Position
   */
  private int y = 0;

  public AllLevels(Task task) {
    this.task = task;
    paintLevels();
  }

  public void paintLevels() {
    task.getLevels().forEach((difficulty, levels) -> {
      y = 0;
      levels.forEach((level) -> {
        level.paintMapInScene(this, x, y);
        y -= task.getMaxHeight() + 1;
      });
      x += task.getMaxWidth() + 1;
    });
  }
}
