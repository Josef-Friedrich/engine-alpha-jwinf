package rocks.friedrich.jwinf.en.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import rocks.friedrich.jwinf.en.tasks.conditionals_excercises.find_the_destination.Robot;
import rocks.friedrich.jwinf.en.tasks.conditionals_excercises.find_the_destination.TaskSolver;

public class SolverTest
{
    TaskSolver solver = new TaskSolver();

    @Test
    void testinstantiateClass() throws ReflectiveOperationException
    {
        var robot = solver.<Robot>instantiateClass(
                "en.tasks.conditionals_excercises.find_the_destination.Robot");
        assertEquals(robot.getClass().getName(),
                "rocks.friedrich.jwinf.en.tasks.conditionals_excercises.find_the_destination.Robot");
        assertTrue(robot instanceof Robot);
    }
}
