package rocks.friedrich.jwinf.blockly_robot.logic.item;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static rocks.friedrich.jwinf.TestHelper.loadItemCreator;

import org.junit.jupiter.api.Test;


public class ItemCreatorTest
{
    ItemCreator items = loadItemCreator(
            "conditionals_excercises/find_the_destination");

    @Test
    public void testCreateItemByNum()
    {
        assertEquals(items.create(2).getNum(), 2);
    }

    @Test
    public void testCreateItemByName()
    {
        assertEquals(items.create("wall").getNum(), 2);
    }
}
