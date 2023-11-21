package rocks.friedrich.jwinf.platform.data.model;

public class TileData {

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
   * Zum Beispiel: <code>candle</code>
   */
  public String name;

  /**
   * Kachelnummer. Siehe levels.easy.titles. Importiert aus Javascript-Datei.
   */
  public int num;

  /**
   * Dateiname. Zum Beispiel: <code>kerze.png</code>. Importiert aus
   * Javascript-Datei.
   */
  public String img;

  /**
   * Größe in Pixel. Importiert aus Javascript-Datei.
   */
  public int side;

  /**
   * Importiert aus Javascript-Datei.
   */
  public boolean isRobot;

  /**
   * Importiert aus Javascript-Datei.
   */
  public int offsetX;

  /**
   * Importiert aus Javascript-Datei.
   */
  public int offsetY;

  /**
   * Importiert aus Javascript-Datei.
   */
  public int zOrder;

  /**
   * Importiert aus Javascript-Datei.
   */
  public String color;

  /**
   * Importiert aus Javascript-Datei.
   */
  public boolean isObstacle;

  /**
   * Importiert aus Javascript-Datei.
   */
  public boolean isExit;

  /**
   * Importiert aus Javascript-Datei.
   */
  public boolean isContainer;

  /**
   * Importiert aus Javascript-Datei.
   */
  public boolean isPaint;

  /**
   * Importiert aus Javascript-Datei.
   */
  public boolean isWritable;

  /**
   * Gibt an ob eine Kachel eingesammelt werden kann.
   *
   * https://github.com/France-ioi/bebras-modules/blob/ec1baf055c7f1c383ce8dfa5d27998463ef5be59/pemFioi/blocklyRobot_lib-1.1.js#L3001-L3023
   *
   * Importiert aus Javascript-Datei.
   */
  public boolean isWithdrawable;

  public int nbStates;

}
