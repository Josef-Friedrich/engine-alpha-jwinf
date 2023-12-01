package rocks.friedrich.jwinf.platform.logic.robot;

import rocks.friedrich.jwinf.platform.logic.map.DirectionalPoint;

/**
 * Represents a movement made by a robot.
 */
public class Movement
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

    public String name;

    /**
     * Indicates whether a change in location has occurred or not.
     */
    public boolean relocated;

    /**
     * 1 ist 90 Grad nach rechts, -1 ist 90 Grad nach links, 2 ist 180 Grad nach
     * rechts.
     */
    public int rotation;

    public ErrorMessages error;

    /**
     * Constructs a Movement object with the specified name and robot.
     *
     * @param name  the name of the movement
     * @param robot the virtual robot
     */
    public Movement(String name, VirtualRobot robot)
    {
        this.name = name;
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
        to = new DirectionalPoint(robot.row, robot.col, robot.dir);
        relocated = from.row != to.row || from.col != to.col;
        rotation = ((to.dir.getNumber() - from.dir.getNumber() + 1) % 4) - 1;
        return this;
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
            return "Movement [name=%s, error=%s]".formatted(from.getSummary(),
                    error);
        }
        return "Movement [name=%s, from=%s, to=%s, relocated=%s, rotation=%s]"
                .formatted(from.getSummary(), to.getSummary(), name, relocated,
                        rotation);
    }
}
