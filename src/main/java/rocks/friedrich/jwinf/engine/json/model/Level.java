package rocks.friedrich.jwinf.engine.json.model;

public class Level {
  public int[][] tiles;
  public InitItem[] initItems;

  public int getWidth() {
    return tiles[0].length;
  }

  public int getHeight() {
    return tiles.length;
  }
}
