package rocks.friedrich.jwinf.platform.logic.map;

import ea.Vector;
import rocks.friedrich.jwinf.platform.data.model.ItemData;

/**
 * Die mit Gegenständen (Item) ausgefüllte Karte (Map) einer
 * Trainingsaufgabeversion (Level).
 */
public class LevelMap
{
    private int[][] map;

    private StackedItems[][] stackedItems;

    private ItemDataStore items;

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

    public LevelMap(int[][] map, ItemDataStore items)
    {
        this.map = map;
        rows = map.length;
        cols = map[0].length;
        stackedItems = new StackedItems[rows][cols];
        for (int row = 0; row < rows; row++)
        {
            int[] rowMap = map[row];
            for (int col = 0; col < cols; col++)
            {
                int itemNum = rowMap[col];
                ItemData itemData = items.get(itemNum);
                if (itemData != null)
                {
                    stackedItems[row][col] = new StackedItems(itemData);
                }
                else
                {
                    stackedItems[row][col] = new StackedItems();
                }
            }
        }
        this.items = items;
    }

    public int getRows()
    {
        return rows;
    }

    public int getCols()
    {
        return cols;
    }

    public LevelMap(int[][] map)
    {
        this(map, new ItemDataStore());
    }

    public LevelMap(int rows, int cols)
    {
        this(new int[rows][cols]);
    }

    /**
     * Retrieves the item data by the specified item number.
     *
     * @param itemNum the number of the item data to retrieve
     * @return the item data at the item number
     */
    public ItemData get(int itemNum)
    {
        return items.get(itemNum);
    }

    public ItemData get(int row, int col)
    {
        int num = map[row][col];
        return items.get(num);
    }

    public StackedItems getStacked(int row, int col)
    {
        return stackedItems[row][col];
    }

    public ItemData get(Point point)
    {
        return get(point.row, point.col);
    }

    public Item bottom(int row, int col)
    {
        return stackedItems[row][col].bottom();
    }

    /**
     * Überprüfe, ob die Kachel ein Hindernis darstellt.
     */
    public boolean isObstacle(int row, int col)
    {
        ItemData item = get(row, col);
        if (item != null)
        {
            return item.isObstacle;
        }
        return false;
    }

    /**
     * Überprüfe, ob sich auf einer Kachel ein Gegenstand gefindet, der ein
     * Hindernis darstellt.
     */
    public boolean isObstacleNg(int row, int col)
    {
        return getStacked(row, col).isObstacle();
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
