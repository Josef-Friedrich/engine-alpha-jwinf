package rocks.friedrich.jwinf.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import ea.internal.io.ResourceLoader;

import org.junit.jupiter.api.Test;

import rocks.friedrich.jwinf.engine.task.Task;

class TaskTest {

  Task task = Task.loadById("20-DE-13-Kerzen-einfach");

  private Set<String> listTasks() throws IOException {
    return Stream.of(ResourceLoader.loadAsFile("data/tasks").listFiles())
        .filter(file -> !file.isDirectory() && !file.getName().equals("_template.json"))
        .map(File::getName).map((String fileName) -> fileName.replace(".json", ""))
        .collect(Collectors.toSet());
  }

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
    for (String id : listTasks()) {
      Task task = Task.loadById(id);
      assertTrue(task.title != null);
    }
  }
}
