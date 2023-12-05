package rocks.friedrich.jwinf.en.tasks.conditionals_excercises.heat_the_castle;

import static rocks.friedrich.jwinf.blockly_robot.logic.level.Difficulty.EASY;
import static rocks.friedrich.jwinf.blockly_robot.logic.level.Difficulty.HARD;
import static rocks.friedrich.jwinf.blockly_robot.logic.level.Difficulty.MEDIUM;

import org.junit.jupiter.api.Test;

import rocks.friedrich.jwinf.en.tasks.TaskTester;

/**
 * https://jwinf.de/task/1159
 */
public class TaskSolverTest extends TaskTester<Robot>
{
    public TaskSolverTest()
    {
        super(new TaskSolver());
    }

    @Test
    void testEasy() throws Exception
    {
        assertActions(EASY, 0, 2, 3, "dropPlatformInFront", "forward", "fall",
                "forward", "fall", "withdraw", "forward", "fall",
                "dropWithdrawable");
    }

    @Test
    void testMedium() throws Exception
    {
        assertActions(MEDIUM, 0, 2, 11, "forward", "fall", "withdraw",
                "dropPlatformInFront", "forward", "fall", "forward", "fall",
                "forward", "fall", "dropPlatformInFront", "forward", "fall",
                "forward", "fall", "dropPlatformInFront", "forward", "fall",
                "dropPlatformInFront", "forward", "fall", "forward", "fall",
                "forward", "fall", "forward", "fall", "dropWithdrawable");
    }

    @Test
    void testHard() throws Exception
    {
        assertActions(HARD, 0, 0, 15, "forward", "fall", "dropPlatformInFront",
                "forward", "fall", "forward", "fall", "turnAround", "forward",
                "fall", "forward", "fall", "forward", "fall", "turnAround",
                "forward", "fall", "forward", "fall", "forward", "fall", "jump",
                "forward", "fall", "dropPlatformInFront", "forward", "fall",
                "forward", "fall", "withdraw", "turnAround", "forward", "fall",
                "forward", "fall", "forward", "fall", "dropWithdrawable",
                "turnAround", "forward", "fall", "forward", "fall", "forward",
                "fall", "jump", "dropPlatformInFront", "forward", "fall",
                "forward", "fall", "forward", "fall", "turnAround", "forward",
                "fall", "forward", "fall", "forward", "fall", "turnAround",
                "forward", "fall", "forward", "fall", "forward", "fall", "jump",
                "forward", "fall", "forward", "fall", "withdraw", "forward",
                "fall", "turnAround", "forward", "fall", "forward", "fall",
                "dropWithdrawable", "forward", "fall", "turnAround", "forward",
                "fall", "forward", "fall", "forward", "fall",
                "dropPlatformAbove", "jump", "forward", "fall", "withdraw",
                "forward", "fall", "forward", "fall", "turnAround", "forward",
                "fall", "dropWithdrawable", "forward", "fall", "forward",
                "fall", "turnAround", "forward", "fall", "forward", "fall",
                "forward", "fall", "dropPlatformAbove", "jump");
    }
}
