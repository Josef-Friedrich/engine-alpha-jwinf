package rocks.friedrich.jwinf.platform.logic.map;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rocks.friedrich.jwinf.platform.logic.Task;

public class TilesStoreTest
{
    ItemStore store;

    private static ItemStore loadByTaskId(String taskId)
    {
        Task task = Task.loadById(taskId);
        return new ItemStore(task.data.grid.itemTypes);
    }

    @BeforeEach
    void load()
    {
        store = loadByTaskId("17-FR-07-platforms-marbles");
    }

    @Test
    public void getTileByNum()
    {
        assertEquals(store.get(2).name, "wall");
    }

    @Test
    public void getTileByName()
    {
        assertEquals(store.get("wall").num, 2);
    }

    @Test
    public void getAll()
    {
        assertEquals(store.all().size(), 3);
    }
}
