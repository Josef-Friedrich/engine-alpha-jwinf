package rocks.friedrich.jwinf;

import rocks.friedrich.jwinf.platform.logic.Task;
import rocks.friedrich.jwinf.platform.logic.level.Difficulty;
import rocks.friedrich.jwinf.platform.logic.level.Level;
import rocks.friedrich.jwinf.platform.logic.level.LevelContext;
import rocks.friedrich.jwinf.platform.logic.robot.VirtualRobot;

public class TestHelper
{
    public static Task loadTask(String taskId)
    {
        return Task.loadById(taskId);
    }

    public static Level loadLevel(String taskId)
    {
        var task = Task.loadById(taskId);
        return task.getLevel(Difficulty.EASY);
    }

    public static LevelContext loadLevelContext(String taskId)
    {
        return loadLevel(taskId).createContext();
    }

    public static VirtualRobot loadVirtualRobot(String taskId)
    {
        return loadLevelContext(taskId).robot;
    }
}
