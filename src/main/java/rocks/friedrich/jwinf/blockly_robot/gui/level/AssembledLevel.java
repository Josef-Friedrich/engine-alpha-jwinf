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
    public float x;

    public float y;

    public Grid grid;

    public RobotWrapper robot;

    public Scene scene;

    public Level level;

    public CoordinateSystemTranslator translate;

    public AssembledLevel(Level level, Scene scene, float x, float y)
    {
        this.level = level;
        this.scene = scene;
        this.x = x;
        this.y = y;
        var context = level.getContext();
        translate = new CoordinateSystemTranslator(context.getRows(),
                context.getCols(), x, y);
        for (Item item : context.getBagPacker())
        {
            item.setController(getItemController(item));
        }
    }

    public GraphicalItemController getItemController(Item item)
    {
        return new GraphicalItemController(item, translate, scene);
    }
}
