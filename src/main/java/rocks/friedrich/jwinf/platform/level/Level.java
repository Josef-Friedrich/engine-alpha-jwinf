package rocks.friedrich.jwinf.platform.level;

import java.lang.reflect.InvocationTargetException;

import ea.Scene;
import ea.Vector;
import rocks.friedrich.jwinf.platform.data.model.LevelData;
import rocks.friedrich.jwinf.platform.data.model.TileData;
import rocks.friedrich.jwinf.platform.gui.map.Grid;
import rocks.friedrich.jwinf.platform.gui.map.TileMap;
import rocks.friedrich.jwinf.platform.logic.Task;
import rocks.friedrich.jwinf.platform.logic.level.Difficulty;
import rocks.friedrich.jwinf.platform.logic.level.LevelMap;
import rocks.friedrich.jwinf.platform.robot.Robot;
import rocks.friedrich.jwinf.platform.robot.RobotWrapper;

/**
 * Ein Test bzw. eine Version einer Trainingsaufgabe in einer bestimmen
 * Schwierigkeit.
 *
 * Eine Trainingsaufgabe kann mehrere Versionen unterschiedlicher
 * Schwierigkeitsgrade haben, z. B. eine Zweistern- (<code>Version**</code>,
 * <em>easy</em>), Dreistern-(<code>Version***</code>, <em>medium</em>), und
 * eine Vierstern-Version (<code>Version****</code>, <em>hard</em>).
 */
public class Level {

  public LevelData data;

  public Task task;

  public Difficulty difficulty;

  public int testNo;

  /**
   * Zum Beispiel „Der Roboter soll den Edelstein einsammeln. Sobald er das Feld
   * mit dem
   * Edelstein erreicht, wird dieser automatisch eingesammelt.“
   */
  public String intro;

  public int cols;

  public int rows;

  public LevelMap map;

  /**
   * Der Haupt-Kachelsatz. Die Figur muss auf diesen Kachelsatz Zugriff haben,
   * um entscheiden zu können, ob sie sich vor einem Hindernis befindet oder
   * nicht.
   */
  public TileMap tileMap;

  public Robot actor;

  public Level(LevelData data, Task task) {
    this.data = data;
    this.task = task;
    map = new LevelMap(data.tiles, task.tiles);
    cols = map.cols;
    rows = map.rows;
    difficulty = data.difficulty;
    testNo = data.testNo;
  }

  public TileMap createTileMap() {
    TileMap tileMap = new TileMap(cols, rows, "images");

    for (TileData tile : task.tiles.all()) {
      if (tile.relPath != null) {
        tileMap.registerImage(tile.letter, tile.relPath, tile.name);
      }
    }

    for (int row = 0; row < map.rows; row++) {
      for (int col = 0; col < map.cols; col++) {
        int num = data.tiles[row][col];
        TileData tile = task.tiles.get(num);
        if (tile != null) {
          tileMap.setTile(col, row, tile.letter);
        }
      }
    }
    return tileMap;
  }

  public Grid createGrid() {
    Grid grid = new Grid(cols, rows);
    grid.setColor(task.gridColor);
    grid.setBackground(task.backgroundColor);
    return grid;
  }

  public RobotWrapper createRobot() throws InstantiationException, IllegalAccessException, IllegalArgumentException,
      InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {

    String className = "rocks.friedrich.jwinf.tasks.conditionals.candle.Robot";
    if (task.data.packagePath != null) {
      className = "rocks.friedrich.jwinf.tasks.%s.Robot".formatted(task.data.packagePath.replace("/", "."));
    }
    RobotWrapper robot = RobotWrapper.class.getClassLoader()
        .loadClass(className)
        .asSubclass(RobotWrapper.class).getDeclaredConstructor()
        .newInstance();

    robot.actor = new Robot("images/candle/robot.png", map);
    robot.actor.addGridEdgesMovementListener();
    robot.actor.addObstaclesMovementListener();
    return robot;
  }

  /**
   * @param x - x-Koordinate der linken unteren Ecke
   * @param y - y-Koordinate der linken unteren Ecke
   */
  public AssembledLevel placeActorsInScene(Scene scene, float x, float y) {
    AssembledLevel level = new AssembledLevel();
    level.level = this;
    level.x = x;
    level.y = y;
    level.scene = scene;
    level.grid = createGrid();
    level.grid.setPosition(x - 0.5f, y - 0.5f);
    scene.add(level.grid);

    level.tileMap = createTileMap().container;
    level.tileMap.setPosition(x - 0.5f, y - 0.5f);
    scene.add(level.tileMap);

    map.setPosition(x, y);

    try {
      level.robot = createRobot();
      Vector robotPosition = map.translateToVector(data.initItems[0].row, data.initItems[0].col);
      level.robot.actor.setCenter(robotPosition.getX(), robotPosition.getY());
      scene.add(level.robot.actor);
    } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
        | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
      e.printStackTrace();
    }

    return level;
  }

}
