package rocks.friedrich.jwinf.platform.gui.scenes;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import ea.Scene;
import ea.event.KeyListener;
import ea.internal.Bounds;
import rocks.friedrich.jwinf.platform.gui.Controller;
import rocks.friedrich.jwinf.platform.gui.level.AssembledLevel;
import rocks.friedrich.jwinf.platform.gui.level.LevelAssembler;
import rocks.friedrich.jwinf.platform.logic.Task;
import rocks.friedrich.jwinf.platform.logic.menu.TaskList;

public class AllLevelsScene extends Scene
        implements WindowScene, KeyListener, AssembledLevelScene
{
    static TaskList taskList = TaskList.readFromMenu();

    private final ArrayList<AssembledLevel> assembledLevels = new ArrayList<>();

    public Task task;

    private final float INITIAL_X = 0;

    private final float INITIAL_Y = 0;

    /**
     * Abstand zwischen den Tests.
     */
    private final float MARGIN = 1f;

    /**
     * aktuelle x-Position
     */
    private float x = 0;

    /**
     * aktuelle y-Position
     */
    private float y = 0;

    public AllLevelsScene(Task task)
    {
        this.task = task;
        paintLevels();
    }

    public AllLevelsScene(String taskPath)
    {
        this(Task.loadByTaskPath(taskPath));
    }

    public float getWidth()
    {
        int numDiff = task.getNumberOfDifficulties();
        return (task.getMaxCols() * numDiff) + (MARGIN * numDiff - 1);
    }

    public float getHeight()
    {
        int numLevels = task.getMaxLevelsPerDifficulty();
        return (task.getMaxRows() * numLevels) + (MARGIN * numLevels - 1);
    }

    public String getTitle()
    {
        return task.title;
    }

    public Bounds getWindowBounds()
    {
        return new Bounds(INITIAL_X - 0.5f,
                INITIAL_Y - getHeight() + task.getMaxRows() - 0.5f, getWidth(),
                getHeight());
    }

    public List<AssembledLevel> getAssembledLevels()
    {
        return assembledLevels;
    }

    public void paintLevels()
    {
        x = INITIAL_X;
        task.getLevels().forEach((difficulty, levels) -> {
            y = INITIAL_Y;
            levels.forEach((level) -> {
                var assembler = new LevelAssembler(level);
                assembledLevels.add(assembler.placeActorsInScene(this, x, y));
                y -= task.getMaxRows() + 1;
            });
            x += task.getMaxCols() + 1;
        });
    }

    public static void launch(String taskPath)
    {
        var scene = new AllLevelsScene(taskPath);
        Controller.launchScene((WindowScene) scene);
    }

    @Override
    public void onKeyDown(KeyEvent e)
    {
        switch (e.getKeyCode())
        {
        // n = next
        case KeyEvent.VK_N:
            launch(taskList.next());
            break;

        // p = previous
        case KeyEvent.VK_P:
            launch(taskList.previous());
            break;
        }
    }

    public static void main(String[] args)
    {
        // launch("conditionals_excercises/light_all_candles");
        launch("conditionals_excercises/find_the_destination");
    }
}
