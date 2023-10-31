package rocks.friedrich.jwinf.engine;

/**
 * Eine Trainingsaufgabe (Task) besteht aus mehreren Schwierigkeitsgraden (Level)
 */
public class Task {

  /**
   * Zum Beispiel „Edelsteine einsammeln“
   */
  public String title;

  /**
   * Zum Beispiel „Programmiere den Roboter“
   */
  public String intro;

  /**
   * Die aktuelle Figur.
   */
  public static Actor actor;

  /**
   * Der aktuelle Kachelsatz.
   */
  public static TileMap map;

  public static int pixelPerMeter = 60;
}
