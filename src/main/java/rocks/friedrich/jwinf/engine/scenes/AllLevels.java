package rocks.friedrich.jwinf.engine.scenes;

import ea.Scene;
import rocks.friedrich.jwinf.engine.Controller;
import rocks.friedrich.jwinf.engine.task.Task;

public class AllLevels extends Scene {

  public Task task;

  /**
   * aktuelle x-Position
   */
  private float x = 0;

  /**
   * aktuelle y-Position
   */
  private float y = 0;

  public AllLevels(Task task) {
    this.task = task;
    paintLevels();
  }

  public AllLevels(String taskId) {
    this(Task.loadById(taskId));
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

  public static void main(String[] args) {
    Controller.launchScene(new AllLevels("17-FR-07-platforms-marbles"));
  }

}
