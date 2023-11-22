package rocks.friedrich.jwinf.platform.logic.map;

import rocks.friedrich.jwinf.platform.logic.CompassDirection;

public class Movement extends DirectionalPoint {
  /**
   * Gibt an, ob die Bewegung stattgefunden hat oder nicht.
   */
  public boolean successful;

  public Movement(int row, int col, CompassDirection dir, boolean successful) {
    super(row, col, dir);
    this.successful = successful;
  }
}
