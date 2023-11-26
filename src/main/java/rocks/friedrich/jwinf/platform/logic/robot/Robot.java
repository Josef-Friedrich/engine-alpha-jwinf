package rocks.friedrich.jwinf.platform.logic.robot;

import rocks.friedrich.jwinf.platform.logic.map.Point;

public interface Robot
{
    /**
     * Returns the row number of the robot's current position. |
     *
     * DE: <i>Die Reihe, in der sich die Figur im Kachelgitter befindet.</i>
     *
     * @return the row number of the robot's current position
     */
    public int getRow();

    /**
     * Returns the column of the robot's current position. |
     *
     * DE: <i>Die Spalte, in der
     * sich die Figur im Kachelgitter befindet.</i>
     *
     * @return the column of the robot's current position
     */
    public int getCol();

    /**
     * Returns the current position of the robot as a Point object. deutsch
     *u
     * @return the current position of the robot
     */
    public Point getPoint();

    public String[] reportRoute();

    public void printRoute();

    public boolean isOnExit();
    // public void hasOn();
    // public void setIndexes();
    // public void getItemsOn();
    // public void isOn();
    // public void isInFront();
    // public void isInGrid();
    // public void tryToBeOn();
    // public void coordsInFront();
    // public void isCrossing();
    // public void moveItem();
    // public void moveProjectile();
    // public void destroy();
    // public void fall();
    // public void jump();
    // public void withdraw();
    // public void checkContainer();
    // public void drop();
    // public void dropObject();

    /**
     * @see <a href=
     *      "https://github.com/France-ioi/bebras-modules/blob/ec1baf055c7f1c383ce8dfa5d27998463ef5be59/pemFioi/blocklyRobot_lib-1.1.js#L3265-L3268">blocklyRobot_lib-1.1.js
     *      L3265-L3268</a>
     */
    public Movement turnLeft();

    /**
     * @see <a href=
     *      "https://github.com/France-ioi/bebras-modules/blob/ec1baf055c7f1c383ce8dfa5d27998463ef5be59/pemFioi/blocklyRobot_lib-1.1.js#L3270-L3273">blocklyRobot_lib-1.1.js
     *      L3270-L3273</a>
     */
    public Movement turnRight();

    /**
     * @see <a href=
     *      "https://github.com/France-ioi/bebras-modules/blob/ec1baf055c7f1c383ce8dfa5d27998463ef5be59/pemFioi/blocklyRobot_lib-1.1.js#L3275-L3278">blocklyRobot_lib-1.1.js
     *      L3275-L3278</a>
     */
    public Movement turnAround();

    /**
     * @see <a href=
     *      "https://github.com/France-ioi/bebras-modules/blob/ec1baf055c7f1c383ce8dfa5d27998463ef5be59/pemFioi/blocklyRobot_lib-1.1.js#L3280-L3297">
     *      blocklyRobot_lib-1.1.js L3280-L3297</a>
     *
     */
    public Movement forward();
    // public Movement backwards();

    /**
     * Gehe nach rechts in Richtung Osten.
     *
     * @see <a href=
     *      "https://github.com/France-ioi/bebras-modules/blob/ec1baf055c7f1c383ce8dfa5d27998463ef5be59/pemFioi/blocklyRobot_lib-1.1.js#L3346-L3358">blocklyRobot_lib-1.1.js
     *      L3346-L3358</a>
     */
    public Movement east();

    /**
     * Gehe nach oben in Richtung Norden.
     *
     * @see <a href=
     *      "https://github.com/France-ioi/bebras-modules/blob/ec1baf055c7f1c383ce8dfa5d27998463ef5be59/pemFioi/blocklyRobot_lib-1.1.js#L3318-L3330">blocklyRobot_lib-1.1.js
     *      L3318-L3330</a>
     */
    public Movement north();

    /**
     * Gehe nach links in Richtung Westen.
     *
     * @see <a href=
     *      "https://github.com/France-ioi/bebras-modules/blob/ec1baf055c7f1c383ce8dfa5d27998463ef5be59/pemFioi/blocklyRobot_lib-1.1.js#L3360-L3372">blocklyRobot_lib-1.1.js
     *      L3360-L3372</a>
     */
    public Movement west();

    /**
     * Gehe nach unten in Richtung Süden.
     *
     * @see <a href=
     *      "https://github.com/France-ioi/bebras-modules/blob/ec1baf055c7f1c383ce8dfa5d27998463ef5be59/pemFioi/blocklyRobot_lib-1.1.js#L3332-L3344">blocklyRobot_lib-1.1.js
     *      L3332-L3344</a>
     */
    public Movement south();

    /**
     * @see <a href=
     *      "https://github.com/France-ioi/bebras-modules/blob/ec1baf055c7f1c383ce8dfa5d27998463ef5be59/pemFioi/blocklyRobot_lib-1.1.js#L3374-L3376">blocklyRobot_lib-1.1.js
     *      L3374-L3376</a>
     */
    public boolean obstacleInFront();
    // public boolean platformInFront();
    // public boolean platformAbove();
    // public void writeNumber();
    // public void readNumber();
    // public void pushObject();
    // public void shoot();
    // public void connect();
}
