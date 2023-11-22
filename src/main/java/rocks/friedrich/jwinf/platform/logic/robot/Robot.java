package rocks.friedrich.jwinf.platform.logic.robot;

import rocks.friedrich.jwinf.platform.logic.map.Movement;

public interface Robot {

  /**
   * Gehe nach rechts in Richtung Osten.
   *
   * @see <a href=
   *      "https://github.com/France-ioi/bebras-modules/blob/ec1baf055c7f1c383ce8dfa5d27998463ef5be59/pemFioi/blocklyRobot_lib-1.1.js#L3346-L3358">blocklyRobot_lib-1.1.js
   *      L3346-L3358</a>
   */
  public Movement east();

  /**
   * Gehe nach oben in Richtung Norden.
   *
   * @see <a href=
   *      "https://github.com/France-ioi/bebras-modules/blob/ec1baf055c7f1c383ce8dfa5d27998463ef5be59/pemFioi/blocklyRobot_lib-1.1.js#L3318-L3330">blocklyRobot_lib-1.1.js
   *      L3318-L3330</a>
   */
  public Movement north();

  /**
   * Gehe nach links in Richtung Westen.
   *
   * @see <a href=
   *      "https://github.com/France-ioi/bebras-modules/blob/ec1baf055c7f1c383ce8dfa5d27998463ef5be59/pemFioi/blocklyRobot_lib-1.1.js#L3360-L3372">blocklyRobot_lib-1.1.js
   *      L3360-L3372</a>
   */
  public Movement west();

  /**
   * Gehe nach unten in Richtung Süden.
   *
   * @see <a href=
   *      "https://github.com/France-ioi/bebras-modules/blob/ec1baf055c7f1c383ce8dfa5d27998463ef5be59/pemFioi/blocklyRobot_lib-1.1.js#L3332-L3344">blocklyRobot_lib-1.1.js
   *      L3332-L3344</a>
   */
  public Movement south();

}
