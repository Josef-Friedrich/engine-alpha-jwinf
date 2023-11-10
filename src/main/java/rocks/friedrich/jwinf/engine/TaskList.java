package rocks.friedrich.jwinf.engine;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import ea.internal.io.ResourceLoader;

public class TaskList {

  private List<String> ids;

  private int current = 0;

  public TaskList() throws IOException {
    ids = listTaskIds();
    Collections.sort(ids);
  }

  private List<String> listTaskIds() throws IOException {
    return Stream.of(ResourceLoader.loadAsFile("data/tasks").listFiles())
        .filter(file -> !file.isDirectory() && !file.getName().equals("_template.json"))
        .map(File::getName).map((String fileName) -> fileName.replace(".json", ""))
        .collect(Collectors.toList());
  }

  public String get(int index) {
    return ids.get(current);
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
