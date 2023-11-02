package rocks.friedrich.jwinf.engine;

import java.util.HashMap;

import ea.actor.Tile;
import ea.actor.TileContainer;

/**
 * Ein Kachelsatz (tile map), bei dem die einzelnen Kacheln (tile) durch
 * Buchstaben (letter) repräsentiert sind.
 *
 * Die Größe der Kachel wird auf 1 x 1 Pixelmeter und die linke oberen Ecke
 * an die Position -0.5 x 0.5 im Engine-Alpha-Koordinatensystem gesetzt,
 * sodass zum Beispiel (0,0) die Mitte der ersten Kachel (links oben)
 * adressiert.
 */
public class RealTileMap extends TileMap {

  HashMap<Character, Tile> tiles;

  public TileContainer container;

  public RealTileMap(int width, int height) {
    this(width, height, "", null);
  }

  public RealTileMap(int width, int height, String pathPrefix) {
    this(width, height, pathPrefix, null);
  }

  /**
   * @param width      Die Breite des Kachelsatz bzw. die Anzahl an Kacheln in
   *                   x-Richtung.
   * @param height     Die Höhe des Kachelsatz bzw. die Anzahl an Kacheln in
   *                   y-Richtung.
   * @param pathPrefix
   * @param extension  Die Dateiendung der Bild-Dateien, die als Kacheln verwendet
   *                   werden.
   */
  public RealTileMap(int width, int height, String pathPrefix, String extension) {
    super(width, height, pathPrefix, extension);
    tiles = new HashMap<>();

    container = new TileContainer(width, height, 1, 1);
    container.setPosition(-0.5f, -height + 0.5f);
  }

  protected void createTile(char letter, String filePath) {
    tiles.put(letter, ea.actor.TileMap.createFromImage(assembleFilePath(filePath)));
  }

    /**
   * @param x Die x-Position im Kachelgitter. 0 adressiert die erste,
   *          (ganz am linken Rand gelegene) Spalte.
   * @param y Die y-Position im Kachelgitter. 0 adressiert die erste,
   *          (oberste) Zeile.
   */
  protected Tile getTileFromCache(int x, int y) {
    return tiles.get(getLetter(x, y));
  }

  /**
   * @param x    Die x-Position im Kachelgitter. 0 adressiert die erste,
   *             (ganz am linken Rand gelegene) Spalte.
   * @param y    Die y-Position im Kachelgitter. 0 adressiert die erste,
   *             (oberste) Zeile.
   * @param tile Der Buchstabe, der für ein bestimmtes Kachelbild registiert
   *             wurde.
   */
  public void setTile(int x, int y, char tile) {
    checkLetter(tile);
    letters[x][y] = tile;
    container.setTile(x, y, getTileFromCache(x, y));
  }

}
