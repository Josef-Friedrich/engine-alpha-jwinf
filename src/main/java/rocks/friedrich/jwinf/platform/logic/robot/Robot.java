package rocks.friedrich.jwinf.platform.logic.robot;

import rocks.friedrich.jwinf.platform.logic.item.Item;
import rocks.friedrich.jwinf.platform.logic.map.Point;
// EN
//   row: "robot's row",
//   col: "robot's column",
//   wait: "wait",
//   north: "move up",
//   south: "move down",
//   east: "move right",
//   west: "move left",
//   left: "turn left",
//   right: "turn right",
//   turnAround: "turn around",
//   forward: "move forward",
//   backwards: "move backwards",
//   jump: "jump",
//   obstacleInFront: "obstacle ahead",
//   obstacleEast: "obstacle on the right",
//   obstacleWest: "obstacle on the left",
//   obstacleNorth: "obstacle above",
//   obstacleSouth: "obstacle below",
//   obstacleRight: "obstacle on the right",
//   obstacleLeft: "obstacle on the left",
//   gridEdgeEast: "grid edge on the right",
//   gridEdgeWest: "grid edge on the left",
//   gridEdgeNorth: "grid edge above",
//   gridEdgeSouth: "grid edge below",
//   platformInFront: "platform ahead",
//   platformAbove: "platform above",
//   withdrawObject: "pick the object",
//   dropObject: "drop the object",
//   onObject: "on an object",
//   onContainer: "on a container",
//   onNumber: "on a number",
//   onWritable: "on a blackboard",
//   onLauncher: "on a laser emitter",
//   writeNumber: "write the number",
//   readNumber: "number in the cell",
//   pushObject: "push the object",
//   pushableInFront: "pushable object ahead",
//   shoot: "shoot a laser in direction %1",
//   shoot_noShadow: "shoot a laser in direction %1",
//   shootCondition:
//     "laser shot returning to starting point in direction %1",
//   shootCondition_noShadow:
//     "laser shot returning to starting point in direction %1",
//   connect: "plug a wire",
//   onMale: "to a male plug",
//   onFemale: "to a female plug",
//   dropPlatformInFront: "drop platform in front",
//   dropPlatformAbove: "drop platform above",
// DE
//   row: "Zeile des Roboters",
//   col: "Spalte des Roboters",
//   north: "gehe nach oben",
//   south: "gehe nach unten",
//   east: "gehe nach rechts",
//   west: "gehe nach links",
//   left: "drehe nach links",
//   right: "drehe nach rechts",
//   turnAround: "drehe um",
//   forward: "gehe",
//   backwards: "gehe rückwärts",
//   jump: "springe",
//   obstacleInFront: "vor Hindernis",
//   obstacleEast: "Hindernis rechts",
//   obstacleWest: "Hindernis links",
//   obstacleNorth: "Hindernis oben",
//   obstacleSouth: "Hindernis unten",
//   obstacleRight: "Hindernis rechts",
//   obstacleLeft: "Hindernis links",
//   gridEdgeAbove: "unter Rand des Gitters",
//   gridEdgeBelow: "über Rand des Gitters",
//   gridEdgeEast: "links vom Gitterrand",
//   gridEdgeWest: "rechts vom Gitterrand",
//   platformInFront: "vor Plattform",
//   platformAbove: "Plattform darüber",
//   withdrawObject: "hebe Objekt auf",
//   dropObject: "lege Objekt ab",
//   onObject: "auf Objekt",
//   onContainer: "auf Kiste",
//   onNumber: "auf Zahl",
//   onWritable: "auf Tafel",
//   onLauncher: "sur un lanceur laser", // TODO :: translate
//   writeNumber: "schreibe Zahl",
//   readNumber: "Zahl auf dem Feld",
//   pushObject: "schiebe Kiste",
//   pushableInFront: "vor Kiste",
//   shoot: "schieße Laser in Richtung %1",
//   shoot_noShadow: "schieße Laser in Richtung %1",
//   shootCondition: "Rückkehr von der Schießrichtung %1",
//   shootCondition_noShadow: "Rückkehr von der Schießrichtung %1",

public interface Robot
{
    /**
     * Returns the row number of the robot's current position. |
     *
     * DE: <i>Die Reihe, in der sich die Figur im Gitter befindet.</i>
     *
     * @return the row number of the robot's current position
     */
    public int getRow();

    /**
     * Returns the column of the robot's current position. |
     *
     * DE: <i>Die Spalte, in der sich die Figur im Gitter befindet.</i>
     *
     * @return the column of the robot's current position
     */
    public int getCol();

    /**
     * Returns the current position of the robot as a Point object. deutsch u
     *
     * @return the current position of the robot
     */
    public Point getPoint();

    public String[] reportRoute();

    public void printRoute();

    public boolean onExit();

    /**
     * @see <a href=
     *      "https://github.com/France-ioi/bebras-modules/blob/ec1baf055c7f1c383ce8dfa5d27998463ef5be59/pemFioi/blocklyRobot_lib-1.1.js#L4477-L4502">blocklyRobot_lib-1.1.js
     *      L4477-L4502</a>
     */
    public boolean onPaint();
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

    /**
     * 
     * @see <a href=
     *      "https://github.com/France-ioi/bebras-modules/blob/ec1baf055c7f1c383ce8dfa5d27998463ef5be59/pemFioi/blocklyRobot_lib-1.1.js#L3104-L3123">blocklyRobot_lib-1.1.js
     *      L3104-L3123</a>
     */
    public Movement jump();

    /**
     * @see <a href=
     *      "https://github.com/France-ioi/bebras-modules/blob/ec1baf055c7f1c383ce8dfa5d27998463ef5be59/pemFioi/blocklyRobot_lib-1.1.js#L3125-L3164">blocklyRobot_lib-1.1.js
     *      L3125-L3164</a>
     */
    public Item withdraw();
    // public void checkContainer();
    // public void drop();

    /**
     * @see <a href=
     *      "https://github.com/France-ioi/bebras-modules/blob/ec1baf055c7f1c383ce8dfa5d27998463ef5be59/pemFioi/blocklyRobot_lib-1.1.js#L3235-L3263">blocklyRobot_lib-1.1.js
     *      L3235-L3263</a>
     */
    public Item dropObject(int itemNum);

    /**
     * https://github.com/France-ioi/bebras-modules/blob/ec1baf055c7f1c383ce8dfa5d27998463ef5be59/pemFioi/blocklyRobot_lib-1.1.js#L2312-L2328
     * 
     * @return
     */
    public Item dropPlatformInFront();

    /**
     * https://github.com/France-ioi/bebras-modules/blob/ec1baf055c7f1c383ce8dfa5d27998463ef5be59/pemFioi/blocklyRobot_lib-1.1.js#L2330-L2346
     * 
     * @return
     */
    public Item dropPlatformAbove();

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

    /**
     * @see <a href=
     *      "https://github.com/France-ioi/bebras-modules/blob/ec1baf055c7f1c383ce8dfa5d27998463ef5be59/pemFioi/blocklyRobot_lib-1.1.js#L3299-L3316">
     *      blocklyRobot_lib-1.1.js L3299-L3316</a>
     *
     */
    public Movement backwards();

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
