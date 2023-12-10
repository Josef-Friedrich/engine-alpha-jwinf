package rocks.friedrich.jwinf.blockly_robot.logic.item.relocation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static rocks.friedrich.jwinf.TestHelper.loadBagPacker;

public class BagPackerTest
{
    BagPacker bagPacker = loadBagPacker("loops_excercises/securing_the_road");

    @Test
    void testGetBagSize()
    {
        assertEquals(bagPacker.getBagSize(), 200);
    }
}
