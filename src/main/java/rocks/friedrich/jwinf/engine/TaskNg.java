package rocks.friedrich.jwinf.engine;

import java.io.IOException;

import rocks.friedrich.jwinf.engine.data.TaskLoader;
import rocks.friedrich.jwinf.engine.data.model.TaskData;

/**
 * Eine Trainingsaufgabe (Task) besteht aus mehreren Schwierigkeitsgraden
 * (Level)
 */
public class TaskNg {

  public TaskData data;

  /**
   * Zum Beispiel „Edelsteine einsammeln“
   */
  public String title;

  /**
   * Zum Beispiel „Programmiere den Roboter“
   */
  public String intro;

  public LevelCollectionNg levels;

  public TaskNg(String filePath) {
    try {
      data = TaskLoader.load(filePath);
    } catch (IOException e) {
      e.printStackTrace();
    }
    title = data.title;
    intro = data.intro;

    levels = new LevelCollectionNg(data._levels);
  }

  /**
   * @param difficulty
   * @param test
   * @return
   */
  public LevelNg getLevel(DifficultyLevel difficulty, int test) {
    return levels.getLevel(difficulty, test);
  }

  public LevelNg getLevel(DifficultyLevel difficulty) {
    return getLevel(difficulty, 0);
  }

  public LevelNg getLevel(int difficulty) {
    return getLevel(DifficultyLevel.indexOf(difficulty), 0);
  }

  // public void launchLevelByDifficulty(DifficultyLevel difficulty) {
  // Controller.launchLevel(getLevel(difficulty));
  // }

  // public void launchLevelByDifficulty(int difficulty) {
  // Controller.launchLevel(levels[difficulty]);
  // }

}
