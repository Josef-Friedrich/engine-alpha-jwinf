package rocks.friedrich.jwinf.platform.gui.map;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ea.Vector;
import rocks.friedrich.jwinf.platform.logic.map.Point;

public class CoordinateSystemTranslatorTest
{
    private CoordinateSystemTranslator translate(int rows, int cols, int x, int y)
    {
        return new CoordinateSystemTranslator(rows, cols, x, y);
    }

    private CoordinateSystemTranslator create(int rows, int cols)
    {
        return translate(rows, cols, 0, 0);
    }

    private void assertPoint(Point point, int row, int col)
    {
        assertEquals(point.row, row);
        assertEquals(point.col, col);
    }

    private void assertVector(Vector vector, int x, int y)
    {
        assertEquals(vector.getX(), x);
        assertEquals(vector.getY(), y);
    }

    @Test
    public void testRowsAndCols()
    {
        var translator = create(4, 5);
        assertEquals(translator.rows, 4);
        assertEquals(translator.cols, 5);
    }

    @Test
    public void testTranslateToPoint()
    {
        Point point = translate(3, 4, 3, 2).toPoint(5, 3);
        assertPoint(point, 1, 2);
        point = translate(3, 4, -3, -2).toPoint(-1, -1);
        assertPoint(point, 1, 2);
        point = translate(3, 4, 5, -5).toPoint(7, -4);
        assertPoint(point, 1, 2);
        point = translate(3, 4, 0, 0).toPoint(2, 1);
        assertPoint(point, 1, 2);
        point = translate(10, 10, -5, -5).toPoint(4, 4);
        assertPoint(point, 0, 9);
    }

    @Test
    public void testTranslateToVector()
    {
        Vector vector = translate(3, 4, 3, 2).toVector(2, 0);
        assertVector(vector, 3, 2);
        vector = translate(3, 4, -3, -2).toVector(2, 0);
        assertVector(vector, -3, -2);
        vector = translate(3, 4, 0, 0).toVector(0, 0);
        assertVector(vector, 0, 2);
    }
}
