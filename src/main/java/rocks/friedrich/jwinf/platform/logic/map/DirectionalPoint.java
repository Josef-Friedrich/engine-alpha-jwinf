package rocks.friedrich.jwinf.platform.logic.map;

import ea.Direction;
import rocks.friedrich.jwinf.platform.logic.Compass;

public class DirectionalPoint extends Point
{
    public Compass dir;

    public DirectionalPoint(int row, int col, Compass dir)
    {
        super(row, col);
        this.dir = dir;
    }

    public Direction getDirection()
    {
        return Compass.toDirection(dir);
    }
}
