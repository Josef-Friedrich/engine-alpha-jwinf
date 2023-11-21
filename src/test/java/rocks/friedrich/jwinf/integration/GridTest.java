package rocks.friedrich.jwinf.integration;

import ea.Game;
import ea.Scene;
import rocks.friedrich.jwinf.platform.gui.map.Grid;

public class GridTest extends Scene {

  public static void main(String[] args) {
    new GridTest();
  }

  public GridTest() {
    if (!Game.isRunning()) {
      Game.start(1000, 1000, this);
      Game.setDebug(true);
    } else {
      Game.transitionToScene(this);
    }

    Grid grid = new Grid(10, 10);
    grid.setCenter(1, 1);
    grid.setPosition(3, 3);
    grid.setOpacity(0.3f);
    add(grid);

    grid.rotateBy(33);

    getCamera().setFocus(grid);
    getCamera().setZoom(70);
  }

}
