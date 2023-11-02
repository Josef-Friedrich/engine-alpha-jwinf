package rocks.friedrich.jwinf.tasks.conditionals.candle;

import rocks.friedrich.jwinf.engine.DifficultyLevel;
import rocks.friedrich.jwinf.engine.Task;
import rocks.friedrich.jwinf.engine.State;

public class CandleTask extends Task {

  public static void main(String[] args) {
    new CandleTask().launchLevelByDifficulty(2);

    Robot robot = (Robot) State.actor;

    while (robot.getGridX() < State.level.width - 1) {
      robot.goRight();

      if (robot.isOnCandle()) {
        while (robot.isOnCandle()) {
          robot.goUp();
        }

        robot.lightCandle();

        while (robot.getGridY() < State.level.height - 1) {
          robot.goDown();
        }
      }
    }
  }

  public CandleTask() {
    setupLevels();
  }

  void setupLevels() {
    for (DifficultyLevel difficulty : DifficultyLevel.values()) {
      int i = difficulty.getIndex();

      int width = 9;
      int height = 6;
      int[] robotPosition = new int[] { 1, 5 };
      int[][] candles;

      switch (i) {

        case 0:
        default:
          candles = new int[][] { { 4, 3 } };
          break;

        case 1:
          candles = new int[][] { { 2, 4 }, { 3, 4 }, { 4, 4 }, { 5, 4 }, { 6, 4 } };
          break;

        case 2:
          width = 10;
          candles = new int[][] { { 2, 3 }, { 4, 3 }, { 5, 3 }, { 8, 3 } };
          break;

      }

      levels[i] = new CandleLevel(width, height, robotPosition, candles);
    }
  }
}
