package rocks.friedrich.jwinf.en.tasks.conditionals_excercises.find_the_way_to_the_lake;

import static rocks.friedrich.jwinf.blockly_robot.logic.level.Difficulty.EASY;
import static rocks.friedrich.jwinf.blockly_robot.logic.level.Difficulty.HARD;
import static rocks.friedrich.jwinf.blockly_robot.logic.level.Difficulty.MEDIUM;

import org.junit.jupiter.api.Test;

import rocks.friedrich.jwinf.en.tasks.TaskTester;

/**
 * https://jwinf.de/task/1158
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
        assertRoute(EASY, 0, 2, 2, "forward", "forward", "forward", "forward",
                "forward", "forward", "turnLeft",
                //
                "forward", "forward", "forward", "forward", "forward",
                "forward", "turnLeft",
                //
                "forward", "forward", "forward", "forward", "forward",
                "turnLeft");
    }

    @Test
    void testMedium() throws Exception
    {
        assertRoute(MEDIUM, 0, 2, 2, "forward", "turnRight", "turnLeft",
                "turnLeft", "forward", "forward", "forward", "turnRight",
                "forward", "forward", "forward", "forward", "forward",
                "turnRight", "turnLeft", "turnLeft", "forward", "forward",
                "forward", "turnRight", "turnLeft", "turnLeft", "forward",
                "forward", "forward", "forward", "forward", "turnRight",
                "turnLeft", "turnLeft");
    }

    @Test
    void testHard() throws Exception
    {
        assertRoute(HARD, 0, 2, 2, "forward", "turnRight", "turnLeft",
                "turnLeft", "forward", "turnLeft", "turnRight", "forward",
                "turnLeft", "turnRight", "forward", "turnRight", "forward",
                "turnLeft", "turnRight", "forward", "turnLeft", "turnRight",
                "forward", "turnRight", "forward", "turnLeft", "turnRight",
                "forward", "turnRight", "turnLeft", "turnLeft", "forward",
                "turnLeft", "turnRight", "forward", "turnLeft", "forward",
                "forward", "turnLeft", "turnRight", "forward", "turnLeft",
                "turnRight", "forward", "turnLeft", "turnRight", "forward",
                "turnLeft", "forward", "forward", "turnLeft", "turnRight",
                "forward", "turnLeft", "turnRight", "forward", "turnLeft",
                "turnRight", "forward", "turnRight", "turnLeft", "turnLeft");
    }
}
