package rocks.friedrich.jwinf.en.tasks.conditionals_excercises.platforms;

import static rocks.friedrich.jwinf.blockly_robot.logic.level.Difficulty.EASY;
import static rocks.friedrich.jwinf.blockly_robot.logic.level.Difficulty.HARD;
import static rocks.friedrich.jwinf.blockly_robot.logic.level.Difficulty.MEDIUM;

import org.junit.jupiter.api.Test;

import rocks.friedrich.jwinf.en.tasks.TaskTester;

/**
 * https://jwinf.de/task/1160
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
        assertActions(EASY, 0, 1, 17, "forward", "fall", "withdraw", "forward",
                "fall", "jump", "forward", "fall", "forward", "fall", "forward",
                "fall", "jump", "forward", "fall", "forward", "fall", "forward",
                "fall", "jump", "forward", "fall", "jump", "forward", "fall",
                "forward", "fall", "jump", "forward", "fall", "forward", "fall",
                "forward", "fall", "jump", "forward", "fall", "jump", "forward",
                "fall", "dropWithdrawableFromBag", "forward", "fall");
    }

    @Test
    void testMedium() throws Exception
    {
        assertActions(MEDIUM, 0, 1, 6, "forward", "fall", "withdraw", "forward",
                "fall", "jump", "forward", "fall", "forward", "fall", "forward",
                "fall", "jump", "jump", "forward", "fall", "forward", "fall",
                "forward", "fall", "forward", "fall", "turnAround", "forward",
                "fall", "forward", "fall", "forward", "fall", "forward", "fall",
                "forward", "fall", "forward", "fall", "forward", "fall", "jump",
                "jump", "turnAround", "forward", "fall", "forward", "fall",
                "forward", "fall", "forward", "fall", "forward", "fall",
                "forward", "fall", "forward", "fall", "jump", "jump", "forward",
                "fall", "forward", "fall", "forward", "fall", "forward", "fall",
                "turnAround", "forward", "fall", "forward", "fall", "forward",
                "fall", "forward", "fall", "forward", "fall", "forward", "fall",
                "forward", "fall", "dropWithdrawableFromBag");
    }

    @Test
    void testHard() throws Exception
    {
        assertActions(HARD, 0, 4, 15, "forward", "fall", "forward", "fall",
                "withdraw", "jump", "jump", "jump", "jump", "jump",
                "dropWithdrawableFromBag", "forward", "fall", "backwards",
                "fall", "backwards", "fall", "forward", "fall", "forward",
                "fall", "backwards", "fall", "backwards", "fall", "forward",
                "fall", "forward", "fall", "forward", "fall", "forward", "fall",
                "forward", "fall", "withdraw", "jump", "jump", "jump",
                "dropWithdrawableFromBag", "jump", "jump", "forward", "fall",
                "backwards", "fall", "backwards", "fall", "forward", "fall",
                "forward", "fall", "backwards", "fall", "backwards", "fall",
                "forward", "fall", "forward", "fall", "forward", "fall",
                "forward", "fall", "forward", "fall", "withdraw", "jump",
                "dropWithdrawableFromBag", "jump", "jump", "jump", "jump",
                "forward", "fall", "backwards", "fall", "backwards", "fall",
                "forward", "fall", "forward", "fall", "backwards", "fall",
                "backwards", "fall", "forward", "fall", "forward", "fall",
                "forward", "fall", "forward", "fall", "forward", "fall",
                "withdraw", "jump", "jump", "jump", "jump", "jump",
                "dropWithdrawableFromBag", "forward", "fall", "!backwards",
                "!backwards", "!forward", "!forward", "!backwards",
                "!backwards", "!forward", "!forward");
    }
}
