package rocks.friedrich.jwinf;

import ea.Scene;
import rocks.friedrich.jwinf.engine.Controller;
import rocks.friedrich.jwinf.engine.map.TileMap;
import rocks.friedrich.jwinf.engine.task.Task;

public class JsonToTileMapTest extends Scene {

  public static void main(String[] args) {
    new JsonToTileMapTest();
  }

  public JsonToTileMapTest() {
    Controller.launchScene(this);
    Task task = new Task("data/candle.json");
    TileMap map = task.getLevel(0).createTileMap();
    add(map.container);
  }
}
