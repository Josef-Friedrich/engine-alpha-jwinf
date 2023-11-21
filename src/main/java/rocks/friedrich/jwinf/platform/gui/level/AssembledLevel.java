package rocks.friedrich.jwinf.platform.gui.level;

import ea.Scene;
import ea.actor.TileContainer;
import rocks.friedrich.jwinf.platform.gui.map.Grid;
import rocks.friedrich.jwinf.platform.gui.robot.RobotWrapper;
import rocks.friedrich.jwinf.platform.logic.level.Level;

/**
 * Die Figuren und Hintergründe, die erzeugt wurden, um eine Version einer
 * Trainingsaufgabe zeichnen zu können.
 */
public class AssembledLevel {
  public float x;

  public float y;

  public Grid grid;

  public TileContainer tileMap;

  public RobotWrapper robot;

  public Scene scene;

  public Level level;
}
