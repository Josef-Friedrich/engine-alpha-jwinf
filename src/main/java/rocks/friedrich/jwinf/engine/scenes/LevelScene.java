package rocks.friedrich.jwinf.engine.scenes;

import ea.Scene;
import ea.internal.Bounds;
import rocks.friedrich.jwinf.engine.Controller;
import rocks.friedrich.jwinf.engine.Difficulty;
import rocks.friedrich.jwinf.engine.WindowScene;
import rocks.friedrich.jwinf.engine.task.Level;
import rocks.friedrich.jwinf.engine.task.Task;

public class LevelScene extends Scene implements WindowScene {
  public Task task;
  public Level level;

  public LevelScene(String taskId, Difficulty difficulty) {
    this(taskId, difficulty, 0);
  }

  public LevelScene(String taskId, Difficulty difficulty, int test) {
    this(Task.loadById(taskId), difficulty, test);
  }

  public LevelScene(Task task, Difficulty difficulty, int test) {
    this.task = task;
    level = task.getLevel(difficulty, test);
    level.paintMapInScene(this, 0, 0);
  }

  public Bounds getWindowBounds() {
    return new Bounds(0, 0, level.cols, level.rows);
  }

  public String getTitle() {
    return level.task.title;
  }

  public static void launch(String taskId, Difficulty difficulty) {
    launch(taskId, difficulty, 0);
  }

  public static void launch(String taskId, Difficulty difficulty, int test) {
    Controller.launchScene((WindowScene) new LevelScene(taskId, difficulty, test));
  }

  public static void main(String[] args) {
    launch("20-DE-13-Kerzen-einfach", Difficulty.HARD);
  }

}
