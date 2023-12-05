package rocks.friedrich.jwinf.blockly_robot.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static rocks.friedrich.jwinf.blockly_robot.logic.Compass.*;

import org.junit.jupiter.api.Test;

public class CompassTest
{
    @Test
    public void methodRotateByNumber()
    {
        assertEquals(EAST.rotate(0), EAST);
        assertEquals(EAST.rotate(1), SOUTH);
        assertEquals(EAST.rotate(2), WEST);
        assertEquals(EAST.rotate(3), NORTH);
        assertEquals(EAST.rotate(4), EAST);
    }
}
