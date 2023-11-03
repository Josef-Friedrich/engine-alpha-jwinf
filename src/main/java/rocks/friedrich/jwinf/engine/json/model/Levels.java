package rocks.friedrich.jwinf.engine.json.model;

import rocks.friedrich.jwinf.engine.DifficultyLevel;

public class Levels {
  public Level[] easy;
  public Level[] medium;
  public Level[] hard;

  public Level getLevel(DifficultyLevel difficulty, int test) {
    Level[] levels;

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

  public Level getLevel(DifficultyLevel difficulty) {
    return getLevel(difficulty, 0);
  }

  public Level getLevel(int difficulty) {
    return getLevel(DifficultyLevel.indexOf(difficulty), 0);
  }
}
