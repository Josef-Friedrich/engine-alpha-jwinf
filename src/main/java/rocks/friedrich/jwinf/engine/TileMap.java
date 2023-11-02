package rocks.friedrich.jwinf.engine;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Ein Kachelsatz (tile map), bei dem die einzelnen Kacheln (tile) durch
 * Buchstaben (letter) repräsentiert sind.
 *
 * Die Größe der Kachel wird auf 1 x 1 Pixelmeter und die linke oberen Ecke
 * an die Position -0.5 x 0.5 im Engine-Alpha-Koordinatensystem gesetzt,
 * sodass zum Beispiel (0,0) die Mitte der ersten Kachel (links oben)
 * adressiert.
 */
abstract class TileMap {
  char[][] letters;

  /**
   * Die Breite des Kachelsatzes, d. h. die Anzahl der Kacheln in der x-Richtung.
   */
  public int width;

  /**
   * Die Höhe des Kachelsatzes, d. h. die Anzahl der Kacheln in der y-Richtung.
   */
  public int height;

  /**
   * Ein Speicher für einprägsamere Namen für eine Kachel als nur der Buchstabe.
   */
  HashMap<Character, String> names;

  HashMap<String, Character> namesToLetter;

  HashSet<Character> obstacles;

  String pathPrefix;

  String extension;

  public TileMap(int width, int height) {
    this(width, height, "", null);
  }

  public TileMap(int width, int height, String pathPrefix) {
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
  public TileMap(int width, int height, String pathPrefix, String extension) {
    this.width = width;
    this.height = height;
    this.pathPrefix = pathPrefix;
    this.extension = extension;
    names = new HashMap<>();
    namesToLetter = new HashMap<>();
    obstacles = new HashSet<>();
    letters = new char[width][height];
  }

  protected String assembleFilePath(String filePath) {
    String extension;
    if (this.extension != null) {
      extension = "." + this.extension;
    } else {
      extension = "";
    }

    if (pathPrefix.length() > 0) {
      char last = pathPrefix.charAt(pathPrefix.length() - 1);
      if (last != '/') {
        pathPrefix = pathPrefix + "/";
      }
    }
    return pathPrefix + filePath + extension;
  }

  protected abstract void createTile(char letter, String filePath);

  protected final void setName(char letter, String name) {
    if (namesToLetter.get(name) != null) {
      throw new IllegalArgumentException(String.format("Eine Kachel mit dem Namen „%s“ existiert bereits!", name));
    }
    namesToLetter.put(name, letter);
    names.put(letter, name);
  }

  protected final void checkLetterUnset(char letter) {
    if (names.get(letter) != null) {
      throw new IllegalArgumentException(
          String.format("Eine Kachel mit dem Buchstaben „%s“ existiert bereits!", letter));
    }
  }

  public final void registerImage(char letter, String filePath, String name) {
    if (name == null) {
      name = filePath;
    }
    setName(letter, name);
    checkLetterUnset(letter);
    createTile(letter, filePath);
  }

  public final void registerImage(char letter, String filePath) {
    registerImage(letter, filePath, null);
  }

  /**
   * @param x Die x-Position im Kachelgitter. 0 adressiert die erste,
   *          (ganz am linken Rand gelegene) Spalte.
   * @param y Die y-Position im Kachelgitter. 0 adressiert die erste,
   *          (oberste) Zeile.
   */
  public final char getLetter(int x, int y) {
    return letters[x][y];
  }

  /**
   * Überprüfe, ob ein Buchstabe, der eine Kachel repräsentiert, bereits
   * registiert ist.
   *
   * @param tile Der Buchstabe, der für ein bestimmtes Kachelbild registiert
   *             wurde.
   */
  protected final boolean existsTile(char tile) {
    if (tile == ' ') {
      return true;
    }
    return names.get(tile) != null;
  }

  /**
   * @throws IllegalArgumentException
   */
  protected final void checkWidth(String row) {
    if (row.length() > width) {
      throw new IllegalArgumentException(
          String.format("Anzahl der Zeichen in einer Reihe (%s) muss kleiner gleich numX (%s) sein!", row,
              width));
    }
  }

  /**
   * @throws IllegalArgumentException
   */
  protected final void checkHeight(String[] rows) {
    if (rows.length > height) {
      throw new IllegalArgumentException(
          String.format("Anzahl der Reihen (%s) muss kleiner gleich numY (%s) sein!", rows.length, height));
    }
  }

  /**
   * @throws IllegalArgumentException
   */
  protected final void checkLetter(char letter) {
    if (!existsTile(letter)) {
      throw new IllegalArgumentException(String.format("Unbekannte Kachel mit dem Buchstaben “%s”!", letter));
    }
  }

  public final void parseMap(String[] rows) {
    checkHeight(rows);
    int currentRow = 0;
    for (String row : rows) {
      setRow(currentRow, row);
      currentRow++;
    }
  }

  /**
   * Setze ein und dieselbe Kachel auf alle Positionen im Kachelgitter.
   *
   * @param tile Der Buchstabe, der für ein bestimmtes Kachelbild registiert
   *             wurde.
   */
  public final void fill(char tile) {
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        setTile(x, y, tile);
      }
    }
  }

  /**
   * @param x    Die x-Position im Kachelgitter. 0 adressiert die erste,
   *             (ganz am linken Rand gelegene) Spalte.
   * @param y    Die y-Position im Kachelgitter. 0 adressiert die erste,
   *             (oberste) Zeile.
   * @param tile Der Buchstabe, der für ein bestimmtes Kachelbild registiert
   *             wurde.
   */
  public abstract void setTile(int x, int y, char tile);

  /**
   * Setzt die Kacheln in einer Zeile von links nach rechts.
   *
   * @param y Die y-Position im Kachelgitter. 0 adressiert die erste,
   *          (oberste) Zeile.
   */
  public final void setRow(int y, String row) {
    checkWidth(row);
    for (int x = 0; x < row.length(); x++) {
      setTile(x, y, row.charAt(x));
    }
  }

  /**
   * Setzt die Kacheln in einer Spalte von oben nach unten.
   *
   * @param x Die x-Position im Kachelgitter. 0 adressiert die erste,
   *          (ganz am linken Rand gelegene) Spalte.
   */
  public final void setColumn(int x, String column) {
    for (int y = 0; y < column.length(); y++) {
      setTile(x, y, column.charAt(y));
    }
  }

  /**
   * Definiert welche Kacheln als Hindernisse gelten.
   *
   * @param obstacles Die Buchstaben der Kacheln, die als Hindernis gelten.
   */
  public final void setObstacles(char... obstacles) {
    for (char c : obstacles) {
      this.obstacles.add(c);
    }
  }

  /**
   * Gibt wahr zurück, wenn die Kachel auf der angegeben Gitterposition ein
   * Hindernis darstellt.
   *
   * @param x Die x-Position im Kachelgitter. 0 adressiert die erste,
   *          (ganz am linken Rand gelegene) Spalte.
   * @param y Die y-Position im Kachelgitter. 0 adressiert die erste,
   *          (oberste) Zeile.
   */
  public final boolean isObstacle(int x, int y) {
    return obstacles.contains(getLetter(x, y));
  }

}
