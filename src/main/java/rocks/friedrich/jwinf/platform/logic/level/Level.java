package rocks.friedrich.jwinf.platform.logic.level;

import rocks.friedrich.jwinf.platform.data.model.ItemData;
import rocks.friedrich.jwinf.platform.data.model.LevelData;
import rocks.friedrich.jwinf.platform.logic.Task;
import rocks.friedrich.jwinf.platform.logic.context.Context;
import rocks.friedrich.jwinf.platform.logic.robot.VirtualRobot;

/**
 * Ein Test bzw. eine Version einer Trainingsaufgabe in einer bestimmen
 * Schwierigkeit.
 *
 * Eine Trainingsaufgabe kann mehrere Versionen unterschiedlicher
 * Schwierigkeitsgrade haben, z. B. eine Zweistern- (<code>Version**</code>,
 * <em>easy</em>), Dreistern-(<code>Version***</code>, <em>medium</em>), und
 * eine Vierstern-Version (<code>Version****</code>, <em>hard</em>).
 */
public class Level
{
    private LevelData data;

    private Task task;

    private Difficulty difficulty;

    private int testIndex;

    private Context context;

    public Level(LevelData data, Task task)
    {
        this.data = data;
        this.task = task;
        context = createContext();
        difficulty = data.difficulty;
        testIndex = data.testNo;
    }

    public LevelEnvironment createEnvironment()
    {
        var robot = new VirtualRobot(this);
        robot.addDefaultMovementListener();
        robot.setInitPosition(getInitItem());
        return new LevelEnvironment(this, robot);
    }

    private Context createContext()
    {
        return new Context(data.tiles, task.getItemsData());
    }

    public Task getTask()
    {
        return task;
    }

    public Difficulty getDifficulty()
    {
        return difficulty;
    }

    public int getTestIndex()
    {
        return testIndex;
    }

    public Context getContext()
    {
        return context;
    }

    public int getRows()
    {
        return context.getRows();
    }

    public int getCols()
    {
        return context.getCols();
    }

    public ItemData getInitItem()
    {
        return data.getInitItem();
    }

    public String getBorderColor()
    {
        return task.getBorderColor();
    }

    public String getBackgroundColor()
    {
        return task.getBackgroundColor();
    }
}
