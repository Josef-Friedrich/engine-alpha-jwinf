package rocks.friedrich.jwinf.platform.logic.robot;

import rocks.friedrich.jwinf.platform.logic.map.Movement;

public interface Robot {

  /**
   * Gehe nach rechts in Richtung Osten.
   */
  public Movement east();

  /**
   * Gehe nach oben in Richtung Norden.
   */
  public Movement north();

  /**
   * Gehe nach links in Richtung Westen.
   */
  public Movement west();

  /**
   * Gehe nach unten in Richtung Süden.
   */
  public Movement south();

}
