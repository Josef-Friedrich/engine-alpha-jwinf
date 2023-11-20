package rocks.friedrich.jwinf.platform.task;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import rocks.friedrich.jwinf.platform.Color;
import rocks.friedrich.jwinf.platform.data.JsonLoader;
import rocks.friedrich.jwinf.platform.data.model.TaskData;
import rocks.friedrich.jwinf.platform.level.Difficulty;
import rocks.friedrich.jwinf.platform.level.Level;
import rocks.friedrich.jwinf.platform.level.LevelCollection;
import rocks.friedrich.jwinf.platform.map.TilesStore;

/**
 * Eine Trainingsaufgabe (Task) besteht aus mehreren (in der Regel 3)
 * Schwierigkeitsgraden (Difficulty). Ein Schwierigkeitsgrad kann einen oder
 * mehrere Tests (Level) haben.
 */
public class Task {

  public TaskData data;

  /**
   * Zum Beispiel „Edelsteine einsammeln“
   */
  public String title;

  /**
   * Zum Beispiel „Programmiere den Roboter“
   */
  public String intro;

  public LevelCollection levels;

  public TilesStore tiles;

  public Color backgroundColor;

  public Color gridColor;

  /**
   * Die Anzahl an Tests (Level) der Schwierigkeitsstufe mit den meisten Tests.
   */
  private int maxLevelsPerDifficulty;

  public Task(String filePath) {
    try {
      data = JsonLoader.loadTask(filePath);
    } catch (IOException e) {
      e.printStackTrace();
    }
    title = data.title;
    intro = data.intro;

    backgroundColor = new Color(data.grid.backgroundColor);
    gridColor = new Color(data.grid.gridColor);

    tiles = new TilesStore(data.grid.tiles);
    levels = new LevelCollection(data.levels, this);
  }

  public static Task loadById(String id) {
    return new Task("data/tasks/%s.json".formatted(id));
  }

  public Map<Difficulty, List<Level>> getLevels() {
    return levels.levels;
  }

  public Level getLevel(Difficulty difficulty, int test) {
    return levels.getLevel(difficulty, test);
  }

  public Level getLevel(Difficulty difficulty) {
    return getLevel(difficulty, 0);
  }

  public Level getLevel(int difficulty) {
    return getLevel(Difficulty.indexOf(difficulty), 0);
  }

  /**
   * Die Anzahl der Kacheln einer Zeile, des Tests (Level) mit der
   * größten
   * horizontalen Ausdehnung.
   */
  public int getMaxCols() {
    return levels.maxCols;
  }

  /**
   * Die Anzahl der Kacheln einer Zeile, des Tests (Level) mit der
   * größten
   * vertikalen Ausdehnung.
   */
  public int getMaxRows() {
    return levels.maxRows;
  }

  /**
   * Die Anzahl an Schwierigkeitsgraden (In der Regel 3).
   */
  public int getNumberOfDifficulties() {
    return levels.levels.size();
  }

  /**
   * Die Anzahl der Tests des Schwierigkeitsgrads mit den meisten Tests.
   */
  public int getMaxLevelsPerDifficulty() {
    getLevels().forEach((difficulty, levels) -> {
      if (maxLevelsPerDifficulty < levels.size()) {
        maxLevelsPerDifficulty = levels.size();
      }
    });
    return maxLevelsPerDifficulty;
  }

  /**
   * Die Anzahl aller Tests (Level).
   */
  public int getNumberOfLevels() {
    return levels.numberOfLevels;
  }

}
