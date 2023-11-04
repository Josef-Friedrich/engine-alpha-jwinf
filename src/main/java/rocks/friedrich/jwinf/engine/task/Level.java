package rocks.friedrich.jwinf.engine.task;

import ea.Scene;
import rocks.friedrich.jwinf.engine.Actor;
import rocks.friedrich.jwinf.engine.Difficulty;
import rocks.friedrich.jwinf.engine.Grid;
import rocks.friedrich.jwinf.engine.data.model.LevelData;
import rocks.friedrich.jwinf.engine.data.model.TileData;
import rocks.friedrich.jwinf.engine.map.TileMap;

/**
 * Ein Schwierigkeitsgrad bzw. eine Version einer Trainingsaufgabe.
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

  public int width;

  public int height;

  public Grid grid;

  /**
   * Der Haupt-Kachelsatz. Die Figur muss auf diesen Kachelsatz Zugriff haben,
   * um entscheiden zu können, ob sie sich vor einem Hindernis befindet oder
   * nicht.
   */
  public TileMap map;

  public Actor actor;

  public Level(LevelData data, Task task) {
    this.data = data;
    this.task = task;

    width = data.getWidth();
    height = data.getHeight();
    difficulty = data.difficulty;
    testNo = data.testNo;
  }

  public TileMap createTileMap() {
    TileMap map = new TileMap(width, height, "images");

    for (TileData tile : task.getTiles()) {
      map.registerImage(tile.letter, tile.relPath, tile.name);
    }

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int num = data.tiles[y][x];
        TileData tile = task.getTile(num);
        if (tile != null) {
          map.setTile(x, y, tile.letter);
        }
      }
    }
    return map;
  }

  public Grid createGrid() {
    Grid grid = new Grid(width, height);
    grid.setColor(task.gridColor);
    grid.setBackground(task.backgroundColor);
    return grid;
  }

  public void paintMapInScene(Scene scene, float x, float y) {
    var grid = createGrid();
    grid.setPosition(x, y);
    scene.add(grid);

    var tileMap = createTileMap().container;
    tileMap.setPosition(x, y);
    scene.add(tileMap);
  }

  public void setGrid(String gridColor, String backgroundColor) {
    grid = createGrid();
    // Damit (0,0) in der Mitte einer Kachel liegt.
    grid.setPosition(-0.5f, -height + 0.5f);
    add(grid);
  }

  public void setMap(TileMap map) {
    this.map = map;
    add(this.map.container);
  }

  public void setMap(String pathPrefix, String extension) {
    map = new TileMap(width, height, pathPrefix, extension);
    add(map.container);
  }

  public void addActor(Actor actor) {
    this.actor = actor;
    add(actor);
  }
}
