package rocks.friedrich.jwinf.platform.logic.level;

import rocks.friedrich.jwinf.platform.logic.robot.VirtualRobot;

/**
 * Sammelt alle Objekte, die für eine Version einer Trainingsaufgabe benötigt
 * werden.
 */
public class LevelEnvironment
{
    private Level level;

    private VirtualRobot robot;

    public LevelEnvironment(Level level, VirtualRobot robot)
    {
        this.level = level;
        this.robot = robot;
    }

    public Level getLevel()
    {
        return level;
    }

    public VirtualRobot getRobot()
    {
        return robot;
    }
}
