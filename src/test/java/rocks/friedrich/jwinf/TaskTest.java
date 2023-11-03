package rocks.friedrich.jwinf;

import rocks.friedrich.jwinf.engine.TaskNg;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TaskTest {

  TaskNg task = new TaskNg("data/candle.json");

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
