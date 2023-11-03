package rocks.friedrich.jwinf.engine;

/**
 * Eine Trainingsaufgabe (Task) besteht aus mehreren Schwierigkeitsgraden
 * (Level)
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

  public Level[] levels = new Level[3];

  public void launchLevelByDifficulty(Difficulty difficulty) {
    Controller.launchLevel(levels[difficulty.getIndex()]);
  }

  public void launchLevelByDifficulty(int difficulty) {
    Controller.launchLevel(levels[difficulty]);
  }

}
