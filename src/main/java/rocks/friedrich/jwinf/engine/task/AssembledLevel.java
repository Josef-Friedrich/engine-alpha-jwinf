package rocks.friedrich.jwinf.engine.task;

import ea.Scene;
import ea.actor.TileContainer;
import rocks.friedrich.jwinf.engine.Grid;
import rocks.friedrich.jwinf.engine.RobotWrapper;

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
