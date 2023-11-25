package rocks.friedrich.jwinf.platform.logic.map;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rocks.friedrich.jwinf.platform.logic.Task;

public class TilesStoreTest
{
    ItemStore store;

    private static ItemStore loadByTaskId(String taskPath)
    {
        Task task = Task.loadByRelPath(taskPath);
        return new ItemStore(task.data.grid.itemTypes);
    }

    @BeforeEach
    void load()
    {
        store = loadByTaskId("conditionals_excercises/find_the_destination");
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
