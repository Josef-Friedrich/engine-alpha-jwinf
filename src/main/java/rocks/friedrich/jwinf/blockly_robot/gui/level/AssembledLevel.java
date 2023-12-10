package rocks.friedrich.jwinf.blockly_robot.gui.level;

import ea.Scene;
import rocks.friedrich.jwinf.blockly_robot.gui.map.CoordinateSystemTranslator;
import rocks.friedrich.jwinf.blockly_robot.gui.map.GraphicalItemController;
import rocks.friedrich.jwinf.blockly_robot.gui.map.Grid;
import rocks.friedrich.jwinf.blockly_robot.logic.item.Item;
import rocks.friedrich.jwinf.blockly_robot.logic.level.Level;
import rocks.friedrich.jwinf.blockly_robot.logic.robot.RobotWrapper;

/**
 * Die Figuren und Hintergründe, die erzeugt wurden, um eine Version einer
 * Trainingsaufgabe zeichnen zu können.
 */
public class AssembledLevel
{
    private Grid grid;

    private RobotWrapper robot;

    private Scene scene;

    private Level level;

    public CoordinateSystemTranslator translate;

    public AssembledLevel(Level level, Scene scene, float x, float y)
    {
        this.level = level;
        this.scene = scene;
        var context = level.getContext();
        translate = new CoordinateSystemTranslator(context.getRows(),
                context.getCols(), x, y);
        for (Item item : context.getBagPacker())
        {
            item.setController(getItemController(item));
        }
    }

    public Level getLevel()
    {
        return level;
    }

    public Grid getGrid()
    {
        return grid;
    }

    public void setGrid(Grid grid)
    {
        this.grid = grid;
    }

    public RobotWrapper getRobot()
    {
        return robot;
    }

    public void setRobot(RobotWrapper robot)
    {
        this.robot = robot;
    }

    public GraphicalItemController getItemController(Item item)
    {
        return new GraphicalItemController(item, translate, scene);
    }
}
