package rocks.friedrich.jwinf.blockly_robot.logic.level;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static rocks.friedrich.jwinf.TestHelper.loadLevel;
import static rocks.friedrich.jwinf.blockly_robot.logic.level.Difficulty.EASY;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class LevelTest
{
    static Level level;

    @BeforeAll
    static void getLevel()
    {
        level = loadLevel("conditionals_excercises/light_all_candles");
    }

    @Test
    void testGetTask()
    {
        assertEquals(level.getTask().getTitle(), "Kerzen anzünden");
    }

    @Test
    void testGetDifficulty()
    {
        assertEquals(level.getDifficulty(), EASY);
    }

    @Test
    void testGetTestIndex()
    {
        assertEquals(level.getTestIndex(), 0);
    }

    @Test
    void testGetContext()
    {
        assertEquals(level.getContext().getCols(), 9);
    }

    @Test
    void testGetRows()
    {
        assertEquals(level.getRows(), 6);
    }

    @Test
    void testGetCols()
    {
        assertEquals(level.getCols(), 9);
    }

    @Test
    void testGetInitItem()
    {
        assertEquals(level.getInitItem().row, 5);
    }

    @Test
    void testGetBorderColor()
    {
        assertEquals(level.getBorderColor(), "#b4ccc7");
    }

    @Test
    void testGetBackgroundColor()
    {
        assertEquals(level.getBackgroundColor(), "#c5e2dd");
    }
}
