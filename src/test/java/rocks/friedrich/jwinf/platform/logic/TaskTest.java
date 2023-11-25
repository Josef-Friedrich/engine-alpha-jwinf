package rocks.friedrich.jwinf.platform.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import rocks.friedrich.jwinf.platform.logic.menu.TaskList;

class TaskTest
{
    Task task = Task.loadByRelPath("conditionals_excercises/light_all_candles");

    @Test
    void attributeTitle()
    {
        assertEquals(task.title, "Kerzen anzünden");
    }

    @Test
    void attributeIntro()
    {
        assertEquals(task.intro, "Programmiere den Roboter:\n"
                + "Der Roboter soll alle Kerzen anzünden.");
    }

    @Test
    void methodGetNumberOfLevels()
    {
        assertEquals(task.getNumberOfLevels(), 3);
    }

    @Test
    void methodGetMaxWidth()
    {
        assertEquals(task.getMaxCols(), 10);
    }

    @Test
    void methodGetMaxHeight()
    {
        assertEquals(task.getMaxRows(), 6);
    }

    @Test
    void all() throws IOException
    {
        TaskList list = TaskList.readFromResources();
        for (String id : list.getRelPaths())
        {
            Task task = Task.loadByRelPath(id);
            assertTrue(task.title != null);
        }
    }
}
