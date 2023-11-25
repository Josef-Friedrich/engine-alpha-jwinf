package rocks.friedrich.jwinf.platform.logic.level;

import ea.Vector;
import rocks.friedrich.jwinf.platform.data.model.ItemData;
import rocks.friedrich.jwinf.platform.logic.map.Point;
import rocks.friedrich.jwinf.platform.logic.map.ItemStore;

/**
 * Die mit Dingen (Item) ausgefüllte Karte (Map) einer Trainingsaufgabeversion
 * (Level).
 */
public class LevelMap
{
    private int[][] map;

    private ItemStore items;

    /**
     * Anzahl an Reihen (y-Richtung bzw. Höhe)
     */
    public int rows;

    /**
     * Anzahl an Spalten (x-Richtung bzw. Breite)
     */
    public int cols;

    /**
     * Die x-Koordinate des linken unteren Ecks, an dem das Kachelgitter im
     * Engine-Alpha-Koordinatensystem verankert ist.
     */
    public int x;

    /**
     * Die y-Koordinate des linken unteren Ecks, an dem das Kachelgitter im
     * Engine-Alpha-Koordinatensystem verankert ist.
     */
    public int y;

    public LevelMap(int[][] map, ItemStore items)
    {
        this.map = map;
        rows = map.length;
        cols = map[0].length;
        this.items = items;
    }

    public LevelMap(int[][] map)
    {
        this.map = map;
        rows = map.length;
        cols = map[0].length;
        items = new ItemStore();
    }

    public LevelMap(int rows, int cols)
    {
        this(new int[rows][cols]);
    }

    public ItemData get(int row, int col)
    {
        int num = map[row][col];
        return items.get(num);
    }

    public ItemData get(Point point)
    {
        return get(point.row, point.col);
    }

    /**
     * Überprüfe, ob die Kachel ein Hindernis darstellt.
     */
    public boolean isObstacle(int row, int col)
    {
        ItemData tile = get(row, col);
        if (tile != null)
        {
            return tile.isObstacle;
        }
        return false;
    }

    public void setPosition(float x, float y)
    {
        this.x = Math.round(x);
        this.y = Math.round(y);
    }

    /**
     * y-Koordinate des Ursprungs des Kachelgitters (links oben)
     */
    public int row0y()
    {
        return y + rows - 1;
    }

    /**
     * @param vector Ein Punkt im Engine-Alpha-Koordinatensystem.
     */
    public Point translateToPoint(Vector vector)
    {
        int xVector = Math.round(vector.getX());
        int yVector = Math.round(vector.getY());
        return new Point(row0y() - yVector, xVector - x);
    }

    public Point translateToPoint(float x, float y)
    {
        return translateToPoint(new Vector(x, y));
    }

    public Vector translateToVector(Point point)
    {
        return new Vector(x + point.col, row0y() - point.row);
    }

    public Vector translateToVector(int row, int col)
    {
        return translateToVector(new Point(row, col));
    }
}
