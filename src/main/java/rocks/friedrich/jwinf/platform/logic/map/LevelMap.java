package rocks.friedrich.jwinf.platform.logic.map;

import rocks.friedrich.jwinf.platform.data.model.ItemData;
import rocks.friedrich.jwinf.platform.logic.item.Item;
import rocks.friedrich.jwinf.platform.logic.item.ItemDataStore;
import rocks.friedrich.jwinf.platform.logic.item.StackedItems;

/**
 * Die mit Gegenständen (Item) ausgefüllte Karte (Map) einer
 * Trainingsaufgabeversion (Level).
 */
public class LevelMap
{
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
     * Die x-Koordinate des linken unteren Ecks, an dem das Gitter im
     * Engine-Alpha-Koordinatensystem verankert ist.
     */
    public int x;

    /**
     * Die y-Koordinate des linken unteren Ecks, an dem das Gitter im
     * Engine-Alpha-Koordinatensystem verankert ist.
     */
    public int y;

    public LevelMap(int[][] map, ItemDataStore items)
    {
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

    public StackedItems get(int row, int col)
    {
        return stackedItems[row][col];
    }

    public StackedItems get(Point point)
    {
        return get(point.row, point.col);
    }

    public Item bottom(int row, int col)
    {
        return stackedItems[row][col].bottom();
    }

    /**
     * Überprüfe, ob sich auf einer Kachel ein Gegenstand gefindet, der ein
     * Hindernis darstellt.
     */
    public boolean isObstacle(int row, int col)
    {
        return get(row, col).isObstacle();
    }
}
