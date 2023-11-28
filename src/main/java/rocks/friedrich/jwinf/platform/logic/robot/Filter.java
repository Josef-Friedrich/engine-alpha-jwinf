package rocks.friedrich.jwinf.platform.logic.robot;

import rocks.friedrich.jwinf.platform.logic.map.Item;

public interface Filter
{
    public boolean check(Item item);
}