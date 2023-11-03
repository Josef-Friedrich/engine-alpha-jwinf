package rocks.friedrich.jwinf.engine.data.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import rocks.friedrich.jwinf.engine.Difficulty;

public class LevelCollectionData {
  public LevelData[] easy;
  public LevelData[] medium;
  public LevelData[] hard;

  public List<LevelData> getLevelList() {
    List<LevelData> collection = new ArrayList<>();
    prepareList(easy, Difficulty.EASY, collection);
    prepareList(medium, Difficulty.MEDIUM, collection);
    prepareList(hard, Difficulty.HARD, collection);
    return collection;
  }

  private void prepareList(LevelData[] levels, Difficulty difficulty, Collection<LevelData> collection) {
    if (levels.length > 1) {
      for (int i = 1; i <= levels.length; i++) {
        LevelData level = levels[i - 1];
        level.testNo = i;
        level.difficulty = difficulty;
        collection.add(level);

      }
    } else {
      LevelData level = levels[0];
      level.difficulty = difficulty;
      collection.add(level);
    }
  }

  public LevelData getLevel(Difficulty difficulty, int test) {
    LevelData[] levels;

    switch (difficulty) {
      default:
      case EASY:
        levels = easy;
        break;

      case MEDIUM:
        levels = medium;
        break;

      case HARD:
        levels = hard;
        break;
    }

    return levels[test];
  }

  public LevelData getLevel(Difficulty difficulty) {
    return getLevel(difficulty, 0);
  }

  public LevelData getLevel(int difficulty) {
    return getLevel(Difficulty.indexOf(difficulty), 0);
  }
}
