package rocks.friedrich.jwinf.platform.logic.context;

import rocks.friedrich.jwinf.platform.logic.item.Item;
import rocks.friedrich.jwinf.platform.logic.robot.ItemRelocation;

public class ItemRelocator
{
    protected Context context;

    public ItemRelocator(Context context)
    {
        this.context = context;
    }

    protected ItemRelocation reportItemRelocation(String name, Item item)
    {
        var action = new ItemRelocation(name, item);
        return action;
    }

    public Item drop()
    {
        return null;
    }

    public Item withdraw()
    {
        return null;
    }
}
