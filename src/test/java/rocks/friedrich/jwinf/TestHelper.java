package rocks.friedrich.jwinf;

import rocks.friedrich.jwinf.platform.task.Task;

public class TestHelper {
  public static Task loadTask(String taskId) {
    return Task.loadById("20-DE-13-Kerzen-einfach");
  }
}
