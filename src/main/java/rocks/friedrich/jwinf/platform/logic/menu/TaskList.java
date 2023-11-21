package rocks.friedrich.jwinf.platform.logic.menu;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import ea.internal.io.ResourceLoader;
import rocks.friedrich.jwinf.platform.logic.Task;

public class TaskList {

  private List<String> ids;

  private int current = 0;

  public TaskList(List<String> ids) {
    this.ids = ids;
  }

  public static TaskList readFromResources() throws IOException {
    List<String> ids = Stream.of(ResourceLoader.loadAsFile("data/tasks").listFiles())
        .filter(file -> !file.isDirectory() && !file.getName().equals("_template.json"))
        .map(File::getName).map((String fileName) -> fileName.replace(".json", ""))
        .collect(Collectors.toList());
    Collections.sort(ids);
    return new TaskList(ids);
  }

  public static TaskList readFromMenu() {
    Menu menu = new Menu();
    List<String> ids = new ArrayList<>();
    menu.getMain().forEach((main, sub) -> {
      sub.forEach((subMenu, taskId) -> {
        if (taskId != null) {
          ids.add(taskId);
        }
      });
    });
    return new TaskList(ids);
  }

  public int size() {
    return ids.size();
  }

  public List<String> getIds() {
    return ids;
  }

  public String getId(int index) {
    return ids.get(current);
  }

  public Task get(int index) {
    return Task.loadById(getId(index));
  }

  public void reset() {
    current = 0;
  }

  public String previous() {
    if (current == 0) {
      current = ids.size() - 1;
    } else {
      current--;
    }
    return ids.get(current);
  }

  public String next() {
    if (current == ids.size() - 1) {
      current = 0;
    } else {
      current++;
    }
    return ids.get(current);
  }
}
