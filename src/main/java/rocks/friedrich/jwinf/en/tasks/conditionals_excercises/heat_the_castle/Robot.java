package rocks.friedrich.jwinf.en.tasks.conditionals_excercises.heat_the_castle;

import rocks.friedrich.jwinf.platform.logic.robot.RobotWrapper;

/**
 * https://jwinf.de/task/1159
 */
public class Robot extends RobotWrapper
{
    /**
     * gehe vorwärts
     */
    public void forward()
    {
        actor.forward();
    }

    /**
     * Holz einsammeln
     */
    public void collectFirewood()
    {
    }

    /**
     * Holz ablegen
     */
    public void dropFirewood()
    {
    }

    /**
     * baue eine Plattform vorne
     */
    public void constructPlatformInFront()
    {
        actor.dropPlatformInFront();
    }

    /**
     * baue eine Plattform oben
     */
    public void constructPlatformTop()
    {
        actor.dropPlatformAbove();
    }

    /**
     * Plattform vorne
     */
    public boolean platformInFront()
    {
        return true;
    }

    /**
     * Plattform oben
     */
    public boolean platformTop()
    {
        return true;
    }

    /**
     * auf Holz
     */
    public boolean onFirewood()
    {
        return true;
    }

    /**
     * beim Kamin
     */
    public boolean onFireplace()
    {
        return true;
    }

    /**
     * drehe um
     */
    public void turnAround()
    {
        actor.turnAround();
    }

    /**
     * spring hoch
     */
    public void jump()
    {
    }
}
