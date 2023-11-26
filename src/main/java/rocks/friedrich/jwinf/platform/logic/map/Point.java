package rocks.friedrich.jwinf.platform.logic.map;

/**
 * Ein Punkt auf dem Kachelgitter. Der Ursprung ist links oben (Reihe 0 und
 * Spalte 0).
 */
public class Point
{
    public int col;

    public int row;

    public Point(int row, int col)
    {
        this.row = row;
        this.col = col;
    }

    @Override
    public String toString()
    {
        return "Point [row=" + row + ", col=" + col + "]";
    }
}
