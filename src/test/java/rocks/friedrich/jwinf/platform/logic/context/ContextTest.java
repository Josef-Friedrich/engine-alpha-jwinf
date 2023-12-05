package rocks.friedrich.jwinf.platform.logic.context;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static rocks.friedrich.jwinf.TestHelper.loadContext;

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
}
