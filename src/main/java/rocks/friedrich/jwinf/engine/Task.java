package rocks.friedrich.jwinf.engine;

import ea.Game;
import ea.animation.Interpolator;
import ea.animation.interpolation.EaseInOutFloat;
import ea.animation.interpolation.LinearFloat;

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

  /**
   * Die aktuelle Figur.
   */
  public static Actor actor;

  /**
   * Der aktuelle Kachelsatz.
   */
  public static TileMap map;

  public static int pixelPerMeter = 60;

  public static Interpolator<Float> interpolator = new EaseInOutFloat(0, 1);

  public static int speed = 1;

  public void launchLevelByDifficulty(DifficultyLevel difficulty) {
    launchLevel(levels[difficulty.getIndex()]);
  }

  public void launchLevelByDifficulty(int difficulty) {
    launchLevel(levels[difficulty]);
  }

  public static void launchLevel(Level level) {
    if (!Game.isRunning()) {
      Game.start(pixelPerMeter * level.width, pixelPerMeter * level.height, level);
      Game.setTitle("Zünde alle Kerzen an");
      // Game.setDebug(true);
    } else {
      Game.setFrameSize(pixelPerMeter * level.width, pixelPerMeter * level.height);
      Game.transitionToScene(level);
    }

    map = level.getMap();
    actor = level.getActor();

    level.focus();
  }

  public static void toggleInterpolator() {
    if (!(interpolator instanceof EaseInOutFloat)) {
      interpolator = new EaseInOutFloat(0, 1);
    } else {
      interpolator = new LinearFloat(0, 1);
    }
  }

}
