package rocks.friedrich.jwinf.engine.json;

import java.io.IOException;
import java.util.HashMap;

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

    Level level = task.levels.easy[0];

    int height = level.tiles.length;
    int width = level.tiles[0].length;

    HashMap<Integer, Tile> tiles = new HashMap<>();

    for (Tile tile : task.tiles.values()) {
      if (tile.relPath != null && tile.num != 0) {
        tiles.put(tile.num, tile);
      }
    }

    TileMap map = new TileMap(width, height, "images");

    for (Tile tile : tiles.values()) {
      map.registerImage(tile.letter, tile.relPath, tile.name);
    }

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int num = level.tiles[y][x];
        if (num != 1) {
          Tile tile = tiles.get(num);
          map.setTile(x, y, tile.letter);
        }
      }
    }

    return map;
  }

  public static void main(String[] args) {
    try {
      Task task = load("json/candle.json");
      System.out.println(task.tiles.get("robot").img);
    } catch (StreamReadException e) {
      e.printStackTrace();
    } catch (DatabindException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
