package rocks.friedrich.jwinf.engine.task;

import rocks.friedrich.jwinf.engine.data.model.TileData;

public class LevelMap {
  private int[][] map;

  private TilesStore tiles;

  /**
   * Anzahl an Reihen (y-Richtung bzw Höhe)
   */
  public int rows;

  /**
   * Anzahl an Spalten (x-Richtung bzw Breite)
   */
  public int cols;

  public LevelMap(int[][] map) {
    this.map = map;
    rows = map.length;
    cols = map[0].length;
  }

  public TileData get(int row, int col) {
    int num = map[row][col];
    return tiles.get(num);
  }

}
