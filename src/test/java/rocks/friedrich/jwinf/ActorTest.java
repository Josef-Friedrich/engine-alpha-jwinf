package rocks.friedrich.jwinf;

import ea.Game;
import ea.Scene;
import ea.event.KeyListener;
import rocks.friedrich.jwinf.engine.Actor;
import rocks.friedrich.jwinf.engine.State;
import rocks.friedrich.jwinf.engine.grid.TileMapGrid;

import java.awt.Color;
import java.awt.event.KeyEvent;

public class ActorTest extends Scene implements KeyListener {

  public static void main(String[] args) {
    new ActorTest();
  }

  private int pixelPerMeter = 60;

  public int width;

  public int height;

  private Actor robot;

  public ActorTest() {
    width = 5;
    height = 5;

    if (!Game.isRunning()) {
      Game.start(pixelPerMeter * width, pixelPerMeter * height, this);
      // Game.setDebug(true);
    } else {
      Game.transitionToScene(this);
    }

    setBackgroundColor(new Color(212, 232, 196));

    TileMapGrid map = new TileMapGrid(width, height, "images/sea/", "png");

    map.registerImage('r', "road");

    map.parseMap(new String[] {
        "",
        "",
        "  r",
      });

    map.setObstacles('r');

    State.map = map;

    add(map.container);

    robot = new Actor("images/candle/robot.png");
    robot.setCenter(0, 0);
    add(robot);

    getCamera().setFocus(map.container);
    getCamera().setZoom(pixelPerMeter);
  }

  @Override
  public void onKeyDown(KeyEvent keyEvent) {
    switch (keyEvent.getKeyCode()) {
      case KeyEvent.VK_RIGHT:
        robot.goRightNonBlocking();
        break;

      case KeyEvent.VK_UP:
        robot.goUpNonBlocking();
        break;

      case KeyEvent.VK_LEFT:
        robot.goLeftNonBlocking();
        break;

      case KeyEvent.VK_DOWN:
        robot.goDownNonBlocking();
        break;
    }
  }
}
