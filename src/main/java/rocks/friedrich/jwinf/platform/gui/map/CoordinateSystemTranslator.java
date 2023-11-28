package rocks.friedrich.jwinf.platform.gui.map;

import ea.Vector;
import rocks.friedrich.jwinf.platform.logic.map.Point;

public class CoordinateSystemTranslator
{
    /**
     * Anzahl an Reihen (y-Richtung bzw. Höhe)
     */
    public int rows;

    /**
     * Anzahl an Spalten (x-Richtung bzw. Breite)
     */
    public int cols;

    /**
     * Die x-Koordinate des linken unteren Ecks, an dem das Gitter im
     * Engine-Alpha-Koordinatensystem verankert ist.
     */
    public int x;

    /**
     * Die y-Koordinate des linken unteren Ecks, an dem das Gitter im
     * Engine-Alpha-Koordinatensystem verankert ist.
     */
    public int y;

    public CoordinateSystemTranslator(int rows, int cols, int x, int y)
    {
        this.rows = rows;
        this.cols = cols;
        this.x = x;
        this.y = y;
    }

    public CoordinateSystemTranslator(int rows, int cols)
    {
        this.rows = rows;
        this.cols = cols;
    }

    public void setPosition(float x, float y)
    {
        this.x = Math.round(x);
        this.y = Math.round(y);
    }

    /**
     * y-Koordinate des Ursprungs des Gitters (links oben)
     */
    public int row0y()
    {
        return y + rows - 1;
    }

    public int toRow(float y)
    {
        return row0y() - Math.round(y);
    }

    public int toCol(float x)
    {
        return Math.round(x) - this.x;
    }

    public int toX(int col)
    {
        return col + x;
    }

    public int toY(int row)
    {
        return row0y() - row;
    }

    /**
     * @param vector Ein Punkt im Engine-Alpha-Koordinatensystem.
     */
    public Point toPoint(Vector vector)
    {
        return new Point(toRow(vector.getY()), toCol(vector.getX()));
    }

    public Point toPoint(float x, float y)
    {
        return toPoint(new Vector(x, y));
    }

    public Vector toVector(Point point)
    {
        return new Vector(toX(point.getCol()), toY(point.getRow()));
    }

    public Vector toVector(int row, int col)
    {
        return toVector(new Point(row, col));
    }
}
