package rocks.friedrich.jwinf.blockly_robot.logic.item;

import java.util.HashMap;
import java.util.Map;

import rocks.friedrich.jwinf.blockly_robot.data.model.ItemData;

/**
 * Ein Speicher für die Daten der Gegenstände (ItemData). Eine Trainingsaufgabe
 * (Task) bedient sich eines Gegenständedatenspeichers (ItemDataStore).
 */
public class ItemCreator
{
    private HashMap<String, ItemData> itemsByName;

    private HashMap<Integer, ItemData> itemsByNumber;

    public ItemCreator(Map<String, ItemData> items)
    {
        itemsByName = new HashMap<>();
        itemsByNumber = new HashMap<>();
        items.forEach((name, itemData) -> {
            if (itemData.name == null)
            {
                itemData.name = name;
            }
        });
        for (ItemData item : items.values())
        {
            if (item.name != null)
            {
                itemsByName.put(item.name, item);
            }
            if (item.num != 0)
            {
                itemsByNumber.put(item.num, item);
            }
        }
    }

    private ItemData get(int num)
    {
        return itemsByNumber.get(num);
    }

    private ItemData get(String name)
    {
        return itemsByName.get(name);
    }

    /**
     * Creates a new Item object based on the provided ItemData.
     *
     * @param itemData The ItemData object to create the Item from.
     * @return The newly created Item object, or null if itemData is null or
     *         cloning is not supported.
     */
    private Item createItem(ItemData itemData)
    {
        if (itemData == null)
        {
            return null;
        }
        return new Item(itemData);
    }

    /**
     * Creates an item with the given number.
     *
     * @param num the number of the item
     * @return the created item
     */
    public Item create(int num)
    {
        return createItem(get(num));
    }

    /**
     * Creates an item with the given name.
     *
     * @param name the name of the item
     * @return the created item
     */
    public Item create(String name)
    {
        return createItem(get(name));
    }
}
