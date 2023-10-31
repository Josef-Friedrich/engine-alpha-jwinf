package rocks.friedrich.jwinf.tasks.conditionals.candle;

import rocks.friedrich.jwinf.engine.Level;

interface RobotAction {
  public void act(Robot robot, CandleLevel exercise);
}

public class CandleLevel extends Level {

  TileMap background;

  Robot robot;

  /**
   * @param width         - Grid width
   * @param height        - Grid height
   * @param robotPosition - starting from 0 {0, 0} is bottom left
   * @param candles       - starting from 0, wick positions
   */
  public CandleLevel(int width, int height, int[] robotPosition, int[][] candles) {
    super(width, height);

    setGrid("b4ccc7", "c5e2dd");

    background = new TileMap(width, height);
    add(background);

    for (int i = 0; i < candles.length; i++) {
      int[] candlePosition = candles[i];
      background.setCandle(candlePosition[0], candlePosition[1]);
    }

    robot = new Robot(background);
    robot.setCenter(robotPosition[0], robotPosition[1]);
    add(robot);
    robot.setRotation(0);
    robot.setSpeed(1.5f);
  }

  public void controlRobot(RobotAction robotAction) {
    robotAction.act(robot, this);
  }
}
