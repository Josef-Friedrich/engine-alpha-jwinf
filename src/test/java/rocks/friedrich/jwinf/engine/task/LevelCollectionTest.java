package rocks.friedrich.jwinf.engine.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static rocks.friedrich.jwinf.TestHelper.loadTask;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import rocks.friedrich.jwinf.engine.level.Difficulty;
import rocks.friedrich.jwinf.engine.level.LevelCollection;

class LevelCollectionTest {

  static LevelCollection levels;

  @BeforeAll
  static void getLevel() {
    levels = loadTask("20-DE-13-Kerzen-einfach").levels;
  }

  @Test
  void attributeNumberOfLevels() {
    assertEquals(levels.numberOfLevels, 3);
  }

  @Test
  void attributeMaxCols() {
    assertEquals(levels.maxCols, 10);
  }

  @Test
  void attributeMaxRows() {
    assertEquals(levels.maxRows, 6);
  }

  @Test
  void methodGetLevel() {
    assertEquals(levels.getLevel(Difficulty.EASY, 1).testNo, 0);
  }

    @Test
  void methodGetLevelNoTest() {
    assertEquals(levels.getLevel(Difficulty.EASY).testNo, 0);
  }

}
