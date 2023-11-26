package rocks.friedrich.jwinf.en.tasks.conditionals_excercises.find_the_destination;

import static rocks.friedrich.jwinf.platform.logic.level.Difficulty.EASY;
import static rocks.friedrich.jwinf.platform.logic.level.Difficulty.HARD;
import static rocks.friedrich.jwinf.platform.logic.level.Difficulty.MEDIUM;

import org.junit.jupiter.api.Test;

import rocks.friedrich.jwinf.en.tasks.TaskTester;

/**
 * https://jwinf.de/task/1157
 */
public class TaskSolverTest extends TaskTester<Robot>
{
    public TaskSolverTest()
    {
        super(new TaskSolver());
    }

    @Test
    void testEasy0() throws Exception
    {
        assertRoute(EASY, 0, 1, 4, "turnLeft", "turnRight", "forward",
                "turnLeft", "turnRight", "forward", "turnLeft", "turnRight",
                "forward", "turnLeft", "forward");
    }

    @Test
    void testEasy1() throws Exception
    {
        assertRoute(EASY, 1, 1, 6, "turnLeft", "turnRight", "forward",
                "turnLeft", "turnRight", "forward", "turnLeft", "turnRight",
                "forward", "turnLeft", "turnRight", "forward", "turnLeft",
                "turnRight", "forward", "turnLeft", "forward");
    }

    @Test
    void testMedium() throws Exception
    {
        assertRoute(MEDIUM, 0, 3, 4, "turnLeft", "turnRight", "turnRight",
                "turnLeft", "forward", "turnLeft", "turnRight", "turnRight",
                "turnLeft", "forward", "turnLeft", "turnRight", "turnRight",
                "turnLeft", "forward", "turnLeft", "turnRight", "turnRight",
                "forward");
    }

    @Test
    void testHard() throws Exception
    {
        assertRoute(HARD, 0, 3, 4, "turnRight", "turnLeft", "forward",
                "turnRight", "forward", "turnRight", "turnLeft", "turnLeft",
                "turnRight", "turnLeft", "turnLeft", "turnRight", "turnLeft",
                "forward", "turnRight", "forward", "turnRight", "turnLeft",
                "forward", "turnRight", "forward");
    }
}
