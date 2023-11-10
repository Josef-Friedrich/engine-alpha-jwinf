package rocks.friedrich.jwinf.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import rocks.friedrich.jwinf.engine.task.Task;
import rocks.friedrich.jwinf.engine.task.TilesStore;

public class TilesStoreTest {

  TilesStore store;

  private static TilesStore loadByTaskId(String taskId) {
    Task task = Task.loadById(taskId);
    return new TilesStore(task.data.grid.tiles);
  }

  @BeforeEach
  void load() {
    store = loadByTaskId("17-FR-07-platforms-marbles");
  }

  @Test
  public void getTileByNum() {
    assertEquals(store.get(2).name, "wall");
  }

  @Test
  public void getTileByName() {
    assertEquals(store.get("wall").num, 2);
  }

  @Test
  public void getAll() {
    assertEquals(store.all().size(), 3);
  }
}
