package rocks.friedrich.jwinf.platform.robot;

import ea.Direction;

interface MovementListener {
  /**
   * @param row       Die aktuelle Zeile (y), in der sich die Figur im
   *                  Kachel-Gitter befindet.
   * @param col       Die aktuelle Spalte (x), in der sich die Figur im
   *                  Kachel-Gitter befindet.
   * @param direction Die Richtung, in der sich die Figur bewegen will.
   *
   * @return Wahr, wenn sich die Figur bewegen darf, sonst falsch.
   */
  boolean allowMovement(int row, int col, Direction direction);
}
