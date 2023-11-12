package rocks.friedrich.jwinf.engine.scenes;

import java.awt.event.KeyEvent;

import ea.Scene;
import ea.event.KeyListener;
import ea.internal.Bounds;
import rocks.friedrich.jwinf.engine.Controller;
import rocks.friedrich.jwinf.engine.TaskList;
import rocks.friedrich.jwinf.engine.WindowScene;
import rocks.friedrich.jwinf.engine.task.Task;

public class AllLevelsScene extends Scene implements WindowScene, KeyListener {

  static TaskList taskList = TaskList.readFromMenu();

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
    return new Bounds(INITAL_X - 0.5f, INITAL_Y - getHeight() + task.getMaxRows() - 0.5f, getWidth(),
        getHeight());
  }

  public void paintLevels() {
    x = INITAL_X;
    task.getLevels().forEach((difficulty, levels) -> {
      y = INITAL_Y;
      levels.forEach((level) -> {
        level.placeActorsInScene(this, x, y);
        y -= task.getMaxRows() + 1;
      });
      x += task.getMaxCols() + 1;
    });
  }

  public static void launch(String taskId) {
    var scene = new AllLevelsScene(taskId);
    Controller.launchScene((WindowScene) scene);
  }

  @Override
  public void onKeyDown(KeyEvent e) {
    switch (e.getKeyCode()) {
      // n = next
      case KeyEvent.VK_N:
        launch(taskList.next());
        break;
      // p = previous
      case KeyEvent.VK_P:
        launch(taskList.previous());
        break;
    }
  }

  public static void main(String[] args) {
    // launch("20-DE-13-Kerzen-einfach");
    launch("17-FR-07-platforms-marbles");
  }

}
