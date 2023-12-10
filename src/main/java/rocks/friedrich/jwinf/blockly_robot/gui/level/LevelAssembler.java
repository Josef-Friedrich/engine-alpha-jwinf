package rocks.friedrich.jwinf.blockly_robot.gui.level;

import ea.Scene;
import ea.Vector;
import rocks.friedrich.jwinf.blockly_robot.gui.Color;
import rocks.friedrich.jwinf.blockly_robot.gui.Painter;
import rocks.friedrich.jwinf.blockly_robot.gui.map.Grid;
import rocks.friedrich.jwinf.blockly_robot.gui.map.ItemMapPainter;
import rocks.friedrich.jwinf.blockly_robot.gui.robot.ImageRobot;
import rocks.friedrich.jwinf.blockly_robot.logic.level.Level;
import rocks.friedrich.jwinf.blockly_robot.logic.robot.RobotWrapper;

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
        var context = level.getContext();
        robot.actor = new ImageRobot("images/robots/robot.png",
                context.getRobot(), l);
        return robot;
    }

    /**
     * @param x - x-Koordinate der linken unteren Ecke
     * @param y - y-Koordinate der linken unteren Ecke
     */
    public AssembledLevel placeActorsInScene(Scene scene, float x, float y)
    {
        AssembledLevel a = new AssembledLevel(level, scene, x, y);
        // Grid
        a.setGrid(createGrid());
        a.getGrid().setPosition(x - SHIFT, y - SHIFT);
        scene.add(a.getGrid());
        // ItemGrid
        new ItemMapPainter(level.getContext()).paint(scene, x - SHIFT,
                y - SHIFT);
        Painter.paintVersionHeading(scene, x, y + level.getRows(), level);
        try
        {
            a.setRobot(createRobot(a));
            Vector robotPosition = a.translate.toVector(level.getInitItem().row,
                    level.getInitItem().col);
            ImageRobot robot = (ImageRobot) a.getRobot().actor;
            robot.setCenter(robotPosition.getX(), robotPosition.getY());
            scene.add(robot);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return a;
    }
}
