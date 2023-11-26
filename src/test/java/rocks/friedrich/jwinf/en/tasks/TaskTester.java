package rocks.friedrich.jwinf.en.tasks;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import rocks.friedrich.jwinf.platform.Solver;
import rocks.friedrich.jwinf.platform.logic.level.Difficulty;
import rocks.friedrich.jwinf.platform.logic.map.Point;

public class TaskTester<T>
{
    Solver<T> solver;

    public TaskTester(Solver<T> solver)
    {
        this.solver = solver;
    }

    /**
     * Asserts the route of an actor in a specific difficulty and test case.
     * Compares the actor's reported route with the provided arguments.
     * Also checks if the actor's end position matches the specified row and column.
     *
     * @param difficulty the difficulty level of the test case
     * @param test the test case number
     * @param row the expected row of the actor's end position
     * @param col the expected column of the actor's end position
     * @param args the expected route of the actor
     * @throws Exception if an error occurs during the assertion
     */
    public void assertRoute(Difficulty difficulty, int test, int row, int col,
            String... args) throws Exception
    {
        var w = solver.solveVirtual(difficulty, test);
        assertArrayEquals(w.actor.reportRoute(), args,
                "\"" + String.join("\", \"", w.actor.reportRoute()) + "\"");
        Point p = w.actor.getPoint();
        assertEquals(p.row, row);
        assertEquals(p.col, col);
    }
}
