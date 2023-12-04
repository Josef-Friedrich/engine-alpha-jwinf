package rocks.friedrich.jwinf.en.tasks.conditionals_excercises.gems_and_obstacles;

import static rocks.friedrich.jwinf.platform.logic.level.Difficulty.EASY;
import static rocks.friedrich.jwinf.platform.logic.level.Difficulty.HARD;
import static rocks.friedrich.jwinf.platform.logic.level.Difficulty.MEDIUM;

import org.junit.jupiter.api.Test;

import rocks.friedrich.jwinf.en.tasks.TaskTester;

/**
 * https://jwinf.de/task/1161
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
        assertRoute(EASY, 0, 2, 3, "forward", "autoWithdraw", "forward",
                "autoWithdraw", "forward", "autoWithdraw", "forward",
                "autoWithdraw", "forward", "autoWithdraw", "turnLeft",
                "forward", "autoWithdraw", "forward", "autoWithdraw", "forward",
                "autoWithdraw", "forward", "autoWithdraw", "turnLeft",
                "forward", "autoWithdraw", "forward", "autoWithdraw",
                "turnLeft", "forward", "autoWithdraw", "turnLeft");
    }

    @Test
    void testMedium() throws Exception
    {
        assertRoute(MEDIUM, 0, 6, 4, "forward", "autoWithdraw", "forward",
                "autoWithdraw", "forward", "autoWithdraw", "forward",
                "autoWithdraw", "forward", "autoWithdraw", "forward",
                "autoWithdraw", "turnLeft", "forward", "autoWithdraw",
                "forward", "autoWithdraw", "forward", "autoWithdraw", "forward",
                "autoWithdraw", "forward", "autoWithdraw", "turnLeft",
                "turnRight", "turnRight", "forward", "autoWithdraw", "turnLeft",
                "forward", "autoWithdraw", "forward", "autoWithdraw",
                "turnLeft", "forward", "autoWithdraw", "forward",
                "autoWithdraw", "forward", "autoWithdraw", "forward",
                "autoWithdraw", "turnLeft", "forward", "autoWithdraw",
                "forward", "autoWithdraw", "forward", "autoWithdraw", "forward",
                "autoWithdraw", "forward", "autoWithdraw", "turnLeft",
                "forward", "autoWithdraw", "turnLeft", "turnRight",
                "turnRight");
    }

    @Test
    void testHard() throws Exception
    {
        assertRoute(HARD, 0, 8, 3, "turnRight", "turnLeft", "forward",
                "autoWithdraw", "turnRight", "turnLeft", "forward",
                "autoWithdraw", "turnRight", "forward", "autoWithdraw",
                "turnRight", "turnLeft", "turnLeft", "forward", "autoWithdraw",
                "turnRight", "forward", "autoWithdraw", "turnRight", "turnLeft",
                "turnLeft", "forward", "autoWithdraw", "turnRight", "turnLeft",
                "forward", "autoWithdraw", "turnRight", "forward",
                "autoWithdraw", "turnRight", "turnLeft", "turnLeft", "forward",
                "autoWithdraw", "turnRight", "forward", "autoWithdraw",
                "turnRight", "turnLeft", "forward", "autoWithdraw", "turnRight",
                "forward", "autoWithdraw", "turnRight", "turnLeft", "turnLeft",
                "forward", "autoWithdraw", "turnRight", "turnLeft", "turnLeft",
                "forward", "autoWithdraw", "turnRight", "forward",
                "autoWithdraw", "turnRight", "turnLeft", "forward",
                "autoWithdraw", "turnRight", "forward", "autoWithdraw",
                "turnRight", "turnLeft", "turnLeft", "forward", "autoWithdraw",
                "turnRight", "forward", "autoWithdraw", "turnRight", "turnLeft",
                "turnLeft", "forward", "autoWithdraw", "turnRight", "forward",
                "autoWithdraw", "turnRight", "turnLeft", "forward",
                "autoWithdraw", "turnRight", "turnLeft", "forward",
                "autoWithdraw", "turnRight", "forward", "autoWithdraw",
                "turnRight", "turnLeft", "forward", "autoWithdraw", "turnRight",
                "turnLeft", "turnLeft", "turnLeft", "forward", "autoWithdraw",
                "turnRight", "turnLeft", "forward", "autoWithdraw", "turnRight",
                "forward", "autoWithdraw", "turnRight", "turnLeft", "forward",
                "autoWithdraw", "turnRight", "forward", "autoWithdraw",
                "turnRight", "turnLeft", "turnLeft", "forward", "autoWithdraw",
                "turnRight", "forward", "autoWithdraw", "turnRight", "turnLeft",
                "turnLeft", "forward", "autoWithdraw", "turnRight", "forward",
                "autoWithdraw", "turnRight", "turnLeft", "forward",
                "autoWithdraw", "turnRight", "turnLeft", "forward",
                "autoWithdraw", "turnRight", "turnLeft", "forward",
                "autoWithdraw", "turnRight", "turnLeft", "forward",
                "autoWithdraw", "turnRight", "forward", "autoWithdraw",
                "turnRight", "turnLeft", "turnLeft", "forward", "autoWithdraw");
    }
}
