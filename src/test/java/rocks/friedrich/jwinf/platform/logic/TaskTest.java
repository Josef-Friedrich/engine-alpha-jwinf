package rocks.friedrich.jwinf.platform.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static rocks.friedrich.jwinf.platform.logic.level.Difficulty.EASY;
import static rocks.friedrich.jwinf.platform.logic.level.Difficulty.HARD;
import static rocks.friedrich.jwinf.platform.logic.level.Difficulty.MEDIUM;

import java.io.IOException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import rocks.friedrich.jwinf.platform.logic.menu.TaskList;

class TaskTest
{
    Task task = Task
            .loadByTaskPath("conditionals_excercises/light_all_candles");

    @Test
    void testLoadByRelPath()
    {
        // Assuming that the relative path
        // "conditionals_excercises/light_all_candles" is valid
        final Task loadedTask = Task
                .loadByTaskPath("conditionals_excercises/light_all_candles");
        assertNotNull(loadedTask);
        assertEquals(loadedTask.title, "Kerzen anzünden");
    }

    @Nested
    @DisplayName("static Method extractTaskPath")
    class extractTaskPath
    {
        private void assertPath(String path, String expected)
        {
            assertEquals(Task.extractTaskPath(path), expected);
        }

        @Test
        void testJsonFile()
        {
            assertPath(
                    "/home/xxx/repos/github/jwinf-java/src/main/resources/data/tasks/conditionals_excercises/find_the_way_to_the_lake.json",
                    "conditionals_excercises/find_the_way_to_the_lake");
        }

        @Test
        void testClassFile()
        {
            assertPath(
                    "rocks.friedrich.jwinf.en.tasks.conditionals_excercises.find_the_way_to_the_lake.TaskSolver.class",
                    "conditionals_excercises/find_the_way_to_the_lake");
        }
    }

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
    void testGetNumberOfLevels()
    {
        assertEquals(task.getNumberOfLevels(), 3);
    }

    @Test
    void testGetTaskPath()
    {
        assertEquals(task.getTaskPath(),
                "conditionals_excercises/light_all_candles");
    }

    @Test
    void testGetMaxCols()
    {
        assertEquals(task.getMaxCols(), 10);
    }

    @Test
    void testGetMaxRows()
    {
        assertEquals(task.getMaxRows(), 6);
    }

    @Test
    void all() throws IOException
    {
        final TaskList list = TaskList.readFromResources();
        for (final String id : list.getRelPaths())
        {
            final Task task = Task.loadByTaskPath(id);
            assertTrue(task.title != null);
        }
    }

    @Test
    void testGetBackgroundColor()
    {
        assertEquals(task.getBackgroundColor(), "#c5e2dd");
    }

    @Test
    void testGetGridColor()
    {
        assertEquals(task.getGridColor(), "#b4ccc7");
    }

    @Test
    void testGetLevelIntDifficulty()
    {
        assertEquals(task.getLevel(0).difficulty, EASY);
    }

    @Test
    void testGetLevelEnumDifficulty()
    {
        assertEquals(task.getLevel(MEDIUM).difficulty, MEDIUM);
    }

    @Test
    void testGetLevelDifficultyAndTestIndex()
    {
        assertEquals(task.getLevel(HARD, 0).difficulty, HARD);
    }

    @Test
    void testGetMaxLevelsPerDifficulty()
    {
        // Assuming that the maximum levels per difficulty is 3
        assertEquals(task.getMaxLevelsPerDifficulty(), 1);
    }

    @Test
    void testGetNumberOfDifficulties()
    {
        // Assuming that the number of difficulties is 3 (EASY, MEDIUM, HARD)
        assertEquals(task.getNumberOfDifficulties(), 3);
    }
}
