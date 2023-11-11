package rocks.friedrich.jwinf.engine;

import ea.animation.Interpolator;
import ea.animation.interpolation.EaseInOutFloat;
import rocks.friedrich.jwinf.engine.map.TileMap;
import rocks.friedrich.jwinf.engine.task.Task;
import rocks.friedrich.jwinf.engine.task.Level;

public class State {
  public static int pixelPerMeter = 60;

  public static Interpolator<Float> interpolator = new EaseInOutFloat(0, 1);

  public static int speed = 1;

  /**
   * Die aktuelle Trainingsaufgabe.
   */
  public static Task task;

  /**
   * Der aktuelle Schwierigkeitsgrad.
   */
  public static Level level;

  /**
   * Die aktuelle Figur.
   */
  public static Robot actor;

  /**
   * Der aktuelle Kachelsatz.
   */
  public static TileMap map;

  public static Menu menu = new Menu();

}
