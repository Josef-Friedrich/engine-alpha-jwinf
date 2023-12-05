package rocks.friedrich.jwinf.platform.logic.context;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static rocks.friedrich.jwinf.TestHelper.loadContext;
import static rocks.friedrich.jwinf.platform.logic.level.Difficulty.EASY;

import org.junit.jupiter.api.Test;

public class ContextTest
{
    private Context context = loadContext(
            "conditionals_excercises/gems_and_obstacles");

    @Test
    public void testGetRows()
    {
        assertEquals(context.getRows(), 7);
    }

    @Test
    public void testGetCols()
    {
        assertEquals(context.getCols(), 7);
    }

    @Test
    public void testGetRobot()
    {
        assertEquals(context.getRobot().getRow(), 5);
    }

    @Test
    public void testGetTask()
    {
        assertEquals(context.getTask().getTitle(),
                "Edelsteine und Hindernisse");
    }

    @Test
    public void testGetLevel()
    {
        assertEquals(context.getLevel().getDifficulty(), EASY);
    }
}
