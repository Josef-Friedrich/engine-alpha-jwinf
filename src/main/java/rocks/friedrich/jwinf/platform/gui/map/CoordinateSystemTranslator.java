package rocks.friedrich.jwinf.platform.gui.map;

import ea.Vector;
import rocks.friedrich.jwinf.platform.logic.map.Coords;

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
    public float x;

    /**
     * Die y-Koordinate des linken unteren Ecks, an dem das Gitter im
     * Engine-Alpha-Koordinatensystem verankert ist.
     */
    public float y;

    public CoordinateSystemTranslator(int rows, int cols, float x, float y)
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
        this.x = x;
        this.y = y;
    }

    /**
     * y-Koordinate des Ursprungs des Gitters (links oben)
     */
    public float row0y()
    {
        return y + rows - 1;
    }

    public int toRow(float y)
    {
        return Math.round(row0y() - y);
    }

    public int toCol(float x)
    {
        return Math.round(x - this.x);
    }

    public float toX(int col)
    {
        return col + x;
    }

    public float toY(int row)
    {
        return row0y() - row;
    }

    /**
     * @param vector Ein Punkt im Engine-Alpha-Koordinatensystem.
     */
    public Coords toPoint(Vector vector)
    {
        return new Coords(toRow(vector.getY()), toCol(vector.getX()));
    }

    public Coords toPoint(float x, float y)
    {
        return toPoint(new Vector(x, y));
    }

    public Vector toVector(Coords point)
    {
        return new Vector(toX(point.getCol()), toY(point.getRow()));
    }

    public Vector toVector(int row, int col)
    {
        return toVector(new Coords(row, col));
    }
}
