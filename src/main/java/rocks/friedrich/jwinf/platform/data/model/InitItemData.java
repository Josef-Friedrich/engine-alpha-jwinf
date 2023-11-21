package rocks.friedrich.jwinf.platform.data.model;

import rocks.friedrich.jwinf.platform.logic.CompassDirection;

/**
 * Die Daten eines Anfangsposten.
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
public class InitItemData {
  /**
   * Die Zeilennummer, in der das Element anfangs plaziert ist.
   */
  public int row;

  /**
   * Die Spaltennummer, in der das Element anfangs plaziert ist.
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

  public CompassDirection getCompassDirection() {
    return CompassDirection.fromNo(dir);
  }
}
