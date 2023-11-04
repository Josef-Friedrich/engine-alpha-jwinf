package rocks.friedrich.jwinf;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import ea.internal.io.ResourceLoader;

import org.junit.jupiter.api.Test;

import rocks.friedrich.jwinf.engine.task.Task;

class TaskTest {

  public Set<String> listTasks() throws IOException {
    return Stream.of(ResourceLoader.loadAsFile("data/tasks").listFiles())
        .filter(file -> !file.isDirectory() && file.getName() != "_template.json")
        .map(File::getName)
        .collect(Collectors.toSet());
  }

  Task task = Task.loadById("20-DE-13-Kerzen-einfach");

  @Test
  void title() {
    assertEquals(task.title, "Kerzen anzünden");
  }

  @Test
  void intro() {
    assertEquals(task.intro, "Programmiere den Roboter:\n" +
        "Der Roboter soll alle Kerzen anzünden.");
  }

  @Test
  void all() throws IOException {
    for (String fileName : listTasks()) {
      if (fileName != "_template.json") {
        System.out.println(fileName);
      }
    }
  }

}
