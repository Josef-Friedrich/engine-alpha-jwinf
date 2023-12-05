package rocks.friedrich.jwinf.blockly_robot.data.model;

import rocks.friedrich.jwinf.blockly_robot.logic.level.Difficulty;

/**
 * Die Daten einer Version einer Trainingsaufgabe.
 *
 * <p>
 * In der JSON-Datei sind die Daten beispielsweise repräsentiert:
 *
 * <pre>{@code
 *   {
 *     "tiles": [
 *       [2, 2, 2, 2, 2, 2, 2],
 *       [2, 2, 2, 2, 3, 2, 2],
 *       [2, 1, 1, 1, 1, 1, 2],
 *       [2, 2, 2, 2, 2, 2, 2]
 *     ],
 *     "initItems": [
 *       {
 *         "row": 2,
 *         "col": 1,
 *         "dir": 0,
 *         "type": "robot"
 *       }
 *     ]
 *   }
 * }</pre>
 */
public class LevelData
{
    public int[][] tiles;

    public ItemData[] initItems;

    public Difficulty difficulty;

    /**
     * 0 no other test 1: first test of x 2: second test of x. x > 2
     */
    public int testNo;

    public int getCols()
    {
        return tiles[0].length;
    }

    public int getRows()
    {
        return tiles.length;
    }

    public ItemData getInitItem()
    {
        return initItems[0];
    }
}
