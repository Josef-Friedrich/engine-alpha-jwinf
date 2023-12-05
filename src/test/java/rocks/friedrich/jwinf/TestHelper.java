package rocks.friedrich.jwinf;

import rocks.friedrich.jwinf.platform.logic.Task;
import rocks.friedrich.jwinf.platform.logic.context.Context;
import rocks.friedrich.jwinf.platform.logic.level.Difficulty;
import rocks.friedrich.jwinf.platform.logic.level.Level;
import rocks.friedrich.jwinf.platform.logic.robot.VirtualRobot;

public class TestHelper
{
    public static Task loadTask(String taskPath)
    {
        return Task.loadByTaskPath(taskPath);
    }

    public static Level loadLevel(String taskPath)
    {
        var task = Task.loadByTaskPath(taskPath);
        return task.getLevel(Difficulty.EASY);
    }

    public static Context loadContext(String taskPath)
    {
        return loadLevel(taskPath).getContext();
    }

    public static VirtualRobot loadVirtualRobot(String taskPath)
    {
        return loadContext(taskPath).getRobot();
    }
}
