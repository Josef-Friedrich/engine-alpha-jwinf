package rocks.friedrich.jwinf;

import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import ea.Scene;
import rocks.friedrich.jwinf.engine.Controller;
import rocks.friedrich.jwinf.engine.json.TaskLoader;
import rocks.friedrich.jwinf.engine.map.TileMap;

public class JsonToTileMapTest extends Scene {

  public static void main(String[] args) {
    new JsonToTileMapTest();
  }

  public JsonToTileMapTest() {
    Controller.launchScene(this);

    try {
      TileMap map = TaskLoader.createTileMap("json/candle.json");

      add(map.container);
    } catch (StreamReadException e) {
      e.printStackTrace();
    } catch (DatabindException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
