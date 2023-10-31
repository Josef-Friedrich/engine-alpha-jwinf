package rocks.friedrich.jwinf.candle;

import rocks.friedrich.jwinf.LetterTileMap;

public class TileMap extends LetterTileMap {

  public TileMap(int width, int height) {
    super(width, height, "candle", "png");
    registerImage('b', "background");
    registerImage('c', "candle");
    registerImage('w', "wick");
    registerImage('f', "flame");
  }

  public void setBackground() {
    fill('b');
  }

  public void setCandle(int x, int height) {
    for (int y = 0; y < height; y++) {
      setTile(x, y, 'c');
    }
    setTile(x, height, 'w');
  }

}
