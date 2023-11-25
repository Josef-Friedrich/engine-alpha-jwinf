package rocks.friedrich.jwinf.en.tasks.conditionals_excercises.find_the_way_to_the_lake;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static rocks.friedrich.jwinf.platform.logic.level.Difficulty.EASY;

import org.junit.jupiter.api.Test;

/**
 * https://jwinf.de/task/1158
 */
public class TaskSolverTest
{
    TaskSolver solver = new TaskSolver();

    @Test
    void testTaskPath()
    {
        assertEquals(solver.taskPath,
                "conditionals_excercises/find_the_way_to_the_lake");
    }

    @Test
    void testEasy() throws Exception
    {
        var w = solver.solveVirtual(EASY, 0);
        var point = w.actor.getPoint();
        assertEquals(point.col, 2);
        assertEquals(point.row, 2);
        assertArrayEquals(w.actor.reportRoute(),
                new String[]
                { "forward", "forward", "forward", "forward", "forward",
                        "forward", "turnLeft",
                        //
                        "forward", "forward", "forward", "forward", "forward",
                        "forward", "turnLeft",
                        //
                        "forward", "forward", "forward", "forward", "forward",
                        "turnLeft" });
    }
}
