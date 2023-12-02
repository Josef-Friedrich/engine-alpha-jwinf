package rocks.friedrich.jwinf.platform.logic.robot;

import rocks.friedrich.jwinf.platform.logic.Compass;
import rocks.friedrich.jwinf.platform.logic.map.DirectionalPoint;
import rocks.friedrich.jwinf.platform.logic.map.Point;

/**
 * Represents a movement made by a robot.
 */
public class Movement extends Action
{
    private VirtualRobot robot;

    /**
     * Represented the point at which the robot is located before the movement.
     */
    public DirectionalPoint from;

    /**
     * Represented the point at which the robot is located after the movement.
     */
    public DirectionalPoint to;

    /**
     * Indicates whether a change in location has occurred or not.
     */
    public boolean relocated;

    /**
     * 1 ist 90 Grad nach rechts, -1 ist 90 Grad nach links, 2 ist 180 Grad nach
     * rechts.
     */
    public int rotation;

    public Movement next;

    /**
     * Constructs a Movement object with the specified name and robot.
     *
     * @param name  the name of the movement
     * @param robot the virtual robot
     */
    public Movement(String name, VirtualRobot robot)
    {
        super(name);
        from = new DirectionalPoint(robot.row, robot.col, robot.dir);
        this.robot = robot;
    }

    public DirectionalPoint getTo()
    {
        return to;
    }

    /**
     * Sets the 'to' location of the movement and calculates the relocation and
     * rotation values.
     *
     * @return the updated Movement object
     */
    public Movement setTo()
    {
        return setTo(robot.row, robot.col, robot.dir);
    }

    public Movement setTo(int toRow, int toCol, Compass toDir)
    {
        to = new DirectionalPoint(toRow, toCol, toDir);
        relocated = from.row != toRow || from.col != toCol;
        rotation = ((toDir.getNumber() - from.dir.getNumber() + 1) % 4) - 1;
        return this;
    }

    public Movement setTo(int toRow, int toCol)
    {
        return setTo(toRow, toCol, from.dir);
    }

    public Movement setTo(Point to)
    {
        return setTo(to.row, to.col);
    }

    public Movement setError(ErrorMessages error)
    {
        setTo();
        this.error = error;
        return this;
    }

    /**
     * Returns a string representation of the Movement object.
     *
     * @return a string representation of the Movement object
     */
    public String toString()
    {
        if (error != null)
        {
            return "Movement [name=%s, error=%s]".formatted(name, error);
        }
        return "Movement [name=%s, from=%s, to=%s, relocated=%s, rotation=%s]"
                .formatted(name, from.getSummary(), to.getSummary(), relocated,
                        rotation);
    }

    @Override
    public String getName()
    {
        if (!relocated && !name.equals("turnLeft") && !name.equals("turnRight")
                && !name.equals("turnAround"))
        {
            return "!%s".formatted(name);
        }
        return name;
    }
}
