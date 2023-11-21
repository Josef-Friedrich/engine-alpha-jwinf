package rocks.friedrich.jwinf.platform.logic.robot;

import rocks.friedrich.jwinf.platform.logic.level.LevelMap;

/**
 * Ein Roboter der nicht grafisch dargestellt ist, sondern der sich nur im
 * Speicher befindet. Er kann durch Unit-Tests getestet werden.
 */
public class VirtualRobot {
  LevelMap map;

  /**
   * Die Zeile, in der sich der Roboter aktuell befindet.
   */
  int row;

  /**
   * Die Spalte, in der sich der Roboter aktuell befindet.
   */
  int col;

  public VirtualRobot(LevelMap map) {
    this.map = map;
  }

}
