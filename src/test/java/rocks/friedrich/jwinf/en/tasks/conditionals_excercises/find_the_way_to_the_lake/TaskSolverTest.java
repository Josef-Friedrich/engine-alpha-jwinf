package rocks.friedrich.jwinf.en.tasks.conditionals_excercises.find_the_way_to_the_lake;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import static rocks.friedrich.jwinf.platform.logic.level.Difficulty.*;

import java.lang.reflect.InvocationTargetException;

public class TaskSolverTest {

  TaskSolver solver = new TaskSolver();

  @Test
  void testTaskPath() {
    assertEquals(solver.taskPath, "conditionals_excercises/find_the_way_to_the_lake");
  }

  @Test
  void testEasy() throws InstantiationException, IllegalAccessException, IllegalArgumentException,
      InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
    var robot = solver.solveVirtual(EASY, 0);
    var point = robot.actor.getPoint();
    assertEquals(point.col, 2);
    assertEquals(point.row, 2);
  }

}
