package rocks.friedrich.jwinf.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import rocks.friedrich.jwinf.engine.data.JsonLoader;

class JsonLoaderTest {

  @Test
  void loadTask() throws StreamReadException, DatabindException, IOException {
    var task = JsonLoader.loadTask("data/tasks/20-DE-13-Kerzen-einfach.json");
    assertEquals(task.id, "20-DE-13-Kerzen-einfach");
  }

  @Test
  void loadMenu() throws StreamReadException, DatabindException, IOException {
    var menu = JsonLoader.loadMenu();
    assertEquals(menu.get("Bedingte Anweisungen – Übungen").get("Zünde alle Kerzen an"), "20-DE-13-Kerzen-einfach");
  }

}
