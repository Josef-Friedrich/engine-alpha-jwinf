package rocks.friedrich.jwinf.tasks.conditionals.candle;

import ea.Game;

public class CandleTask {
  private int pixelPerMeter = 60;

  public static void main(String[] args) {
    new CandleTask().selectLevel(2);
  }

  void launchLevel(CandleLevel scene) {
    if (!Game.isRunning()) {
      Game.start(pixelPerMeter * scene.width, pixelPerMeter * scene.height, scene);
      Game.setTitle("Zünde alle Kerzen an");
      // Game.setDebug(true);
    } else {
      Game.setFrameSize(pixelPerMeter * scene.width, pixelPerMeter * scene.height);
      Game.transitionToScene(scene);
    }

    scene.focus();
  }

  void selectLevel(int difficulty) {
    int width = 9;
    int height = 6;
    int[] robotPosition = new int[] { 1, 0 };
    int[][] candles;

    switch (difficulty) {
      case 3:
        width = 10;
        candles = new int[][] { { 2, 3 }, { 4, 3 }, { 5, 3 }, { 8, 3 } };
        break;

      case 2:
        candles = new int[][] { { 2, 4 }, { 3, 4 }, { 4, 4 }, { 5, 4 }, { 6, 4 } };
        break;

      case 1:
      default:
        candles = new int[][] { { 4, 3 } };

    }

    CandleLevel scene = new CandleLevel(width, height, robotPosition, candles);

    launchLevel(scene);

    scene.controlRobot((robot, exercise) -> {
      while (robot.getGridX() < exercise.width - 1) {
        robot.goRight();

        if (robot.isOnCandle()) {
          while (robot.isOnCandle()) {
            robot.goUp();
          }

          robot.lightCandle();

          while (robot.getGridY() > 0) {
            robot.goDown();
          }
        }
      }
    });

    if (difficulty < 3) {
      selectLevel(++difficulty);
    }
  }
}
