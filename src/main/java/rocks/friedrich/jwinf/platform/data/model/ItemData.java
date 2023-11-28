package rocks.friedrich.jwinf.platform.data.model;

import rocks.friedrich.jwinf.platform.logic.Compass;

/**
 * Die Daten eines Gegenstands (Item).
 *
 * <p>
 * In der JSON-Datei sind die Daten beispielsweise so repräsentiert:
 *
 * <pre>{@code
 *   {
 *     "row": 2,
 *     "col": 1,
 *     "dir": 0,
 *     "type": "robot"
 *   }
 * }</pre>
 */
public class ItemData implements Cloneable
{
    /**
     * Die Nummer (ID) des Gegenstands.
     */
    public int num;

    /**
     * Ein eindeutiger Name, der den Gegenstand identifiziert. Zum Beispiel:
     * <code>candle</code>
     */
    public String name;

    /**
     * Die Zeilennummer, in der der Gegenstand plaziert ist.
     */
    public int row;

    /**
     * Die Spaltennummer, in der der Gegenstand plaziert ist.
     */
    public int col;

    /**
     * <code>0</code> steht für Osten, <code>1</code> steht für Süden,
     * <code>2</code> steht für Westen, <code>3</code> steht für Norden.
     *
     * @see rocks.friedrich.jwinf.platform.logic.Compass
     */
    public int dir;

    /**
     * Hat meistens den Wert <code>robot</code>.
     */
    public String type;

    /**
     * Relativer Pfad zu <code>src/main/resources/images</code>. Zum Beispiel:
     * <code>candle/candle.png</code>
     */
    public String relPath;

    /**
     * Zum Beispiel: <code>c</code>
     */
    public char letter;

    /**
     * Dateiname. Zum Beispiel: <code>kerze.png</code>. Importiert aus
     * Javascript-Datei.
     */
    public String img;

    /**
     * Größe in Pixel.
     */
    public int side;

    /**
     *
     */
    public int offsetX;

    /**
     *
     */
    public int offsetY;

    /**
     *
     */
    public int zOrder;

    /**
     *
     */
    public String color;

    /**
     * Gibt an, ob der Gegenstand eine Kiste darstellt.
     */
    public boolean isContainer;

    /**
     * Gibt an, ob der Gegenstand einen Ausgang darstellt.
     */
    public boolean isExit;

    /**
     * Gibt an, ob der Gegenstand einen Laser darstellt.
     */
    public boolean isLaser;

    /**
     * Gibt an, ob der Gegenstand ein Hindernis darstellt.
     */
    public boolean isObstacle;

    public boolean isPaint;

    /**
     * Gibt an, ob der Gegenstand einen Wurfkörper darstellt.
     */
    public boolean isProjectile;

    /**
     * Gibt an, ob der Gegenstand eine Kiste darstellt.
     */
    public boolean isPushable;

    /**
     * Gibt an, ob der Gegenstand einen Roboter darstellt.
     */
    public boolean isRobot;

    /**
     * Gibt an, ob der Gegenstand eingesammelt werden kann.
     */
    public boolean isWithdrawable;

    /**
     * Gibt an, ob der Gegenstand eine Tafel darstellt.
     */
    public boolean isWritable;

    public int nbStates;

    public Compass getCompassDirection()
    {
        return Compass.fromNumber(dir);
    }

    @Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
