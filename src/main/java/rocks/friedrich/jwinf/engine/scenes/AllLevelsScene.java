package rocks.friedrich.jwinf.engine.scenes;

import ea.Scene;
import ea.internal.Bounds;
import rocks.friedrich.jwinf.engine.Controller;
import rocks.friedrich.jwinf.engine.WindowScene;
import rocks.friedrich.jwinf.engine.task.Task;

public class AllLevelsScene extends Scene implements WindowScene {

  public Task task;

  private final float INITAL_X = 0;

  private final float INITAL_Y = 0;

  /**
   * Abstand zwischen den Tests.
   */
  private final float MARGIN = 1f;

  /**
   * aktuelle x-Position
   */
  private float x = 0;

  /**
   * aktuelle y-Position
   */
  private float y = 0;

  public AllLevelsScene(Task task) {
    this.task = task;
    paintLevels();
  }

  public AllLevelsScene(String taskId) {
    this(Task.loadById(taskId));
  }

  public float getWidth() {
    int numDiff = task.getNumberOfDifficulties();
    return (task.getMaxCols() * numDiff) + (MARGIN * numDiff - 1);
  }

  public float getHeight() {
    int numLevels = task.getMaxLevelsPerDifficulty();
    return (task.getMaxRows() * numLevels) + (MARGIN * numLevels - 1);
  }

  public String getTitle() {
    return task.title;
  }

  public Bounds getWindowBounds() {
    return new Bounds(INITAL_X, INITAL_Y - getHeight() + task.getMaxRows(), getWidth(),
        getHeight());
  }

  public void paintLevels() {
    x = INITAL_X;
    task.getLevels().forEach((difficulty, levels) -> {
      y = INITAL_Y;
      levels.forEach((level) -> {
        level.paintMapInScene(this, x, y);
        y -= task.getMaxRows() + 1;
      });
      x += task.getMaxCols() + 1;
    });
  }

  public static void launch(String taskId) {
    var scene = new AllLevelsScene(taskId);
    Controller.launchScene((WindowScene) scene);
  }

  public static void main(String[] args) {
    // launch("20-DE-13-Kerzen-einfach");
    launch("17-FR-07-platforms-marbles");
  }

}
