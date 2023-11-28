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
     * Die x-Koordinate des linken unteren Ecks, an dem das Kachelgitter im
     * Engine-Alpha-Koordinatensystem verankert ist.
     */
    public int x;

    /**
     * Die y-Koordinate des linken unteren Ecks, an dem das Kachelgitter im
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
     * y-Koordinate des Ursprungs des Kachelgitters (links oben)
     */
    public int row0y()
    {
        return y + rows - 1;
    }

    /**
     * @param vector Ein Punkt im Engine-Alpha-Koordinatensystem.
     */
    public Point toPoint(Vector vector)
    {
        int xVector = Math.round(vector.getX());
        int yVector = Math.round(vector.getY());
        return new Point(row0y() - yVector, xVector - x);
    }

    public Point toPoint(float x, float y)
    {
        return toPoint(new Vector(x, y));
    }

    public Vector toVector(Point point)
    {
        return new Vector(x + point.col, row0y() - point.row);
    }

    public Vector toVector(int row, int col)
    {
        return toVector(new Point(row, col));
    }
}
