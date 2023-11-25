package rocks.friedrich.jwinf;

import rocks.friedrich.jwinf.platform.logic.Task;
import rocks.friedrich.jwinf.platform.logic.level.Difficulty;
import rocks.friedrich.jwinf.platform.logic.level.Level;
import rocks.friedrich.jwinf.platform.logic.level.LevelContext;
import rocks.friedrich.jwinf.platform.logic.robot.VirtualRobot;

public class TestHelper
{
    public static Task loadTask(String taskPath)
    {
        return Task.loadByRelPath(taskPath);
    }

    public static Level loadLevel(String taskPath)
    {
        var task = Task.loadByRelPath(taskPath);
        return task.getLevel(Difficulty.EASY);
    }

    public static LevelContext loadLevelContext(String taskPath)
    {
        return loadLevel(taskPath).createContext();
    }

    public static VirtualRobot loadVirtualRobot(String taskPath)
    {
        return loadLevelContext(taskPath).robot;
    }
}
