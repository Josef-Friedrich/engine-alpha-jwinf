package rocks.friedrich.jwinf.platform.logic.item;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rocks.friedrich.jwinf.platform.logic.Task;

public class ItemDataStoreTest
{
    ItemDataStore store;

    private static ItemDataStore loadByTaskId(String taskPath)
    {
        Task task = Task.loadByTaskPath(taskPath);
        return task.getItemsData();
    }

    @BeforeEach
    void load()
    {
        store = loadByTaskId("conditionals_excercises/find_the_destination");
    }

    @Test
    public void testGetTileByNum()
    {
        assertEquals(store.get(2).name, "wall");
    }

    @Test
    public void testGetTileByName()
    {
        assertEquals(store.get("wall").num, 2);
    }

    @Test
    public void testAll()
    {
        assertEquals(store.all().size(), 3);
    }

    @Test
    public void testCreateItemByItemData()
    {
        assertEquals(store.createItem(store.get("wall")).getNum(), 2);
    }

    @Test
    public void testCreateItemByNum()
    {
        assertEquals(store.createItem(2).getNum(), 2);
    }

    @Test
    public void testCreateItemByName()
    {
        assertEquals(store.createItem("wall").getNum(), 2);
    }
}
