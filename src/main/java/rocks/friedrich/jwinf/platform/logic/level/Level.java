package rocks.friedrich.jwinf.platform.logic.level;

import rocks.friedrich.jwinf.platform.data.model.ItemData;
import rocks.friedrich.jwinf.platform.data.model.LevelData;
import rocks.friedrich.jwinf.platform.logic.Task;
import rocks.friedrich.jwinf.platform.logic.map.Context;
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
    public LevelData data;

    public Task task;

    public Difficulty difficulty;

    public int testIndex;

    /**
     * Zum Beispiel „Der Roboter soll den Edelstein einsammeln. Sobald er das
     * Feld mit dem Edelstein erreicht, wird dieser automatisch eingesammelt.“
     */
    public String intro;

    public int cols;

    public int rows;

    public Context context;

    public Level(LevelData data, Task task)
    {
        this.data = data;
        this.task = task;
        context = new Context(data.tiles, task.getItemsData());
        cols = context.getCols();
        rows = context.getRows();
        difficulty = data.difficulty;
        testIndex = data.testNo;
    }

    public LevelContext createContext()
    {
        var c = new LevelContext();
        c.level = this;
        c.robot = new VirtualRobot(this);
        c.robot.addDefaultMovementListener();
        c.robot.setInitPosition(getInitItem());
        return c;
    }

    public Context getContext()
    {
        return context;
    }

    public ItemData getInitItem()
    {
        return data.getInitItem();
    }

    public String getGridColor()
    {
        return task.getGridColor();
    }

    public String getBackgroundColor()
    {
        return task.getBackgroundColor();
    }
}
