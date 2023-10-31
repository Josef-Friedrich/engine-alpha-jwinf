package rocks.friedrich.jwinf.tasks.conditionals.candle;

import rocks.friedrich.jwinf.engine.Actor;
import rocks.friedrich.jwinf.engine.Task;

class Robot extends Actor {

  public Robot() {
    super("images/candle/robot.png");
    this.speed = 1;
  }

  public void lightCandle() {
    if (getTile() == 'w') {
      Task.map.setTile(getGridX(), getGridY(), 'f');
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
