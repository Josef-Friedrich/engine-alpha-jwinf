package rocks.friedrich.jwinf.platform.logic.level;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import rocks.friedrich.jwinf.platform.data.model.LevelCollectionData;
import rocks.friedrich.jwinf.platform.data.model.LevelData;
import rocks.friedrich.jwinf.platform.logic.Task;

/**
 * Die Tests (Level) nach Schwierigkeitsgraden geordnet.
 *
 * Represents a collection of levels categorized by difficulty.
 */
public class LevelCollection
{
    public Task task;

    public Map<Difficulty, List<Level>> levels;

    public List<Level> list;

    public int numberOfLevels;

    public int maxCols;

    public int maxRows;

    private void setMaxRowsAndCols()
    {
        for (Level level : list)
        {
            if (level.getCols() > maxCols)
            {
                maxCols = level.getCols();
            }
            if (level.getRows() > maxRows)
            {
                maxRows = level.getRows();
            }
        }
    }

    public LevelCollection(LevelCollectionData data, Task task)
    {
        this.task = task;
        list = new ArrayList<>();
        levels = new EnumMap<>(Difficulty.class);
        for (LevelData levelData : data.getLevelList())
        {
            Difficulty difficulty = levelData.difficulty;
            List<Level> levelList = levels.get(difficulty);
            if (levelList == null)
            {
                levelList = new ArrayList<Level>();
                levels.put(difficulty, levelList);
            }
            Level level = new Level(levelData, task);
            levelList.add(level);
            list.add(level);
        }
        numberOfLevels = list.size();
        setMaxRowsAndCols();
    }

    /**
     * Retrieves the level for the given difficulty and test index.
     *
     * @param difficulty the difficulty of the level
     * @param test       the test index of the level (0 is the first test)
     * @return the level corresponding to the given difficulty and test index
     */
    public Level getLevel(Difficulty difficulty, int test)
    {
        return levels.get(difficulty).get(test);
    }

    /**
     * Retrieves the level for the specified difficulty.
     *
     * @param difficulty the difficulty of the level to retrieve
     * @return the level for the specified difficulty
     */
    public Level getLevel(Difficulty difficulty)
    {
        return getLevel(difficulty, 0);
    }

    /**
     * Retrieves the level with the specified difficulty.
     *
     * @param difficulty the difficulty level of the level to retrieve
     * @return the level with the specified difficulty
     */
    public Level getLevel(int difficulty)
    {
        return getLevel(Difficulty.indexOf(difficulty), 0);
    }
}
