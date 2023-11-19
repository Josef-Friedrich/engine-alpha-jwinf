package rocks.friedrich.jwinf.engine.map;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import rocks.friedrich.jwinf.engine.data.model.TileData;

/**
 * Ein Speicher für Kacheln (Tile). Eine Trainingsaufgabe (Task) bedient sicher
 * eines Kachelspeichers (TilesStore).
 */
public class TilesStore {
  private HashMap<String, TileData> tilesByName;

  private HashMap<Integer, TileData> tilesByNumber;

  public TilesStore(Map<String, TileData> tiles) {
    tilesByName = new HashMap<>();
    tilesByNumber = new HashMap<>();
    tiles.forEach((name, tile) -> {
      if (tile.name == null) {
        tile.name = name;
      }
    });

    for (TileData tile : tiles.values()) {
      if (tile.name != null) {
        tilesByName.put(tile.name, tile);
      }
      if (tile.num != 0) {
        tilesByNumber.put(tile.num, tile);
      }
    }
  }

  public TilesStore() {
    this(new HashMap<String, TileData>());
  }

  public TileData get(int num) {
    return tilesByNumber.get(num);
  }

  public TileData get(String name) {
    return tilesByName.get(name);
  }

  public Collection<TileData> all() {
    return tilesByName.values();
  }

}
