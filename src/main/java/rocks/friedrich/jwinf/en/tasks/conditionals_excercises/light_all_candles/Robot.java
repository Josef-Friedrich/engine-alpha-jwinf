package rocks.friedrich.jwinf.en.tasks.conditionals_excercises.light_all_candles;

import rocks.friedrich.jwinf.platform.logic.robot.RobotWrapper;

public class Robot extends RobotWrapper
{
    public void right()
    {
        actor.east();
    }

    public void down()
    {
        actor.south();
    }

    public void left()
    {
        actor.west();
    }

    public void up()
    {
        actor.north();
    }

    public boolean onCandle()
    {
        return actor.onPaint();
    }

    public void lightCandle()
    {
        actor.dropObject(2);
    }
}
