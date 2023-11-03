package rocks.friedrich.jwinf.engine.json;

import java.io.IOException;

import ea.internal.io.ResourceLoader;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import rocks.friedrich.jwinf.engine.json.model.Level;
import rocks.friedrich.jwinf.engine.json.model.Task;
import rocks.friedrich.jwinf.engine.json.model.Tile;
import rocks.friedrich.jwinf.engine.map.TileMap;

public class TaskLoader {

  public static Task load(String filePath) throws StreamReadException, DatabindException, IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.readValue(ResourceLoader.loadAsStream(filePath), Task.class);
  }

  public static TileMap createTileMap(String filePath) throws StreamReadException, DatabindException, IOException {
    Task task = load(filePath);

    task.initialize();

    Level level = task.getLevel(2);

    TileMap map = new TileMap(level.getWidth(), level.getHeight(), "images");

    for (Tile tile : task.getTiles()) {
      map.registerImage(tile.letter, tile.relPath, tile.name);
    }

    for (int y = 0; y < level.getHeight(); y++) {
      for (int x = 0; x < level.getWidth(); x++) {
        int num = level.tiles[y][x];
        if (num != 1) {
          Tile tile = task.getTile(num);
          map.setTile(x, y, tile.letter);
        }
      }
    }

    return map;
  }

  public static void main(String[] args) {
    try {
      Task task = load("json/candle.json");
      System.out.println(task._tiles.get("robot").img);
    } catch (StreamReadException e) {
      e.printStackTrace();
    } catch (DatabindException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
