package rocks.friedrich.jwinf.engine.task;

import ea.Scene;
import ea.Vector;
import rocks.friedrich.jwinf.engine.Difficulty;
import rocks.friedrich.jwinf.engine.Grid;
import rocks.friedrich.jwinf.engine.Robot;
import rocks.friedrich.jwinf.engine.data.model.LevelData;
import rocks.friedrich.jwinf.engine.data.model.TileData;
import rocks.friedrich.jwinf.engine.map.TileMap;

/**
 * Ein Test bzw. eine Version einer Trainingsaufgabe in einer bestimmen
 * Schwierigkeit.
 *
 * Eine Trainingsaufgabe kann mehrere Versionen unterschiedlicher
 * Schwierigkeitsgrade haben, z. B. eine Zweistern- (<code>Version**</code>,
 * <em>easy</em>), Dreistern-(<code>Version***</code>, <em>medium</em>), und
 * eine Vierstern-Version (<code>Version****</code>, <em>hard</em>).
 */
public class Level extends Scene {

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

  /**
   * @param x - x-Koordinate der linken unteren Ecke
   * @param y - y-Koordinate der linken unteren Ecke
   */
  public LevelActors paintMapInScene(Scene scene, float x, float y) {
    LevelActors actors = new LevelActors();
    actors.grid = createGrid();
    actors.grid.setPosition(x - 0.5f, y - 0.5f);
    scene.add(actors.grid);

    actors.tileMap = createTileMap().container;
    actors.tileMap.setPosition(x - 0.5f, y - 0.5f);
    scene.add(actors.tileMap);

    actors.robot = new Robot("images/candle/robot.png", map);

    map.setPosition(x, y);

    Vector robotPosition = map.translateToVector(data.initItems[0].row, data.initItems[0].col);
    actors.robot.setCenter(robotPosition.getX(), robotPosition.getY());
    scene.add(actors.robot);

    return actors;
  }

}
