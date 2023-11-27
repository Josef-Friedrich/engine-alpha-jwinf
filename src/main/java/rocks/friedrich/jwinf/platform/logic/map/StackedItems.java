package rocks.friedrich.jwinf.platform.logic.map;

import java.util.ArrayList;
import java.util.List;

import rocks.friedrich.jwinf.platform.data.model.ItemData;

/**
 * Aufeinander gestapelte Gegenstände.
 */
public class StackedItems
{
    private List<Item> items;

    public StackedItems()
    {
        this.items = new ArrayList<>();
    }

    public StackedItems(Item item)
    {
        this();
        add(item);
    }

    public StackedItems(ItemData itemData)
    {
        this();
        add(itemData);
    }

    public void add(Item item)
    {
        if (item != null)
        {
            items.add(item);
        }
    }

    public void add(ItemData itemData)
    {
        if (itemData != null)
        {
            add(new Item(itemData));
        }
    }

    public boolean isObstacle()
    {
        for (Item item : items)
        {
            if (item.isObstacle())
            {
                return true;
            }
        }
        return false;
    }

    public Item bottom()
    {
        if (!items.isEmpty())
        {
            return items.get(0);
        }
        return null;
    }

    public Item top()
    {
        if (!items.isEmpty())
        {
            return items.get(items.size() - 1);
        }
        return null;
    }
}
