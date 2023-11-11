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
    list = TaskList.readFromResources();
  }

  @Test
  public void methodReadFromResources() throws IOException {
    TaskList list = TaskList.readFromResources();
    assertEquals(list.size(), 4);
  }

  @Test
  public void methodReadFromMenu() throws IOException {
    TaskList list = TaskList.readFromMenu();
    assertEquals(list.size(), 4);
  }

  @Test
  public void methodGet() {
    assertEquals(list.getId(0), "17-FR-07-platforms-marbles");
  }

  @Test
  public void methodNext() {
    assertEquals(list.next(), "19-DE-12-stay-on-the-road");
  }

  @Test
  public void methodPrevious() {
    assertEquals(list.previous(), "castor-informatique.fr__questions__algorea_training_2__01_sequence_02_gems");
  }
}
