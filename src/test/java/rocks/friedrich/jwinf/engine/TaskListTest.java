package rocks.friedrich.jwinf.engine;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskListTest {
  TaskList list;

  @BeforeEach
  public void reset() {
    list.reset();
  }

  public TaskListTest() throws IOException {
    list = new TaskList();
  }

  @Test
  public void methodGet() {
    assertEquals(list.get(0), "17-FR-07-platforms-marbles");
  }

  @Test
  public void methodNext() {
    assertEquals(list.next(), "19-DE-12-stay-on-the-road");
  }

  @Test
  public void methodPrevious() {
    assertEquals(list.previous(), "20-DE-13-Kerzen-einfach");
  }
}
