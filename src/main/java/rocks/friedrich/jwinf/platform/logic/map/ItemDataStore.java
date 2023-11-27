package rocks.friedrich.jwinf.platform.logic.map;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import rocks.friedrich.jwinf.platform.data.model.ItemData;

/**
 * Ein Speicher für Daten der Gegenstände (ItemData). Eine Trainingsaufgabe
 * (Task) bedient sich eines Gegenständedatenspeichers (ItemDataStore).
 */
public class ItemDataStore
{
    private HashMap<String, ItemData> tilesByName;

    private HashMap<Integer, ItemData> tilesByNumber;

    public ItemDataStore(Map<String, ItemData> tiles)
    {
        tilesByName = new HashMap<>();
        tilesByNumber = new HashMap<>();
        tiles.forEach((name, tile) -> {
            if (tile.name == null)
            {
                tile.name = name;
            }
        });
        for (ItemData tile : tiles.values())
        {
            if (tile.name != null)
            {
                tilesByName.put(tile.name, tile);
            }
            if (tile.num != 0)
            {
                tilesByNumber.put(tile.num, tile);
            }
        }
    }

    public ItemDataStore()
    {
        this(new HashMap<String, ItemData>());
    }

    public ItemData get(int num)
    {
        return tilesByNumber.get(num);
    }

    public ItemData get(String name)
    {
        return tilesByName.get(name);
    }

    public Collection<ItemData> all()
    {
        return tilesByName.values();
    }
}
