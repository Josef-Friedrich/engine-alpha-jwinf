package rocks.friedrich.jwinf.engine.task;

import ea.Vector;
import rocks.friedrich.jwinf.engine.data.model.TileData;

public class LevelMap {
  private int[][] map;

  private TilesStore tiles;

  /**
   * Anzahl an Reihen (y-Richtung bzw. Höhe)
   */
  public int rows;

  /**
   * Anzahl an Spalten (x-Richtung bzw. Breite)
   */
  public int cols;

  /**
   * Die x-Koordinate des linken unteren Ecks, an dem das Kachelgitter im
   * Engine-Alpha-Koordinatensystem
   * verankert ist.
   */
  public int x;

  /**
   * Die y-Koordinate des linken unteren Ecks, an dem das Kachelgitter im
   * Engine-Alpha-Koordinatensystem
   * verankert ist.
   */
  public int y;

  public LevelMap(int[][] map) {
    this.map = map;
    rows = map.length;
    cols = map[0].length;
  }

  public LevelMap(int rows, int cols) {
    this(new int[rows][cols]);
  }

  public TileData get(int row, int col) {
    int num = map[row][col];
    return tiles.get(num);
  }

  public boolean isObstacle(int row, int col) {
    return get(row, col).isObstacle;
  }

  public void setPosition(float x, float y) {
    this.x = Math.round(x);
    this.y = Math.round(y);
  }

  /**
   * @param vector Ein Punkt im Engine-Alpha-Koordinatensystem.
   */
  public Point translateToPoint(Vector vector) {
    int xVector = Math.round(vector.getX());
    int yVector = Math.round(vector.getY());
    // Verankerung der linken UNTEREN Ecke des Kachelgitters.
    int xMap = x;
    int yMap = y;

    // y-Koordinate des Ursprungs des Kachelgitters (links oben)
    int yMap0 = yMap + rows - 1;
    return new Point(yMap0 - yVector, xVector - xMap);
  }

  public Point translateToPoint(float x, float y) {
    return translateToPoint(new Vector(x, y));
  }

  public Vector translateToVector(Point point) {
    // y-Koordinate des linken oberen Ecks des Kachelgitters (Ursprung des
    // Kachelgitter)
    // int yMap0 = map.rows - 1 + y;
    // return y - yMap0;

    // int xV = Math.round(vector.getX());
    // int yV = Math.round(vector.getY());
    return new Vector(0, 0);
  }

  public Vector translateToVector(int row, int col) {
    return translateToVector(new Point(row, col));
  }

}
