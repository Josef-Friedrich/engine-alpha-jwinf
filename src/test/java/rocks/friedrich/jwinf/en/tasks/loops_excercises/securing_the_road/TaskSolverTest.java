package rocks.friedrich.jwinf.en.tasks.loops_excercises.securing_the_road;

import static rocks.friedrich.jwinf.blockly_robot.logic.level.Difficulty.EASY;
import static rocks.friedrich.jwinf.blockly_robot.logic.level.Difficulty.HARD;
import static rocks.friedrich.jwinf.blockly_robot.logic.level.Difficulty.MEDIUM;

import org.junit.jupiter.api.Test;

import rocks.friedrich.jwinf.en.tasks.TaskTester;

public class TaskSolverTest extends TaskTester<Robot>
{
    public TaskSolverTest()
    {
        super(new TaskSolver());
    }

    @Test
    void testEasy() throws Exception
    {
        assertActions(EASY, 0, 1, 0, "forward", "forward",
                "dropWithdrawableFromBag", "forward", "dropWithdrawableFromBag",
                "forward", "dropWithdrawableFromBag", "forward",
                "dropWithdrawableFromBag", "forward", "dropWithdrawableFromBag",
                "forward", "dropWithdrawableFromBag", "forward",
                "dropWithdrawableFromBag", "forward", "dropWithdrawableFromBag",
                "forward");
    }

    @Test
    void testMedium() throws Exception
    {
        assertActions(MEDIUM, 0, 2, 13, "forward", "turnLeft", "forward",
                "dropWithdrawableFromBag", "turnRight", "forward", "turnRight",
                "forward", "turnLeft", "forward", "forward", "turnLeft",
                "forward", "dropWithdrawableFromBag", "turnRight", "forward",
                "turnRight", "forward", "turnLeft", "forward", "forward",
                "turnLeft", "forward", "dropWithdrawableFromBag", "turnRight",
                "forward", "turnRight", "forward", "turnLeft", "forward",
                "forward", "turnLeft", "forward", "dropWithdrawableFromBag",
                "turnRight", "forward", "turnRight", "forward", "turnLeft",
                "forward");
    }

    @Test
    void testHard() throws Exception
    {
        assertActions(HARD, 0, 2, 10, "forward", "forward",
                "dropWithdrawableFromBag", "forward", "dropWithdrawableFromBag",
                "turnLeft", "forward", "dropWithdrawableFromBag", "turnRight",
                "forward", "turnRight", "forward", "turnLeft",
                "dropWithdrawableFromBag", "forward", "dropWithdrawableFromBag",
                "turnLeft", "forward", "dropWithdrawableFromBag", "turnRight",
                "forward", "turnRight", "forward", "turnLeft",
                "dropWithdrawableFromBag", "forward", "dropWithdrawableFromBag",
                "turnLeft", "forward", "dropWithdrawableFromBag", "turnRight",
                "forward", "turnRight", "forward", "turnLeft",
                "dropWithdrawableFromBag", "forward", "dropWithdrawableFromBag",
                "turnLeft", "forward", "dropWithdrawableFromBag", "turnRight",
                "forward", "turnRight", "forward", "turnLeft");
    }
}
