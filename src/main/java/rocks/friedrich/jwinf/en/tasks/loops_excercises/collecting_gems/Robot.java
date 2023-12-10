package rocks.friedrich.jwinf.en.tasks.loops_excercises.collecting_gems;

import rocks.friedrich.jwinf.blockly_robot.logic.robot.RobotWrapper;

/**
 * https://jwinf.de/task/1139
 */
public class Robot extends RobotWrapper
{
    /**
     * drehe nach links
     */
    public void turnLeft()
    {
        actor.turnLeft();
    }

    /**
     * drehe nach rechts
     */
    public void turnRight()
    {
        actor.turnRight();
    }

    /**
     * gehe vorwärts
     */
    public void forward()
    {
        actor.forward();
    }
}
