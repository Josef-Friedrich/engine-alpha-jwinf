package rocks.friedrich.jwinf.platform.logic.level;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static rocks.friedrich.jwinf.TestHelper.loadTask;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class LevelCollectionTest
{
    static LevelCollection levels;

    @BeforeAll
    static void getLevel()
    {
        levels = loadTask("conditionals_excercises/light_all_candles").levels;
    }

    @Test
    void testNumberOfLevels()
    {
        assertEquals(levels.numberOfLevels, 3);
    }

    @Test
    void testMaxCols()
    {
        assertEquals(levels.maxCols, 10);
    }

    @Test
    void testMaxRows()
    {
        assertEquals(levels.maxRows, 6);
    }

    @Test
    void testGetLevel()
    {
        assertEquals(levels.getLevel(Difficulty.EASY, 0).getTestIndex(), 0);
    }

    @Test
    void testGetLevelNoTest()
    {
        assertEquals(levels.getLevel(Difficulty.EASY).getTestIndex(), 0);
    }
}
