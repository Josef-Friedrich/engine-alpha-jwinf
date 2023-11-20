package rocks.friedrich.jwinf.engine.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import rocks.friedrich.jwinf.platform.level.Level;

import static rocks.friedrich.jwinf.TestHelper.loadTask;

class LevelTest {

  static Level level;

  @BeforeAll
  static void getLevel() {
    level = loadTask("20-DE-13-Kerzen-einfach").getLevel(0);
  }

  @Test
  void attributeRows() {
    assertEquals(level.rows, 6);
  }

  @Test
  void attributeCols() {
    assertEquals(level.cols, 9);
  }

}
