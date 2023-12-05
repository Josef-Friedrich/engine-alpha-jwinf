package rocks.friedrich.jwinf.platform.logic.context;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ContextTest
{
    private Context create(int rows, int cols)
    {
        return new Context(rows, cols);
    }

    @Test
    public void testRowsAndCols()
    {
        Context context = create(4, 5);
        assertEquals(context.getRows(), 4);
        assertEquals(context.getCols(), 5);
    }
}
