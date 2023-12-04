package rocks.friedrich.jwinf.platform.logic.menu;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskListTest
{
    TaskList list;

    @BeforeEach
    public void reset()
    {
        list.reset();
    }

    public TaskListTest() throws IOException
    {
        list = TaskList.readFromResources();
    }

    @Test
    public void testReadFromResources() throws IOException
    {
        TaskList list = TaskList.readFromResources();
        assertEquals(list.size(), 6);
    }

    @Test
    public void testReadFromMenu() throws IOException
    {
        TaskList list = TaskList.readFromMenu();
        assertEquals(list.size(), 6);
    }

    @Test
    public void testGetId()
    {
        assertEquals(list.getId(0),
                "conditionals_excercises/find_the_destination");
    }

    @Test
    public void testNext()
    {
        assertEquals(list.next(),
                "conditionals_excercises/find_the_way_to_the_lake");
    }

    @Test
    public void testPrevious()
    {
        assertEquals(list.previous(), "conditionals_excercises/platforms");
    }
}
