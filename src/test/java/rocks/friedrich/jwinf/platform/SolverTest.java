package rocks.friedrich.jwinf.platform;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import rocks.friedrich.jwinf.en.tasks.conditionals_excercises.find_the_way_to_the_lake.TaskSolver;

public class SolverTest
{
    TaskSolver solver = new TaskSolver();

    @Test
    void testTaskPath()
    {
        assertEquals(solver.taskPath,
                "conditionals_excercises/find_the_way_to_the_lake");
    }
}
