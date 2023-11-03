package rocks.friedrich.jwinf.engine.json.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import rocks.friedrich.jwinf.engine.DifficultyLevel;

public class Task {

  /**
   * Quelle HTML head title. Zum Beispiel: „Kerzen anzünden“
   */
  public String title;

  /**
   * In h1 HTML-Tags im HTML-Quelltext. Zum Beispiel: „Kerzen anzünden“
   */
  public String heading;

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
  public Map<String, Tile> _tiles;

  /**
   * Nur die Kacheln, die benötigt werden, d. h. die einen Buchstaben, Namen und
   * eine relativen Dateipfad haben.
   */
  private HashMap<Integer, Tile> tilesIndex;

  @JsonProperty("levels")
  public Levels _levels;

  public Task() {
    tilesIndex = new HashMap<>();
  }

  public Level getLevel(DifficultyLevel difficulty, int test) {
    return _levels.getLevel(difficulty, test);
  }

  public Level getLevel(DifficultyLevel difficulty) {
    return getLevel(difficulty, 0);
  }

  public Level getLevel(int difficulty) {
    return getLevel(DifficultyLevel.indexOf(difficulty), 0);
  }

  /**
   * Pseudo-Konstruktor.
   */
  public void initialize() {
    buildTilesIndex();
  }

  private void buildTilesIndex() {
    for (Tile tile : _tiles.values()) {
      if (tile.relPath != null && tile.num != 0) {
        tilesIndex.put(tile.num, tile);
      }
    }
  }

  public Tile getTile(int num) {
    return tilesIndex.get(num);
  }

  public Collection<Tile> getTiles() {
    return tilesIndex.values();
  }
}
