package rocks.friedrich.jwinf.engine.scenes;

import java.awt.event.KeyEvent;

import ea.Scene;
import ea.internal.Bounds;
import rocks.friedrich.jwinf.engine.Robot;
import rocks.friedrich.jwinf.engine.Controller;
import rocks.friedrich.jwinf.engine.Difficulty;
import rocks.friedrich.jwinf.engine.WindowScene;
import rocks.friedrich.jwinf.engine.task.Level;
import rocks.friedrich.jwinf.engine.task.Task;

import ea.event.KeyListener;

public class LevelScene extends Scene implements WindowScene, KeyListener {
  public Task task;
  public Level level;

  private Robot robot;

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

  @Override
  public void onKeyDown(KeyEvent e) {
    switch (e.getKeyCode()) {
      // n = next
      case KeyEvent.VK_N:
        break;
      // p = previous
      case KeyEvent.VK_P:

        break;

      case KeyEvent.VK_RIGHT:
        robot.goRightNonBlocking();
        break;

      case KeyEvent.VK_UP:
        robot.goUpNonBlocking();
        break;

      case KeyEvent.VK_LEFT:
        robot.goLeftNonBlocking();
        break;

      case KeyEvent.VK_DOWN:
        robot.goDownNonBlocking();
        break;
    }
  }

  public static void main(String[] args) {
    launch("20-DE-13-Kerzen-einfach", Difficulty.HARD);
  }

}
