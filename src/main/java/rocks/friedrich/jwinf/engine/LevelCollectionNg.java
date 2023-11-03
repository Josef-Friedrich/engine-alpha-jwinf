package rocks.friedrich.jwinf.engine;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import rocks.friedrich.jwinf.engine.data.model.LevelCollectionData;
import rocks.friedrich.jwinf.engine.data.model.LevelData;

public class LevelCollectionNg {
  public Map<DifficultyLevel, List<LevelNg>> levels;

  public LevelCollectionNg(LevelCollectionData data) {
    levels = new EnumMap<>(DifficultyLevel.class);
    for (LevelData level : data.getLevelList()) {
      DifficultyLevel difficulty = level.difficulty;
      List<LevelNg> levelList = levels.get(difficulty);
      if (levelList == null) {
        levelList = new ArrayList<LevelNg>();
        levels.put(difficulty, levelList);
      }
      levelList.add(new LevelNg(level));
    }
  }

  public LevelNg getLevel(DifficultyLevel difficulty, int test) {
    if (test < 1) {
      test = 1;
    }
    return levels.get(difficulty).get(test - 1);
  }

  public LevelNg getLevel(DifficultyLevel difficulty) {
    return getLevel(difficulty, 0);
  }

  public LevelNg getLevel(int difficulty) {
    return getLevel(DifficultyLevel.indexOf(difficulty), 0);
  }
}
