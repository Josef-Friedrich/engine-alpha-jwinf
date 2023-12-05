package rocks.friedrich.jwinf.blockly_robot.logic.context.item_relocation;

import rocks.friedrich.jwinf.blockly_robot.logic.context.Context;
import rocks.friedrich.jwinf.blockly_robot.logic.context.Coords;
import rocks.friedrich.jwinf.blockly_robot.logic.item.Item;
import rocks.friedrich.jwinf.blockly_robot.logic.item.ItemCreator;
import rocks.friedrich.jwinf.blockly_robot.logic.robot.ItemRelocation;

abstract class ItemRelocator
{
    protected Context context;

    protected ItemCreator itemCreator;

    public ItemRelocator(Context context)
    {
        this.context = context;
        this.itemCreator = context.getItemCreator();
    }

    protected ItemRelocation reportItemRelocation(String name, Item item)
    {
        var action = new ItemRelocation(name, item);
        return action;
    }

    protected Item createItem(int itemNum)
    {
        return itemCreator.create(itemNum);
    }

    protected Item drop(Coords coords, int itemNum)
    {
        return drop(coords, itemCreator.create(itemNum));
    }

    protected Item drop(Coords coords, String itemName)
    {
        return drop(coords, itemCreator.create(itemName));
    }

    protected Item drop(Coords coords, Item item)
    {
        item.setPosition(coords.getRow(), coords.getCol());
        context.get(coords.getRow(), coords.getCol()).add(item);
        return item;
    }
}
