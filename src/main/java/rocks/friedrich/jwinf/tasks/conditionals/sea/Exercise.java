package rocks.friedrich.jwinf.tasks.conditionals.sea;

import java.awt.Color;
import java.awt.event.KeyEvent;

import ea.Game;
import ea.Scene;
import ea.event.KeyListener;
import rocks.friedrich.jwinf.engine.Actor;
import rocks.friedrich.jwinf.engine.Grid;
import rocks.friedrich.jwinf.engine.State;
import rocks.friedrich.jwinf.engine.TileMap;

public class Exercise extends Scene implements KeyListener {

  public static void main(String[] args) {
    new Exercise();
  }

  private int pixelPerMeter = 60;

  public int width;

  public int height;

  private Actor robot;

  public Exercise() {
    width = 10;
    height = 10;

    if (!Game.isRunning()) {
      Game.start(pixelPerMeter * width, pixelPerMeter * height, this);
      // Game.setDebug(true);
    } else {
      Game.transitionToScene(this);
    }

    setBackgroundColor(new Color(212, 232, 196));

    Grid grid = new Grid(width, height);
    grid.setBackground(new Color(212, 232, 196));
    grid.setColor(new Color(73, 182, 117));
    grid.setPosition(-0.5f, - height + 0.5f);
    add(grid);

    // <rect x="24.828125" y="202.171875" width="21.28125" height="21.28125" rx="0"
    // ry="0" fill="#d4e8c4" stroke="#49b675" style=""></rect>

    TileMap map = new TileMap(width, height, "images/sea/", "png");
    map.registerImage('1', "sea_tl");
    map.registerImage('2', "sea_tr");
    map.registerImage('3', "sea_bl");
    map.registerImage('4', "sea_br");
    map.registerImage('b', "brick");
    map.registerImage('f', "flowers");
    map.registerImage('g', "grass");
    map.registerImage('r', "road");
    map.registerImage('s', "sand");
    map.registerImage('t', "tree");
    map.registerImage('w', "bush"); // wood

    map.parseMap(new String[] {
        "ssswwgggtt",
        "s12ggggggg",
        "s34rrrrrgg",
        "fggggggrgg",
        "gggggftrwg",
        "ggggtggrwg",
        "gtgfgtwrwg",
        "ggggggwrwf",
        "rrrrrrrrtg",
        "wwgtbbbbbw" });

    map.setObstacles('b', 'f', 'g', 't', 'w');
    add(map);

    robot = new Actor("images/candle/robot.png");
    robot.setCenter(0, 1);
    add(robot);

    State.map = map;

    getCamera().setFocus(map);
    getCamera().setZoom(pixelPerMeter);
  }

  @Override
  public void onKeyDown(KeyEvent keyEvent) {
    switch (keyEvent.getKeyCode()) {
      case KeyEvent.VK_RIGHT:
        // robot.moveBy(1, 0);
        robot.goRightNonBlocking();
        break;

      case KeyEvent.VK_UP:
        // robot.moveBy(0, 1);
        robot.goUpNonBlocking();
        break;

      case KeyEvent.VK_LEFT:
        // robot.moveBy(-1, 0);
        robot.goLeftNonBlocking();
        break;

      case KeyEvent.VK_DOWN:
        // robot.moveBy(0, -1);
        robot.goDownNonBlocking();
        break;
    }
  }

}
