package rocks.friedrich.jwinf.platform;

import rocks.friedrich.jwinf.platform.gui.Controller;
import rocks.friedrich.jwinf.platform.gui.scenes.AllLevelsScene;
import rocks.friedrich.jwinf.platform.gui.scenes.AssembledLevelScene;
import rocks.friedrich.jwinf.platform.gui.scenes.LevelScene;
import rocks.friedrich.jwinf.platform.gui.scenes.WindowScene;
import rocks.friedrich.jwinf.platform.logic.level.Difficulty;

/**
 * Klasse, die verschiedene Methoden beinhaltet, die die verschiedenen
 * Versionen einer Trainingsaufgabe löst.
 */
public abstract class Solver<T> {

  protected String taskId;

  public Solver(String taskId) {
    this.taskId = taskId;
  }

  public void easy(T robot) {

  }

  public void medium(T robot) {

  }

  public void hard(T robot) {

  }

  public void all(T robot) {

  }

  public void solve() {
    solve(null, 0);
  }

  public void solve(String difficutly) {
    solve(difficutly, 0);
  }

  @SuppressWarnings("unchecked")
  public void solve(String difficutly, int test) {
    AssembledLevelScene scene;
    if (difficutly == null) {
      scene = new AllLevelsScene(taskId);
    } else {
      scene = new LevelScene(taskId, Difficulty.indexOf(difficutly), test);
    }

    Controller.launchScene((WindowScene) scene);

    scene.getAssembledLevels().forEach((level) -> {
      new Thread(() -> {
        switch (level.level.difficulty) {
          case EASY:
            easy((T) level.robot);
            break;

          case MEDIUM:
            medium((T) level.robot);
            break;

          case HARD:
            hard((T) level.robot);
            break;

          default:
            break;
        }
      }).start();

    });
  }
}
