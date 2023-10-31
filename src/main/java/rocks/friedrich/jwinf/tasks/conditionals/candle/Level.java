package rocks.friedrich.jwinf.tasks.conditionals.candle;

import ea.Scene;
import rocks.friedrich.jwinf.engine.Color;
import rocks.friedrich.jwinf.engine.Grid;

interface RobotAction {
  public void act(Robot robot, Level exercise);
}

public class Level extends Scene {

  public int width;

  public int height;

  Grid grid;

  TileMap background;

  Robot robot;

  /**
   * @param width         - Grid width
   * @param height        - Grid height
   * @param robotPosition - starting from 0 {0, 0} is bottom left
   * @param candles       - starting from 0, wick positions
   */
  public Level(int width, int height, int[] robotPosition, int[][] candles) {
    this.width = width;
    this.height = height;

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

  public void setGrid(String gridColor, String backgroundColor) {
    grid = new Grid(width, height);
    grid.setColor(new Color(gridColor));
    grid.setBackground(new Color(backgroundColor));
    grid.setPosition(-0.5f, -0.5f);
    add(grid);
  }

  public void controlRobot(RobotAction robotAction) {
    robotAction.act(robot, this);
  }
}
