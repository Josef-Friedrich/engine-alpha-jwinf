package rocks.friedrich.jwinf.tasks.conditionals.candle;

import rocks.friedrich.jwinf.engine.RealTileMap;

public class TileMap extends RealTileMap {

  public TileMap(int width, int height) {
    super(width, height, "images/candle", "png");
    registerImage('b', "background");
    registerImage('c', "candle");
    registerImage('w', "wick");
    registerImage('f', "flame");
  }

  public void setBackground() {
    fill('b');
  }

  public void setCandle(int x, int candleHeight) {
    int wickPosition = height - candleHeight - 1;
    for (int y = height - 1; y > wickPosition; y--) {
      setTile(x, y, 'c');
    }
    setTile(x, wickPosition, 'w');
  }

}
