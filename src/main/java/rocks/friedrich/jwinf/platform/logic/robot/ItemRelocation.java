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
        super(name);
        this.item = item;
    }
}
