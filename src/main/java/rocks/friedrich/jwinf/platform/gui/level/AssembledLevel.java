package rocks.friedrich.jwinf.platform.gui.level;

import ea.Scene;
import rocks.friedrich.jwinf.platform.gui.map.CoordinateSystemTranslator;
import rocks.friedrich.jwinf.platform.gui.map.Grid;
import rocks.friedrich.jwinf.platform.logic.level.Level;
import rocks.friedrich.jwinf.platform.logic.robot.RobotWrapper;

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
        var map = level.getMap();
        translate = new CoordinateSystemTranslator(map.getRows(), map.getCols(),
                x, y);
    }
}
