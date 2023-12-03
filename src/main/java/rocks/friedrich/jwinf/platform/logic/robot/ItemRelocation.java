package rocks.friedrich.jwinf.platform.logic.robot;

import rocks.friedrich.jwinf.platform.logic.item.Item;

/**
 * Withdrawing or dropping an item.
 */
public class ItemRelocation extends Action
{
    public Item item;

    public ItemRelocation(String name, Item item)
    {
        this(name);
        this.item = item;
    }

    public ItemRelocation(String name)
    {
        super(name);
    }

    public ItemRelocation setItem(Item item)
    {
        this.item = item;
        return this;
    }
}
