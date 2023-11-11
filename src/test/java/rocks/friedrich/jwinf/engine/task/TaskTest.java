package rocks.friedrich.jwinf.engine.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import rocks.friedrich.jwinf.engine.TaskList;

class TaskTest {

  Task task = Task.loadById("20-DE-13-Kerzen-einfach");

  @Test
  void attributeTitle() {
    assertEquals(task.title, "Kerzen anzünden");
  }

  @Test
  void attributeIntro() {
    assertEquals(task.intro, "Programmiere den Roboter:\n" +
        "Der Roboter soll alle Kerzen anzünden.");
  }

  @Test
  void methodGetNumberOfLevels() {
    assertEquals(task.getNumberOfLevels(), 3);
  }

  @Test
  void methodGetMaxWidth() {
    assertEquals(task.getMaxCols(), 10);
  }

  @Test
  void methodGetMaxHeight() {
    assertEquals(task.getMaxRows(), 6);
  }

  @Test
  void all() throws IOException {
    TaskList list = TaskList.readFromResources();
    for (String id : list.getIds()) {
      Task task = Task.loadById(id);
      assertTrue(task.title != null);
    }
  }
}
