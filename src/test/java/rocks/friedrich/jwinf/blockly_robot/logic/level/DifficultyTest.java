package rocks.friedrich.jwinf.blockly_robot.logic.level;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static rocks.friedrich.jwinf.blockly_robot.logic.level.Difficulty.EASY;
import static rocks.friedrich.jwinf.blockly_robot.logic.level.Difficulty.indexOf;

import org.junit.jupiter.api.Test;

public class DifficultyTest
{
    void assertEasy(Difficulty difficulty)
    {
        assertEquals(difficulty, EASY);
    }

    @Test
    void testIndexOf()
    {
        assertEasy(indexOf(0));
        assertEasy(indexOf("easy"));
        assertEasy(indexOf("EASY"));
        assertEasy(indexOf("**"));
    }
}
