package rocks.friedrich.jwinf.tasks.conditionals.candle;

import rocks.friedrich.jwinf.engine.ActorInTilemap;
import rocks.friedrich.jwinf.engine.TileMap;

class Robot extends ActorInTilemap {

  public Robot(TileMap map) {
    super("images/candle/robot.png", map);
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
