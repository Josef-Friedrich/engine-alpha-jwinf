package rocks.friedrich.jwinf.platform.logic.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import rocks.friedrich.jwinf.platform.data.model.ItemData;
import rocks.friedrich.jwinf.platform.logic.robot.Filter;

/**
 * Aufeinander gestapelte Gegenstände.
 */
public class StackedItems implements Iterable<Item>
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

    @Override
    public Iterator<Item> iterator()
    {
        return items.iterator();
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

    public boolean has(Filter filter)
    {
        for (Item item : items)
        {
            if (filter.check(item))
            {
                return true;
            }
        }
        return false;
    }

    public boolean isExit()
    {
        return has(item -> item.isExit());
    }

    public boolean isObstacle()
    {
        return has(item -> item.isObstacle());
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
