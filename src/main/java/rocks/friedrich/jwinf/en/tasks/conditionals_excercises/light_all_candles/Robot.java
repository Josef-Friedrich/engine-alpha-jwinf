package rocks.friedrich.jwinf.en.tasks.conditionals_excercises.light_all_candles;

import rocks.friedrich.jwinf.platform.logic.robot.RobotWrapper;

public class Robot extends RobotWrapper
{

    public void east()
    {
        actor.east();
    }

    public void south()
    {
        actor.south();
    }

    public void west()
    {
        actor.west();
    }

    public void north()
    {
        actor.west();
    }

    public void onPaint() {
        //actor.onPaint();
    }

    public void dropObject()
    {
        actor.dropObject(2);
    }
}
