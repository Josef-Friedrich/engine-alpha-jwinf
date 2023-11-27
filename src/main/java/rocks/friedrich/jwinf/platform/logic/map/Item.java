package rocks.friedrich.jwinf.platform.logic.map;

import rocks.friedrich.jwinf.platform.data.model.ItemData;

/**
 * Ein Gegenstand auf dem Kachelgitter.
 */
public class Item
{
    private ItemData data;

    public Item(ItemData data)
    {
        this.data = data;
    }

    /**
     * Gibt an, ob der Gegenstand eine Kiste darstellt.
     */
    public boolean isContainer()
    {
        return data.isContainer;
    }

    /**
     * Gibt an, ob der Gegenstand einen Ausgang darstellt.
     */
    public boolean isExit()
    {
        return data.isExit;
    }

    /**
     * Gibt an, ob der Gegenstand einen Laser darstellt.
     */
    public boolean isLaser()
    {
        return data.isLaser;
    }

    /**
     * Gibt an, ob der Gegenstand ein Hindernis darstellt.
     */
    public boolean isObstacle()
    {
        return data.isObstacle;
    }

    public boolean isPaint()
    {
        return data.isPaint;
    }

    /**
     * Gibt an, ob der Gegenstand einen Wurfkörper darstellt.
     */
    public boolean isProjectile()
    {
        return data.isProjectile;
    }

    /**
     * Gibt an, ob der Gegenstand eine Kiste darstellt.
     */
    public boolean isPushable()
    {
        return data.isPushable;
    }

    /**
     * Gibt an, ob der Gegenstand einen Roboter darstellt.
     */
    public boolean isRobot()
    {
        return data.isRobot;
    }

    /**
     * Gibt an, ob der Gegenstand eingesammelt werden kann.
     */
    public boolean isWithdrawable()
    {
        return data.isWithdrawable;
    }
}
