package rocks.friedrich.jwinf.platform.data.model;

import rocks.friedrich.jwinf.platform.logic.CompassDirection;

/**
 * Die Daten eines Dings (Item).
 *
 * <p>
 * In der JSON-Datei sind die Daten beispielsweise repräsentiert:
 *
 * <pre>{@code
 *   {
 *     "row": 2,
 *     "col": 1,
 *     "dir": 0,
 *     "type": "robot"
 *   }
 * }</pre>
 */
public class ItemData {

  /**
   * Die Nummer (ID) des Dings.
   */
  public int num;

  /**
   * Ein eindeutiger Name, dar das Ding identifiziert. Zum Beispiel:
   * <code>candle</code>
   */
  public String name;

  /**
   * Die Zeilennummer, in der das Ding plaziert ist.
   */
  public int row;

  /**
   * Die Spaltennummer, in der das Ding plaziert ist.
   */
  public int col;

  /**
   * <code>0</code> steht für Osten, <code>1</code> steht für Süden,
   * <code>2</code> steht für Westen, <code>3</code> steht für Norden.
   *
   * @see rocks.friedrich.jwinf.platform.logic.CompassDirection
   */
  public int dir;

  /**
   * Hat meistens den Wert <code>robot</code>.
   */
  public String type;

  /**
   * Relativer Pfad zu <code>src/main/resources/images</code>. Zum Beispiel:
   * <code>candle/candle.png</code>
   */
  public String relPath;

  /**
   * Zum Beispiel: <code>c</code>
   */
  public char letter;

  /**
   * Dateiname. Zum Beispiel: <code>kerze.png</code>. Importiert aus
   * Javascript-Datei.
   */
  public String img;

  /**
   * Größe in Pixel.
   */
  public int side;

  /**
   *
   */
  public int offsetX;

  /**
   *
   */
  public int offsetY;

  /**
   *
   */
  public int zOrder;

  /**
   *
   */
  public String color;

  /**
   * Gibt an, ob das Ding eine Kiste darstellt.
   */
  public boolean isContainer;

  /**
   *
   */
  public boolean isExit;

  /**
   * Gibt an, ob das Ding einen Laser darstellt.
   */
  public boolean isLaser;

  /**
   * Gibt an, ob das Ding ein Hindernis darstellt.
   */
  public boolean isObstacle;

  /**
   *
   */
  public boolean isPaint;

  /**
   * Gibt an, ob das Ding einen Wurfkörper darstellt.
   */
  public boolean isProjectile;

  public boolean isPushable;

  /**
   * Gibt an, ob das Ding einen Roboter darstellt.
   */
  public boolean isRobot;

  /**
   * Gibt an, ob das Ding eingesammelt werden kann.
   *
   * https://github.com/France-ioi/bebras-modules/blob/ec1baf055c7f1c383ce8dfa5d27998463ef5be59/pemFioi/blocklyRobot_lib-1.1.js#L3001-L3023
   */
  public boolean isWithdrawable;

  /**
   * Gibt an, ob das Ding eine Tafel darstellt.
   */
  public boolean isWritable;

  public int nbStates;

  public CompassDirection getCompassDirection() {
    return CompassDirection.fromNo(dir);
  }

}
