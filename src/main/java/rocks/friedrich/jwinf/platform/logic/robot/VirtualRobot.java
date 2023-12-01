package rocks.friedrich.jwinf.platform.logic.robot;

import static rocks.friedrich.jwinf.platform.logic.Compass.EAST;
import static rocks.friedrich.jwinf.platform.logic.Compass.NORTH;
import static rocks.friedrich.jwinf.platform.logic.Compass.SOUTH;
import static rocks.friedrich.jwinf.platform.logic.Compass.WEST;

import java.util.ArrayList;
import java.util.List;

import rocks.friedrich.jwinf.platform.data.model.ItemData;
import rocks.friedrich.jwinf.platform.logic.Compass;
import rocks.friedrich.jwinf.platform.logic.item.Item;
import rocks.friedrich.jwinf.platform.logic.item.StackedItems;
import rocks.friedrich.jwinf.platform.logic.level.Level;
import rocks.friedrich.jwinf.platform.logic.map.DirectionalPoint;
import rocks.friedrich.jwinf.platform.logic.map.LevelMap;
import rocks.friedrich.jwinf.platform.logic.map.Point;

/**
 * Ein Roboter der nicht grafisch dargestellt ist, sondern der sich nur im
 * Speicher befindet. Er kann durch Unit-Tests getestet werden.
 */
public class VirtualRobot implements Robot
{
    private List<MovementListener> movementListeners = new ArrayList<>();

    public Route route;

    public Level level;

    public LevelMap map;

    /**
     * Die Zeile, in der sich der Roboter aktuell befindet.
     */
    public int row;

    /**
     * Die Spalte, in der sich der Roboter aktuell befindet.
     */
    public int col;

    public Compass dir;

    public DirectionalPoint initPosition;

    /**
     * @see <a href=
     *      "https://github.com/France-ioi/bebras-modules/blob/ec1baf055c7f1c383ce8dfa5d27998463ef5be59/pemFioi/blocklyRobot_lib-1.1.js#L2456">blocklyRobot_lib-1.1.js
     *      L2456</a>
     */
    public int numberOfMovements;

    /**
     * Gibt an, ob die letzte Bewegung erfolgreich war.
     */
    public boolean movementSuccessful;

    public VirtualRobot(Level level)
    {
        this.level = level;
        this.map = level.getMap();
        route = new Route();
    }

    public int getRow()
    {
        return row;
    }

    public int getCol()
    {
        return col;
    }

    public Point getPoint()
    {
        return new Point(row, col);
    }

    public void setInitPosition(ItemData init)
    {
        row = init.row;
        col = init.col;
        dir = Compass.fromNumber(init.dir);
        initPosition = new DirectionalPoint(row, col, dir);
    }

    public void resetInitPosition()
    {
        row = initPosition.row;
        col = initPosition.col;
        dir = initPosition.dir;
    }

    public void addMovementListener(MovementListener listener)
    {
        movementListeners.add(listener);
    }

    public void addGridEdgesMovementListener()
    {
        addMovementListener((Compass direction) -> {
            switch (direction)
            {
            case EAST:
                return col < map.cols - 1;

            case SOUTH:
                return row < map.rows - 1;

            case WEST:
                return col > 0;

            case NORTH:
                return row > 0;

            default:
                return true;
            }
        });
    }

    protected boolean isInFrontOfObstacle(Compass direction)
    {
        int rowMovement = 0;
        int colMovement = 0;
        switch (direction)
        {
        case EAST:
            colMovement = 1;
            break;

        case SOUTH:
            rowMovement = 1;
            break;

        case WEST:
            colMovement = -1;
            break;

        case NORTH:
            rowMovement = -1;
            break;

        default:
        }
        return map.isObstacle(row + rowMovement, col + colMovement);
    }

    public boolean isInFrontOfObstacle()
    {
        return isInFrontOfObstacle(dir);
    }

    public void addObstaclesMovementListener()
    {
        addMovementListener(
                (Compass direction) -> !isInFrontOfObstacle(direction));
    }

    public void addDefaultMovementListener()
    {
        addGridEdgesMovementListener();
        addObstaclesMovementListener();
    }

    /**
     * @see <a href=
     *      "https://github.com/France-ioi/bebras-modules/blob/ec1baf055c7f1c383ce8dfa5d27998463ef5be59/pemFioi/blocklyRobot_lib-1.1.js#L2913-L2921">blocklyRobot_lib-1.1.js
     *      L2913-L2921</a>
     */
    public boolean isInGrid(int row, int col)
    {
        return row < 0 || col < 0 || row >= map.getRows()
                || col >= map.getCols();
    }

    /**
     * @see <a href=
     *      "https://github.com/France-ioi/bebras-modules/blob/ec1baf055c7f1c383ce8dfa5d27998463ef5be59/pemFioi/blocklyRobot_lib-1.1.js#L2923-L2944">blocklyRobot_lib-1.1.js
     *      L2923-L2944</a>
     */
    public boolean tryToBeOn(Compass direction)
    {
        boolean result = true;
        for (MovementListener listener : this.movementListeners)
        {
            if (!listener.allowMovement(direction))
            {
                result = false;
                break;
            }
        }
        movementSuccessful = result;
        return result;
    }

    /**
     * @param dir  Die Himmelsrichtung
     * @param mult Multiplikation
     *
     * @see <a href=
     *      "https://github.com/France-ioi/bebras-modules/blob/ec1baf055c7f1c383ce8dfa5d27998463ef5be59/pemFioi/blocklyRobot_lib-1.1.js#L2946-L2958">blocklyRobot_lib-1.1.js
     *      L2946-L2958</a>
     */
    public Point coordsInFront(Compass dir, int mult)
    {
        int[][] delta = new int[][] { new int[] { 0, 1 }, new int[] { 1, 0 },
                new int[]
                { 0, -1 }, new int[] { -1, 0 } };
        return new Point(row + delta[dir.getNumber()][0] * mult,
                col + delta[dir.getNumber()][1] * mult);
    }

    public Point coordsInFront(Compass dir)
    {
        return coordsInFront(dir, 1);
    }

    /**
     * @see <a href=
     *      "https://github.com/France-ioi/bebras-modules/blob/ec1baf055c7f1c383ce8dfa5d27998463ef5be59/pemFioi/blocklyRobot_lib-1.1.js#L2872-L2880">blocklyRobot_lib-1.1.js
     *      L2872-L2880</a>
     */
    private boolean hasOn(int row, int col, Filter filter)
    {
        return map.get(row, col).has(filter);
    }

    /**
     * @see <a href=
     *      "https://github.com/France-ioi/bebras-modules/blob/ec1baf055c7f1c383ce8dfa5d27998463ef5be59/pemFioi/blocklyRobot_lib-1.1.js#L2872-L2880">blocklyRobot_lib-1.1.js
     *      L2872-L2880</a>
     */
    private boolean hasOn(Point point, Filter filter)
    {
        return hasOn(point.row, point.col, filter);
    }

    /**
     * @see <a href=
     *      "https://github.com/France-ioi/bebras-modules/blob/ec1baf055c7f1c383ce8dfa5d27998463ef5be59/pemFioi/blocklyRobot_lib-1.1.js#L2908-L2911">blocklyRobot_lib-1.1.js
     *      L2908-L2911</a>
     */
    private boolean isInFront(Filter filter)
    {
        return hasOn(coordsInFront(dir), filter);
    }

    /**
     * @see <a href=
     *      "https://github.com/France-ioi/bebras-modules/blob/ec1baf055c7f1c383ce8dfa5d27998463ef5be59/pemFioi/blocklyRobot_lib-1.1.js#L3374-L3376">blocklyRobot_lib-1.1.js
     *      L3374-L3376</a>
     */
    public boolean obstacleInFront()
    {
        return isInFront(item -> item.isObstacle());
    }

    public Movement jump()
    {
        var mov = reportMovement("jump");
        if (!level.task.hasGravity())
        {
            return mov.setError(ErrorMessages.JUMP_WITHOUT_GRAVITY);
        }
        if (!isInGrid(row - 1, col))
        {
            return mov.setError(ErrorMessages.JUMP_OUTSIDE_GRID);
        }
        if (hasOn(row - 2, col,
                item -> item.isObstacle() || item.isProjectile()))
        {
            return mov.setError(ErrorMessages.JUMP_OBSTACLE_BLOCKING);
        }
        if (!hasOn(row - 1, col, item -> item.isObstacle()))
        {
            return mov.setError(ErrorMessages.JUMP_OBSTACLE_BLOCKING);
        }
        row -= 2;
        return mov.setTo();
    }

    public Item withdraw()
    {
        Item item = getOnItems().withdraw();
        item.withdraw();
        return item;
    }

    public Item dropObject(int itemNum)
    {
        if (onContainer())
        {
            Item item = map.add(getRow(), getCol(), itemNum);
            return item;
        }
        return null;
    }

    public Item dropPlatformInFront()
    {
        return null;
    }

    public Item dropPlatformAbove()
    {
        return null;
    }

    public Movement turnLeft()
    {
        var mov = reportMovement("turnLeft");
        dir = dir.rotate(3);
        return mov.setTo();
    }

    public Movement turnRight()
    {
        var mov = reportMovement("turnRight");
        dir = dir.rotate(1);
        return mov.setTo();
    }

    public Movement turnAround()
    {
        var mov = reportMovement("turnAround");
        dir = dir.rotate(2);
        return mov.setTo();
    }

    /**
     * Zeichne die Bewegung auf, die der Roboter macht.
     */
    public Movement reportMovement(String name)
    {
        var mov = new Movement(name, this);
        route.add(mov);
        return mov;
    }

    public String[] reportRoute()
    {
        return route.toArray();
    }

    public void printRoute()
    {
        route.printRoute();
    }

    public Movement forward()
    {
        var mov = reportMovement("forward");
        if (tryToBeOn(dir))
        {
            Point point = coordsInFront(dir);
            row = point.getRow();
            col = point.getCol();
            numberOfMovements++;
        }
        return mov.setTo();
    }

    public Movement backwards()
    {
        var mov = reportMovement("backwards");
        if (tryToBeOn(WEST))
        {
            Point point = coordsInFront(WEST);
            row = point.getRow();
            col = point.getCol();
            numberOfMovements++;
        }
        return mov.setTo();
    }

    public Movement east()
    {
        var mov = reportMovement("east");
        if (tryToBeOn(EAST))
        {
            col++;
            dir = EAST;
            numberOfMovements++;
        }
        return mov.setTo();
    }

    public Movement north()
    {
        var mov = reportMovement("north");
        if (tryToBeOn(NORTH))
        {
            row--;
            dir = NORTH;
            numberOfMovements++;
        }
        return mov.setTo();
    }

    public Movement west()
    {
        var mov = reportMovement("west");
        if (tryToBeOn(WEST))
        {
            col--;
            dir = WEST;
            numberOfMovements++;
        }
        return mov.setTo();
    }

    public Movement south()
    {
        var mov = reportMovement("south");
        if (tryToBeOn(SOUTH))
        {
            row++;
            dir = SOUTH;
            numberOfMovements++;
        }
        return mov.setTo();
    }

    /**
     * Gib das Ding zurück, auf dem sich der Roboter gerade befindet.
     */
    private StackedItems getOnItems()
    {
        return map.get(row, col);
    }

    public boolean onContainer()
    {
        return getOnItems().isContainer();
    }

    public boolean onExit()
    {
        return getOnItems().isExit();
    }

    public boolean onPaint()
    {
        return getOnItems().isPaint();
    }

    public String toString()
    {
        return "VirtualRobot [row=%s, col=%s, dir=%s]".formatted(row, col, dir);
    }
}
