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

    /**
     * Adds an item on top to the specified position.
     */
    public Item drop(int row, int col, int itemNum)
    {
        return drop(row, col, itemCreator.create(itemNum));
    }

    public Item drop(int row, int col, String itemName)
    {
        return drop(row, col, itemCreator.create(itemName));
    }

    public Item drop(Coords coords, String itemName)
    {
        return drop(coords.getRow(), coords.getCol(),
                itemCreator.create(itemName));
    }

    /**
     * Adds an item on top to the specified position.
     */
    private Item drop(int row, int col, Item item)
    {
        item.setPosition(row, col);
        context.get(row, col).add(item);
        return item;
    }

    public Item withdraw()
    {
        return null;
    }
}
