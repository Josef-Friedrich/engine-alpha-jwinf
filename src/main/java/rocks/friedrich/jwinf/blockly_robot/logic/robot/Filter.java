package rocks.friedrich.jwinf.blockly_robot.logic.robot;

import rocks.friedrich.jwinf.blockly_robot.logic.item.Item;

@FunctionalInterface
public interface Filter
{
    public boolean check(Item item);
}