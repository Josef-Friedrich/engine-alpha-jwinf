package rocks.friedrich.jwinf.platform.logic.level;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static rocks.friedrich.jwinf.TestHelper.loadLevel;

class LevelTest
{
    static Level level;

    @BeforeAll
    static void getLevel()
    {
        level = loadLevel("conditionals_excercises/light_all_candles");
    }

    @Test
    void attributeRows()
    {
        assertEquals(level.rows, 6);
    }

    @Test
    void attributeCols()
    {
        assertEquals(level.cols, 9);
    }

    @Test
    void methodGetInitItem()
    {
        assertEquals(level.getInitItem().row, 5);
    }
}
