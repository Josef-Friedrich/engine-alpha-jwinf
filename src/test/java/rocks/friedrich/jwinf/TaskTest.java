package rocks.friedrich.jwinf;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import rocks.friedrich.jwinf.engine.task.Task;

class TaskTest {

  Task task = new Task("data/candle.json");

  @Test
  void title() {
    assertEquals(task.title, "Kerzen anzünden");
  }

  @Test
  void intro() {
    assertEquals(task.intro, "Programmiere den Roboter:\n" +
        "Der Roboter soll alle Kerzen anzünden.");
  }

}
