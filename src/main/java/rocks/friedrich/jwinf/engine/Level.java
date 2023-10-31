package rocks.friedrich.jwinf.engine;

import ea.Scene;

public class Level extends Scene {

  public int width;

  public int height;

  Grid grid;

  TileMap background;

  ActorInTilemap actor;

  /**
   * @param width  Grid width
   * @param height Grid height
   */
  public Level(int width, int height) {
    this.width = width;
    this.height = height;
  }

  public void setGrid(String gridColor, String backgroundColor) {
    grid = new Grid(width, height);
    grid.setColor(new Color(gridColor));
    grid.setBackground(new Color(backgroundColor));
    grid.setPosition(-0.5f, -0.5f);
    add(grid);
  }

  public void controlActor(ActorAction action) {
    action.act(actor, this);
  }
}
