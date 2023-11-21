package rocks.friedrich.jwinf.platform.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ea.Vector;
import rocks.friedrich.jwinf.platform.logic.level.LevelMap;
import rocks.friedrich.jwinf.platform.logic.map.Point;

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

  private void assertPoint(Point point, int row, int col) {
    assertEquals(point.row, row);
    assertEquals(point.col, col);
  }

  @Test
  public void methodTranslateToPoint() {
    Point point = create(3, 4, 3, 2).translateToPoint(5, 3);
    assertPoint(point, 1, 2);

    point = create(3, 4, -3, -2).translateToPoint(-1, -1);
    assertPoint(point, 1, 2);

    point = create(3, 4, 5, -5).translateToPoint(7, -4);
    assertPoint(point, 1, 2);

    point = create(3, 4, 0, 0).translateToPoint(2, 1);
    assertPoint(point, 1, 2);

    point = create(10, 10, -5, -5).translateToPoint(4, 4);
    assertPoint(point, 0, 9);
  }

  private void assertVector(Vector vector, int x, int y) {
    assertEquals(vector.getX(), x);
    assertEquals(vector.getY(), y);
  }

  @Test
  public void methodTranslateToVector() {
    Vector vector = create(3, 4, 3, 2).translateToVector(2, 0);
    assertVector(vector, 3, 2);

    vector = create(3, 4, -3, -2).translateToVector(2, 0);
    assertVector(vector, -3, -2);

    vector = create(3, 4, 0, 0).translateToVector(0, 0);
    assertVector(vector, 0, 2);
  }

}
