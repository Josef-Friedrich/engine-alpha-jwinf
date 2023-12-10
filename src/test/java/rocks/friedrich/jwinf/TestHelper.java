package rocks.friedrich.jwinf;

import rocks.friedrich.jwinf.blockly_robot.logic.Task;
import rocks.friedrich.jwinf.blockly_robot.logic.context.Context;
import rocks.friedrich.jwinf.blockly_robot.logic.item.ItemCreator;
import rocks.friedrich.jwinf.blockly_robot.logic.item.relocation.BagPacker;
import rocks.friedrich.jwinf.blockly_robot.logic.level.Difficulty;
import rocks.friedrich.jwinf.blockly_robot.logic.level.Level;
import rocks.friedrich.jwinf.blockly_robot.logic.robot.VirtualRobot;

public class TestHelper
{
    public static Task loadTask(String taskPath)
    {
        return Task.loadByTaskPath(taskPath);
    }

    public static Level loadLevel(String taskPath)
    {
        return loadTask(taskPath).getLevel(Difficulty.EASY);
    }

    public static Context loadContext(String taskPath)
    {
        return loadLevel(taskPath).getContext();
    }

    public static BagPacker loadBagPacker(String taskPath)
    {
        return loadContext(taskPath).getBagPacker();
    }

    public static VirtualRobot loadVirtualRobot(String taskPath)
    {
        return loadContext(taskPath).getRobot();
    }

    public static ItemCreator loadItemCreator(String taskPath)
    {
        return loadTask(taskPath).getItemCreator();
    }
}
