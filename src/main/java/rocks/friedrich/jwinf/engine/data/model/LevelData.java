package rocks.friedrich.jwinf.engine.data.model;

import rocks.friedrich.jwinf.engine.DifficultyLevel;

public class LevelData {
  public int[][] tiles;
  public InitItemData[] initItems;

  public DifficultyLevel difficulty;

  /**
   * 0 no other test 1: first test of x 2: second test of x. x > 2
   */
  public int testNo;

  public int getWidth() {
    return tiles[0].length;
  }

  public int getHeight() {
    return tiles.length;
  }
}
