package rocks.friedrich.jwinf.engine.task;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rocks.friedrich.jwinf.engine.Difficulty;
import rocks.friedrich.jwinf.engine.data.JsonLoader;
import rocks.friedrich.jwinf.engine.data.model.TaskData;
import rocks.friedrich.jwinf.engine.data.model.TileData;

/**
 * Eine Trainingsaufgabe (Task) besteht aus mehreren Schwierigkeitsgraden
 * (Level)
 */
public class Task {

  public TaskData data;

  /**
   * Zum Beispiel „Edelsteine einsammeln“
   */
  public String title;

  /**
   * Zum Beispiel „Programmiere den Roboter“
   */
  public String intro;

  public LevelCollection levels;

  /**
   * Nur die Kacheln, die benötigt werden, d. h. die einen Buchstaben, Namen und
   * eine relativen Dateipfad haben.
   */
  public HashMap<Integer, TileData> tiles;

  public Task(String filePath) {
    try {
      data = JsonLoader.load(filePath);
    } catch (IOException e) {
      e.printStackTrace();
    }
    title = data.title;
    intro = data.intro;

    tiles = new HashMap<Integer, TileData>();
    buildTilesIndex(data);
    levels = new LevelCollection(data.levels, this);
  }

  private void buildTilesIndex(TaskData data) {
    for (TileData tile : data.tiles.values()) {
      if (tile.relPath != null && tile.num != 0) {
        tiles.put(tile.num, tile);
      }
    }
  }

  public TileData getTile(int num) {
    return tiles.get(num);
  }

  public Collection<TileData> getTiles() {
    return tiles.values();
  }

  public Map<Difficulty, List<Level>> getLevels() {
    return levels.levels;
  }

  public Level getLevel(Difficulty difficulty, int test) {
    return levels.getLevel(difficulty, test);
  }

  public Level getLevel(Difficulty difficulty) {
    return getLevel(difficulty, 0);
  }

  public Level getLevel(int difficulty) {
    return getLevel(Difficulty.indexOf(difficulty), 0);
  }

  // public void launchLevelByDifficulty(DifficultyLevel difficulty) {
  // Controller.launchLevel(getLevel(difficulty));
  // }

  // public void launchLevelByDifficulty(int difficulty) {
  // Controller.launchLevel(levels[difficulty]);
  // }

}
