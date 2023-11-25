package rocks.friedrich.jwinf.platform;

import rocks.friedrich.jwinf.platform.gui.Controller;
import rocks.friedrich.jwinf.platform.gui.scenes.AllLevelsScene;
import rocks.friedrich.jwinf.platform.gui.scenes.AssembledLevelScene;
import rocks.friedrich.jwinf.platform.gui.scenes.LevelScene;
import rocks.friedrich.jwinf.platform.gui.scenes.WindowScene;
import rocks.friedrich.jwinf.platform.logic.Task;
import rocks.friedrich.jwinf.platform.logic.level.Difficulty;
import rocks.friedrich.jwinf.platform.logic.level.Level;
import rocks.friedrich.jwinf.platform.logic.robot.RobotWrapper;
import rocks.friedrich.jwinf.platform.utils.PackageClassLoader;

/**
 * Klasse, die verschiedene Methoden beinhaltet, die die verschiedenen Versionen
 * einer Trainingsaufgabe löst.
 */
public abstract class Solver<T>
{
    public String taskPath;

    public Solver(String taskPath)
    {
        this.taskPath = taskPath;
    }

    public Solver()
    {
        taskPath = findTaskPathInClassPath();
    }

    public RobotWrapper createRobot(Level level) throws Exception
    {
        RobotWrapper robot = PackageClassLoader
                .instantiateClass("en.tasks.%s.Robot".formatted(taskPath));
        var context = level.createContext();
        robot.actor = context.robot;
        return robot;
    }

    public void easy(T robot)
    {
    }

    public void medium(T robot)
    {
    }

    public void hard(T robot)
    {
    }

    public void all(T robot)
    {
    }

    public void solve()
    {
        solve(null, 0);
    }

    public void solve(String difficutly)
    {
        solve(difficutly, 0);
    }

    @SuppressWarnings("unchecked")
    public void solve(String difficutly, int test)
    {
        AssembledLevelScene scene;
        if (difficutly == null)
        {
            scene = new AllLevelsScene(taskPath);
        } else
        {
            scene = new LevelScene(taskPath, Difficulty.indexOf(difficutly),
                    test);
        }
        Controller.launchScene((WindowScene) scene);
        scene.getAssembledLevels().forEach((level) -> {
            new Thread(() -> {
                switch (level.level.difficulty)
                {
                case EASY:
                    easy((T) level.robot);
                    break;

                case MEDIUM:
                    medium((T) level.robot);
                    break;

                case HARD:
                    hard((T) level.robot);
                    break;

                default:
                    break;
                }
            }).start();
        });
    }

    @SuppressWarnings("unchecked")
    public RobotWrapper solveVirtual(Difficulty difficulty, int test)
            throws Exception
    {
        Task task = Task.loadByRelPath(taskPath);
        Level level = task.getLevel(difficulty, test);
        RobotWrapper robot = createRobot(level);
        switch (difficulty)
        {
        case EASY:
            easy((T) robot);
            break;

        case MEDIUM:
            medium((T) robot);
            break;

        case HARD:
            hard((T) robot);
            break;

        default:
            break;
        }
        return robot;
    }

    private String findTaskPathInClassPath()
    {
        // .getName():
        // rocks.friedrich.jwinf.en.tasks.conditionals_excercises.find_the_way_to_the_lake.TaskSolver
        return Task.extractTaskPath(getClass().getName());
    }
}
