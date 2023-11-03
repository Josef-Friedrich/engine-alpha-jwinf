package rocks.friedrich.jwinf.engine.task;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import rocks.friedrich.jwinf.engine.Difficulty;
import rocks.friedrich.jwinf.engine.data.model.LevelCollectionData;
import rocks.friedrich.jwinf.engine.data.model.LevelData;

public class LevelCollection {
  Task task;
  public Map<Difficulty, List<Level>> levels;

  public LevelCollection(LevelCollectionData data, Task task) {
    this.task = task;
    levels = new EnumMap<>(Difficulty.class);
    for (LevelData level : data.getLevelList()) {
      Difficulty difficulty = level.difficulty;
      List<Level> levelList = levels.get(difficulty);
      if (levelList == null) {
        levelList = new ArrayList<Level>();
        levels.put(difficulty, levelList);
      }
      levelList.add(new Level(level, task));
    }
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
