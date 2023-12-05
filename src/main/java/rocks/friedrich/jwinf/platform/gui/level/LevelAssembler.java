package rocks.friedrich.jwinf.platform.gui.level;

import ea.Scene;
import ea.Vector;
import rocks.friedrich.jwinf.platform.gui.Color;
import rocks.friedrich.jwinf.platform.gui.map.Grid;
import rocks.friedrich.jwinf.platform.gui.map.ItemMapPainter;
import rocks.friedrich.jwinf.platform.gui.robot.ImageRobot;
import rocks.friedrich.jwinf.platform.logic.level.Level;
import rocks.friedrich.jwinf.platform.logic.robot.RobotWrapper;

/**
 * Klasse, die eine Version einer Trainingsaufgabe zusammenbaut.
 */
public class LevelAssembler
{
    public static final float SHIFT = 0.5f;

    Level level;

    public LevelAssembler(Level level)
    {
        this.level = level;
    }

    public Grid createGrid()
    {
        Grid grid = new Grid(level.getCols(), level.getRows());
        grid.setColor(new Color(level.getBorderColor()));
        grid.setBackground(new Color(level.getTask().getBackgroundColor()));
        return grid;
    }

    public RobotWrapper createRobot(AssembledLevel l) throws Exception
    {
        String className = "rocks.friedrich.jwinf.en.tasks.%s.Robot"
                .formatted(level.getTask().getTaskPath().replace("/", "."));
        RobotWrapper robot = RobotWrapper.class.getClassLoader()
                .loadClass(className).asSubclass(RobotWrapper.class)
                .getDeclaredConstructor().newInstance();
        var environment = level.createEnvironment();
        robot.actor = new ImageRobot("images/candle/robot.png",
                environment.getRobot(), l);
        return robot;
    }

    /**
     * @param x - x-Koordinate der linken unteren Ecke
     * @param y - y-Koordinate der linken unteren Ecke
     */
    public AssembledLevel placeActorsInScene(Scene scene, float x, float y)
    {
        AssembledLevel l = new AssembledLevel(level, scene, x, y);
        // Grid
        l.grid = createGrid();
        l.grid.setPosition(x - SHIFT, y - SHIFT);
        scene.add(l.grid);
        // ItemGrid
        new ItemMapPainter(level.getContext()).paint(scene, x - SHIFT,
                y - SHIFT);
        try
        {
            l.robot = createRobot(l);
            Vector robotPosition = l.translate.toVector(level.getInitItem().row,
                    level.getInitItem().col);
            ImageRobot robot = (ImageRobot) l.robot.actor;
            robot.setCenter(robotPosition.getX(), robotPosition.getY());
            scene.add(robot);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return l;
    }
}
