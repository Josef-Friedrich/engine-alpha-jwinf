package rocks.friedrich.jwinf.engine;

import java.util.HashMap;
import java.util.Map;

public enum DifficultyLevel {

  /**
   * Leichter Schwierigkeitsgrad: Zweistern-Version (<code>Version**</code>,
   * <em>easy</em>).
   */
  EASY(0),

  /**
   * Mittlerer Schwierigkeitsgrad: Dreistern-Version (<code>Version***</code>,
   * <em>medium</em>)
   */
  MEDIUM(1),

  /**
   * Schwerer Schwierigkeitsgrad: Vierstern-Version (<code>Version****</code>,
   * <em>hard</em>).
   */
  HARD(2);

  private int index;

  private static Map<Integer, DifficultyLevel> map = new HashMap<>();

  private DifficultyLevel(int index) {
    this.index = index;
  }

  static {
    for (DifficultyLevel level : DifficultyLevel.values()) {
      map.put(level.index, level);
    }
  }

  public static DifficultyLevel indexOf(int level) {
    return (DifficultyLevel) map.get(level);
  }

  public int getIndex() {
    return index;
  }

}
