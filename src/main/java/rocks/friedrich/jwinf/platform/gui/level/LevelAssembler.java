package rocks.friedrich.jwinf.platform.gui.level;

import java.lang.reflect.InvocationTargetException;

import ea.Scene;
import ea.Vector;
import rocks.friedrich.jwinf.platform.data.model.TileData;
import rocks.friedrich.jwinf.platform.gui.map.Grid;
import rocks.friedrich.jwinf.platform.gui.map.TileMap;
import rocks.friedrich.jwinf.platform.logic.level.Level;
import rocks.friedrich.jwinf.platform.robot.Robot;
import rocks.friedrich.jwinf.platform.robot.RobotWrapper;

/**
 * Klasse, die eine Version einer Trainingsaufgabe zusammenbaut.
 */
public class LevelAssembler {

  Level level;

  public LevelAssembler(Level level) {
    this.level = level;
  }

  public TileMap createTileMap() {
    TileMap tileMap = new TileMap(level.cols, level.rows, "images");

    for (TileData tile : level.task.tiles.all()) {
      if (tile.relPath != null) {
        tileMap.registerImage(tile.letter, tile.relPath, tile.name);
      }
    }

    for (int row = 0; row < level.map.rows; row++) {
      for (int col = 0; col < level.map.cols; col++) {
        int num = level.data.tiles[row][col];
        TileData tile = level.task.tiles.get(num);
        if (tile != null) {
          tileMap.setTile(col, row, tile.letter);
        }
      }
    }
    return tileMap;
  }

  public Grid createGrid() {
    Grid grid = new Grid(level.cols, level.rows);
    grid.setColor(level.task.gridColor);
    grid.setBackground(level.task.backgroundColor);
    return grid;
  }

  public RobotWrapper createRobot() throws InstantiationException, IllegalAccessException, IllegalArgumentException,
      InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {

    String className = "rocks.friedrich.jwinf.tasks.conditionals.candle.Robot";
    if (level.task.data.packagePath != null) {
      className = "rocks.friedrich.jwinf.tasks.%s.Robot".formatted(level.task.data.packagePath.replace("/", "."));
    }
    RobotWrapper robot = RobotWrapper.class.getClassLoader()
        .loadClass(className)
        .asSubclass(RobotWrapper.class).getDeclaredConstructor()
        .newInstance();

    robot.actor = new Robot("images/candle/robot.png", level.map);
    robot.actor.addGridEdgesMovementListener();
    robot.actor.addObstaclesMovementListener();
    return robot;
  }

  /**
   * @param x - x-Koordinate der linken unteren Ecke
   * @param y - y-Koordinate der linken unteren Ecke
   */
  public AssembledLevel placeActorsInScene(Scene scene, float x, float y) {
    AssembledLevel assembledLevel = new AssembledLevel();
    assembledLevel.level = level;
    assembledLevel.x = x;
    assembledLevel.y = y;
    assembledLevel.scene = scene;
    assembledLevel.grid = createGrid();
    assembledLevel.grid.setPosition(x - 0.5f, y - 0.5f);
    scene.add(assembledLevel.grid);

    assembledLevel.tileMap = createTileMap().container;
    assembledLevel.tileMap.setPosition(x - 0.5f, y - 0.5f);
    scene.add(assembledLevel.tileMap);

    level.map.setPosition(x, y);

    try {
      assembledLevel.robot = createRobot();
      Vector robotPosition = level.map.translateToVector(level.data.initItems[0].row, level.data.initItems[0].col);
      assembledLevel.robot.actor.setCenter(robotPosition.getX(), robotPosition.getY());
      scene.add(assembledLevel.robot.actor);
    } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
        | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
      e.printStackTrace();
    }

    return assembledLevel;
  }

}
