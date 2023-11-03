package rocks.friedrich.jwinf.engine.data.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import rocks.friedrich.jwinf.engine.DifficultyLevel;

public class TaskData {

  /**
   * Quelle HTML head title oder In h1 HTML-Tags im HTML-Quelltext. Zum Beispiel: „Kerzen anzünden“
   */
  public String title;

  /**
   * Quelle HTML DIV class taskIntro
   *
   * Zeilenumbrüche mit \n
   *
   * Zum Beispiel: „Programmiere den Roboter: Der Roboter soll alle Kerzen
   * anzünden.“
   */
  public String intro;

  /**
   * HTML DIV id=menu
   */
  public String[] menu;

  /**
   * Links zu den Quellen der JSON-Datei:
   * https://jwinf.de/tasks/jwinf/jwinf-aufgaben/2020/20-DE-13-Kerzen-einfach/index_new.html?channelId=task
   * https://jwinf.de/tasks/jwinf/jwinf-aufgaben/2020/20-DE-13-Kerzen-einfach/task_new.js
   */
  public String[] sources;

  @JsonProperty("tiles")
  public Map<String, TileData> _tiles;

  /**
   * Nur die Kacheln, die benötigt werden, d. h. die einen Buchstaben, Namen und
   * eine relativen Dateipfad haben.
   */
  private HashMap<Integer, TileData> tilesIndex;

  @JsonProperty("levels")
  public LevelCollectionData _levels;

  public TaskData() {
    tilesIndex = new HashMap<>();
  }

  public LevelData getLevel(DifficultyLevel difficulty, int test) {
    return _levels.getLevel(difficulty, test);
  }

  public LevelData getLevel(DifficultyLevel difficulty) {
    return getLevel(difficulty, 0);
  }

  public LevelData getLevel(int difficulty) {
    return getLevel(DifficultyLevel.indexOf(difficulty), 0);
  }

  /**
   * Pseudo-Konstruktor.
   */
  public void initialize() {
    buildTilesIndex();
  }

  private void buildTilesIndex() {
    for (TileData tile : _tiles.values()) {
      if (tile.relPath != null && tile.num != 0) {
        tilesIndex.put(tile.num, tile);
      }
    }
  }

  public TileData getTile(int num) {
    return tilesIndex.get(num);
  }

  public Collection<TileData> getTiles() {
    return tilesIndex.values();
  }
}
