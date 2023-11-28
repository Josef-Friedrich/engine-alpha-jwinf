package rocks.friedrich.jwinf.platform.gui.map;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Ein Kachelsatz (tile map), bei dem die einzelnen Kacheln (tile) durch
 * Buchstaben (letter) repräsentiert sind.
 *
 * Die Größe der Kachel wird auf 1 x 1 Pixelmeter und die linke obere Ecke an
 * die Position -0.5 x 0.5 im Engine-Alpha-Koordinatensystem gesetzt, sodass zum
 * Beispiel (0,0) die Mitte der ersten Kachel (links oben) adressiert.
 */
public abstract class Map
{
    /**
     * Die Breite des Kachelsatzes, d. h. die Anzahl der Kacheln in der
     * x-Richtung.
     */
    public int width;

    /**
     * Die Höhe des Kachelsatzes, d. h. die Anzahl der Kacheln in der
     * y-Richtung.
     */
    public int height;

    protected char[][] letterMap;

    /**
     * Um doppelte Buchstaben zu verhindern.
     */
    protected HashSet<Character> letters;

    /**
     * Ein Speicher für einprägsamere Namen für eine Kachel als nur der
     * Buchstabe.
     */
    protected HashMap<Character, String> names;

    protected HashMap<String, Character> namesToLetter;

    protected String pathPrefix;

    protected String extension;

    /**
     * @param width      Die Breite des Kachelsatz bzw. die Anzahl an Kacheln in
     *                   x-Richtung.
     * @param height     Die Höhe des Kachelsatz bzw. die Anzahl an Kacheln in
     *                   y-Richtung.
     * @param pathPrefix
     * @param extension  Die Dateiendung der Bild-Dateien, die als Kacheln
     *                   verwendet werden.
     */
    public Map(int width, int height, String pathPrefix, String extension)
    {
        this.width = width;
        this.height = height;
        letters = new HashSet<>();
        names = new HashMap<>();
        namesToLetter = new HashMap<>();
        letterMap = new char[width][height];
        this.pathPrefix = pathPrefix;
        this.extension = extension;
    }
    protected String assembleFilePath(String filePath)
    {
        String extension;
        if (this.extension != null)
        {
            extension = "." + this.extension;
        }
        else
        {
            extension = "";
        }
        if (!pathPrefix.isEmpty())
        {
            char last = pathPrefix.charAt(pathPrefix.length() - 1);
            if (last != '/')
            {
                pathPrefix = pathPrefix + "/";
            }
        }
        return pathPrefix + filePath + extension;
    }

    protected abstract void createTile(char letter, String filePath);

    protected final void setName(char letter, String name)
    {
        if (namesToLetter.get(name) != null)
        {
            throw new IllegalArgumentException(String.format(
                    "Eine Kachel mit dem Namen „%s“ existiert bereits!", name));
        }
        namesToLetter.put(name, letter);
        names.put(letter, name);
    }

    protected final void checkLetterUnset(char letter)
    {
        if (letters.contains(letter))
        {
            throw new IllegalArgumentException(String.format(
                    "Eine Kachel mit dem Buchstaben „%s“ existiert bereits!",
                    letter));
        }
    }

    public final void registerImage(char letter, String filePath, String name)
    {
        if (name == null)
        {
            name = filePath;
        }
        setName(letter, name);
        checkLetterUnset(letter);
        letters.add(letter);
        createTile(letter, filePath);
    }

    /**
     * @param x Die x-Position im Gitter. 0 adressiert die erste, (ganz am
     *          linken Rand gelegene) Spalte.
     * @param y Die y-Position im Gitter. 0 adressiert die erste,
     *          (oberste) Zeile.
     */
    public final char getLetter(int x, int y)
    {
        return letterMap[x][y];
    }

    /**
     * Überprüfe, ob ein Buchstabe, der eine Kachel repräsentiert, bereits
     * registiert ist.
     *
     * @param tile Der Buchstabe, der für ein bestimmtes Kachelbild registiert
     *             wurde.
     */
    protected final boolean existsTile(char tile)
    {
        if (tile == ' ')
        {
            return true;
        }
        return names.get(tile) != null;
    }

    /**
     * @throws IllegalArgumentException
     */
    protected final void checkLetter(char letter)
    {
        if (!existsTile(letter))
        {
            throw new IllegalArgumentException(String.format(
                    "Unbekannte Kachel mit dem Buchstaben “%s”!", letter));
        }
    }

    /**
     * @param x    Die x-Position im Gitter. 0 adressiert die erste, (ganz
     *             am linken Rand gelegene) Spalte.
     * @param y    Die y-Position im Gitter. 0 adressiert die erste,
     *             (oberste) Zeile.
     * @param tile Der Buchstabe, der für ein bestimmtes Kachelbild registiert
     *             wurde.
     */
    public abstract void setTile(int x, int y, char tile);
}
