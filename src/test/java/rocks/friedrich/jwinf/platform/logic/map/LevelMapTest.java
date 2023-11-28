package rocks.friedrich.jwinf.platform.logic.map;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LevelMapTest
{
    private LevelMap create(int rows, int cols)
    {
        return new LevelMap(rows, cols);
    }

    @Test
    public void testRowsAndCols()
    {
        LevelMap map = create(4, 5);
        assertEquals(map.rows, 4);
        assertEquals(map.cols, 5);
    }
}
