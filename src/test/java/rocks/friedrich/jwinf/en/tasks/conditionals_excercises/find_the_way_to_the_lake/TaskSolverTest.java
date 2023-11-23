package rocks.friedrich.jwinf.en.tasks.conditionals_excercises.find_the_way_to_the_lake;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskSolverTest {
  @Test
  void testTaskPath() {
    assertEquals(new TaskSolver().taskPath, "conditionals_excercises/find_the_way_to_the_lake");
  }

}
