package rocks.friedrich.jwinf;

import ea.Scene;
import rocks.friedrich.jwinf.engine.Controller;
import rocks.friedrich.jwinf.engine.task.Task;

public class JsonToTileMapTest extends Scene {

  public static void main(String[] args) {
    new JsonToTileMapTest();
  }

  public JsonToTileMapTest() {
    Controller.launchScene(this);
    var task = Task.loadById("20-DE-13-Kerzen-einfach");
    var map = task.getLevel(0).createTileMap();
    add(map.container);
  }
}
