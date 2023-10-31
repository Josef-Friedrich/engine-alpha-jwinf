package rocks.friedrich.jwinf.candle;

import rocks.friedrich.jwinf.ActorInTilemap;
import rocks.friedrich.jwinf.LetterTileMap;

class Robot extends ActorInTilemap {

  public Robot(LetterTileMap map) {
    super("candle/robot.png", map);
    this.speed = 1;
  }

  public void lightCandle() {
    if (getTile() == 'w') {
      map.setTile(getGridX(), getGridY(), 'f');
    } else {
      wiggle();
    }
  }

  /**
   * Gibt wahr zurück, wenn sich der Roboter an der Kerze befindet.
   */
  public boolean isOnCandle() {
    if (getTile() == 'c') {
      return true;
    }
    return false;
  }
}
