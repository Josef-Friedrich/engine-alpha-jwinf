package rocks.friedrich.jwinf.engine.map;

/**
 * Ein Punkt auf dem Kachelgitter. Der Ursprung ist links oben (Reihe 0 und Spalte 0).
 */
public class Point {

  public int col;
  public int row;

  public Point(int row, int col) {
    this.row = row;
    this.col = col;
  }

}
