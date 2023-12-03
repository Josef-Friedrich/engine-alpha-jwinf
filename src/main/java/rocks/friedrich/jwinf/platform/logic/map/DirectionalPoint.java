package rocks.friedrich.jwinf.platform.logic.map;

import ea.Direction;
import rocks.friedrich.jwinf.platform.logic.Compass;

public class DirectionalPoint extends Point
{
    private Compass dir;

    public DirectionalPoint(int row, int col, Compass dir)
    {
        super(row, col);
        this.dir = dir;
    }

    public Compass getDir()
    {
        return dir;
    }

    public Direction getDirection()
    {
        return Compass.toDirection(dir);
    }

    public String toString()
    {
        return "DirectionalPoint [row=%s, col=%s, dir=%s]".formatted(row, col,
                dir);
    }

    public String getSummary()
    {
        return "(%s,%s;%s)".formatted(row, col, dir);
    }
}
