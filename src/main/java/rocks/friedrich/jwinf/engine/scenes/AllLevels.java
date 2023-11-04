package rocks.friedrich.jwinf.engine.scenes;

import ea.Scene;
import rocks.friedrich.jwinf.engine.task.Level;
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

  private Level lastLevel;

  public AllLevels(Task task) {
    this.task = task;
    paintLevels();
  }

  public void paintLevels() {
    task.getLevels().forEach((difficulty, levels) -> {
      y = 0;
      if (lastLevel != null) {
        x += lastLevel.width + 1;
      }
      levels.forEach((level) -> {
        level.paintMapInScene(this, x, y);
        y -= level.height +1;
        lastLevel = level;
      });
    });
  }
}
