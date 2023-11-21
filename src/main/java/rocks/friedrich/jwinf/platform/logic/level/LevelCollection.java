package rocks.friedrich.jwinf.platform.logic.level;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import rocks.friedrich.jwinf.platform.data.model.LevelCollectionData;
import rocks.friedrich.jwinf.platform.data.model.LevelData;
import rocks.friedrich.jwinf.platform.level.Level;
import rocks.friedrich.jwinf.platform.logic.Task;

/**
 * Die Tests (Level) nach Schwierigkeitsgraden geordnet.
 */
public class LevelCollection {
  public Task task;

  public Map<Difficulty, List<Level>> levels;

  public List<Level> list;

  public int numberOfLevels;

  public int maxCols;

  public int maxRows;

  private void setMaxRowsAndCols() {
    for (Level level : list) {
      if (level.cols > maxCols) {
        maxCols = level.cols;
      }
      if (level.rows > maxRows) {
        maxRows = level.rows;
      }
    }
  }

  public LevelCollection(LevelCollectionData data, Task task) {
    this.task = task;
    list = new ArrayList<>();
    levels = new EnumMap<>(Difficulty.class);
    for (LevelData levelData : data.getLevelList()) {
      Difficulty difficulty = levelData.difficulty;
      List<Level> levelList = levels.get(difficulty);
      if (levelList == null) {
        levelList = new ArrayList<Level>();
        levels.put(difficulty, levelList);
      }
      Level level = new Level(levelData, task);
      levelList.add(level);
      list.add(level);
    }

    numberOfLevels = list.size();

    setMaxRowsAndCols();
  }

  public Level getLevel(Difficulty difficulty, int test) {
    if (test < 1) {
      test = 1;
    }
    return levels.get(difficulty).get(test - 1);
  }

  public Level getLevel(Difficulty difficulty) {
    return getLevel(difficulty, 0);
  }

  public Level getLevel(int difficulty) {
    return getLevel(Difficulty.indexOf(difficulty), 0);
  }

}
