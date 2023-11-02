package rocks.friedrich.jwinf.engine;

import ea.animation.Interpolator;
import ea.animation.interpolation.EaseInOutFloat;

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
  public static Actor actor;

  /**
   * Der aktuelle Kachelsatz.
   */
  public static RealTileMap map;

}
