package rocks.friedrich.jwinf.engine.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ea.Vector;

public class LevelMapTest {

  private LevelMap create(int rows, int cols, int x, int y) {
    LevelMap map = new LevelMap(rows, cols);
    map.setPosition(x, y);
    return map;
  }

  private LevelMap create(int rows, int cols) {
    return new LevelMap(rows, cols);
  }

  @Test
  public void attributeRowsAndCols() {
    LevelMap map = create(4, 5);
    assertEquals(map.rows, 4);
    assertEquals(map.cols, 5);
  }

  @Test
  public void methodTranslateToPoint() {
    LevelMap map = create(3, 4, 3, 2);
    Point point = map.translateToPoint(new Vector(5, 3));
    assertEquals(point.row, 1);
    assertEquals(point.col, 2);

    map = create(3, 4, -3, -2);
    point = map.translateToPoint(new Vector(-1, -1));
    assertEquals(point.row, 1);
    assertEquals(point.col, 2);

    map = create(3, 4, 5, -5);
    point = map.translateToPoint(new Vector(7, -4));
    assertEquals(point.row, 1);
    assertEquals(point.col, 2);

    map = create(3, 4, 0, 0);
    point = map.translateToPoint(new Vector(2, 1));
    assertEquals(point.row, 1);
    assertEquals(point.col, 2);

    map = create(10, 10, -5, -5);
    point = map.translateToPoint(new Vector(4, 4));
    assertEquals(point.row, 0);
    assertEquals(point.col, 9);
  }

}
