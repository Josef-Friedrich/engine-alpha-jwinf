package rocks.friedrich.jwinf.blockly_robot.logic.context;

import rocks.friedrich.jwinf.blockly_robot.logic.Task;
import rocks.friedrich.jwinf.blockly_robot.logic.context.item_relocation.BagPacker;
import rocks.friedrich.jwinf.blockly_robot.logic.context.item_relocation.PlatformBuilder;
import rocks.friedrich.jwinf.blockly_robot.logic.item.Item;
import rocks.friedrich.jwinf.blockly_robot.logic.item.ItemCreator;
import rocks.friedrich.jwinf.blockly_robot.logic.item.StackedItems;
import rocks.friedrich.jwinf.blockly_robot.logic.level.Level;
import rocks.friedrich.jwinf.blockly_robot.logic.robot.VirtualRobot;

/**
 * Sammlung aller wichtigen Objekte die zum Lösen einer Trainingsaufgabenversion
 * nötig sind.
 */
public class Context
{
    private StackedItems[][] stackedItems;

    private ItemCreator itemCreator;

    /**
     * Anzahl an Reihen (y-Richtung bzw. Höhe)
     */
    private int rows;

    /**
     * Anzahl an Spalten (x-Richtung bzw. Breite)
     */
    private int cols;

    private VirtualRobot robot;

    private Task task;

    private Level level;

    private BagPacker bagPacker;

    private PlatformBuilder platformBuilder;

    public Context(int[][] map, ItemCreator itemCreator, VirtualRobot robot,
            Task task, Level level)
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
                Item item = itemCreator.create(itemNum);
                if (item != null)
                {
                    stackedItems[row][col] = new StackedItems(item);
                }
                else
                {
                    stackedItems[row][col] = new StackedItems();
                }
            }
        }
        this.itemCreator = itemCreator;
        this.robot = robot;
        this.task = task;
        this.level = level;
        bagPacker = new BagPacker(this);
        platformBuilder = new PlatformBuilder(this);
    }

    public ItemCreator getItemCreator()
    {
        return itemCreator;
    }

    /**
     * Anzahl an Reihen (y-Richtung bzw. Höhe)
     */
    public int getRows()
    {
        return rows;
    }

    /**
     * Anzahl an Spalten (x-Richtung bzw. Breite)
     */
    public int getCols()
    {
        return cols;
    }

    public VirtualRobot getRobot()
    {
        return robot;
    }

    public Task getTask()
    {
        return task;
    }

    public Level getLevel()
    {
        return level;
    }

    public BagPacker getBagPacker()
    {
        return bagPacker;
    }

    public PlatformBuilder getPlatformBuilder()
    {
        return platformBuilder;
    }

    public StackedItems get(int row, int col)
    {
        return stackedItems[row][col];
    }

    public StackedItems get(Coords point)
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

    public boolean isObstacle(Coords coords)
    {
        return isObstacle(coords.getRow(), coords.getCol());
    }

    /**
     * @see <a href=
     *      "https://github.com/France-ioi/bebras-modules/blob/ec1baf055c7f1c383ce8dfa5d27998463ef5be59/pemFioi/blocklyRobot_lib-1.1.js#L2913-L2921">blocklyRobot_lib-1.1.js
     *      L2913-L2921</a>
     */
    public boolean isInGrid(int row, int col)
    {
        return row >= 0 && col >= 0 && row < rows && col < cols;
    }

    public boolean canFall(int row, int col)
    {
        return !isObstacle(row, col) && isInGrid(row, col);
    }
}
