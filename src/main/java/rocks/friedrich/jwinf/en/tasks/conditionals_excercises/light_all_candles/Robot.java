package rocks.friedrich.jwinf.en.tasks.conditionals_excercises.light_all_candles;

import rocks.friedrich.jwinf.platform.logic.robot.RobotWrapper;

/**
 * https://jwinf.de/task/1156
 */
public class Robot extends RobotWrapper
{
    /**
     * gehe nach rechts
     */
    public void right()
    {
        actor.east();
    }

    /**
     * gehe nach unten
     */
    public void down()
    {
        actor.south();
    }

    /**
     * gehe nach links
     */
    public void left()
    {
        actor.west();
    }

    /**
     * gehe nach oben
     */
    public void up()
    {
        actor.north();
    }

    /**
     * auf Kerze
     */
    public boolean onCandle()
    {
        return actor.onPaint();
    }

    /**
     * zünde Kerze an
     */
    public void lightCandle()
    {
        actor.dropObject(2);
    }
}
