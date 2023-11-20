package rocks.friedrich.jwinf.platform;

import ea.animation.Interpolator;
import ea.animation.interpolation.EaseInOutFloat;
import rocks.friedrich.jwinf.platform.level.Level;
import rocks.friedrich.jwinf.platform.map.TileMap;
import rocks.friedrich.jwinf.platform.robot.Robot;
import rocks.friedrich.jwinf.platform.task.Task;

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
