package rocks.friedrich.jwinf.platform.logic.map;

import ea.Direction;
import rocks.friedrich.jwinf.platform.logic.CompassDirection;

public class DirectionalPoint extends Point {
  public CompassDirection dir;

  public DirectionalPoint(int row, int col, CompassDirection dir) {
    super(row, col);
    this.dir = dir;
  }

  public Direction getDirection() {
    return CompassDirection.toDirection(dir);
  }
}
