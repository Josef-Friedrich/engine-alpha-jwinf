package rocks.friedrich.jwinf.platform.logic.robot;

import rocks.friedrich.jwinf.platform.logic.CompassDirection;

interface MovementListener {
  /**
   * @param dir Die Richtung, in der sich die Figur bewegen will.
   *
   * @return Wahr, wenn sich die Figur bewegen darf, sonst falsch.
   */
  boolean allowMovement(CompassDirection dir);
}
