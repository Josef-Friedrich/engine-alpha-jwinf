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

    public void assertRoute(Difficulty difficulty, int test, String... args)
            throws Exception
    {
        var w = solver.solveVirtual(difficulty, test);
        assertArrayEquals(w.actor.reportRoute(), args,
                "\"" + String.join("\", \"", w.actor.reportRoute()) + "\"");
    }

    public void assertEndPoint(Difficulty difficulty, int test, int row, int col)
            throws Exception
    {
        var w = solver.solveVirtual(difficulty, test);
        Point p = w.actor.getPoint();
        assertEquals(p.row, row);
        assertEquals(p.col, col);
    }
}
