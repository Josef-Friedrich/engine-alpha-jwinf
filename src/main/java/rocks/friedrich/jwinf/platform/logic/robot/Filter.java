package rocks.friedrich.jwinf.platform.logic.robot;

import rocks.friedrich.jwinf.platform.logic.item.Item;

@FunctionalInterface
public interface Filter
{
    public boolean check(Item item);
}