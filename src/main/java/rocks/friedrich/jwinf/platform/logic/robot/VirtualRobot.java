package rocks.friedrich.jwinf.platform.logic.robot;

import static rocks.friedrich.jwinf.platform.logic.Compass.EAST;
import static rocks.friedrich.jwinf.platform.logic.Compass.NORTH;
import static rocks.friedrich.jwinf.platform.logic.Compass.SOUTH;
import static rocks.friedrich.jwinf.platform.logic.Compass.WEST;

import java.util.ArrayList;
import java.util.List;

import rocks.friedrich.jwinf.platform.data.model.ItemData;
import rocks.friedrich.jwinf.platform.logic.Compass;
import rocks.friedrich.jwinf.platform.logic.Task;
import rocks.friedrich.jwinf.platform.logic.item.Item;
import rocks.friedrich.jwinf.platform.logic.item.StackedItems;
import rocks.friedrich.jwinf.platform.logic.level.Level;
import rocks.friedrich.jwinf.platform.logic.map.DirectionalCoords;
import rocks.friedrich.jwinf.platform.logic.map.Context;
import rocks.friedrich.jwinf.platform.logic.map.Coords;

/**
 * Ein Roboter der nicht grafisch dargestellt ist, sondern der sich nur im
 * Speicher befindet. Er kann durch Unit-Tests getestet werden.
 */
public class VirtualRobot implements Robot
{
    private List<MovementListener> movementListeners = new ArrayList<>();

    public ActionLog actionLog;

    public Level level;

    public Context context;

    /**
     * Die Zeile, in der sich der Roboter aktuell befindet.
     */
    public int row;

    /**
     * Die Spalte, in der sich der Roboter aktuell befindet.
     */
    public int col;

    public Compass dir;

    public DirectionalCoords initPosition;

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
        this.context = level.getContext();
        actionLog = new ActionLog();
    }

    public int getRow()
    {
        return row;
    }

    public int getCol()
    {
        return col;
    }

    public Task getTask()
    {
        return level.task;
    }

    public Coords getPoint()
    {
        return new Coords(row, col);
    }

    public void setInitPosition(ItemData init)
    {
        row = init.row;
        col = init.col;
        dir = Compass.fromNumber(init.dir);
        initPosition = new DirectionalCoords(row, col, dir);
    }

    public void resetInitPosition()
    {
        row = initPosition.getRow();
        col = initPosition.getCol();
        dir = initPosition.getDir();
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
                return col < context.getCols() - 1;

            case SOUTH:
                return row < context.getRows() - 1;

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
        return context.isObstacle(row + rowMovement, col + colMovement);
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
    public Coords coordsInFront(Compass dir, int mult)
    {
        int[][] delta = new int[][] { new int[] { 0, 1 }, new int[] { 1, 0 },
                new int[]
                { 0, -1 }, new int[] { -1, 0 } };
        return new Coords(row + delta[dir.getNumber()][0] * mult,
                col + delta[dir.getNumber()][1] * mult);
    }

    public Coords coordsInFront(Compass dir)
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
        return context.get(row, col).has(filter);
    }

    /**
     * @see <a href=
     *      "https://github.com/France-ioi/bebras-modules/blob/ec1baf055c7f1c383ce8dfa5d27998463ef5be59/pemFioi/blocklyRobot_lib-1.1.js#L2872-L2880">blocklyRobot_lib-1.1.js
     *      L2872-L2880</a>
     */
    private boolean hasOn(Coords point, Filter filter)
    {
        return hasOn(point.getRow(), point.getCol(), filter);
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

    /**
     * @see <a href=
     *      "https://github.com/France-ioi/bebras-modules/blob/ec1baf055c7f1c383ce8dfa5d27998463ef5be59/pemFioi/blocklyRobot_lib-1.1.js#L3378-L3381">blocklyRobot_lib-1.1.js
     *      L3378-L3381</a>
     */
    public boolean platformInFront()
    {
        Coords point = coordsInFront(dir);
        return hasOn(point.getRow() + 1, point.getCol(),
                item -> item.isObstacle());
    }

    /**
     * @see <a href=
     *      "https://github.com/France-ioi/bebras-modules/blob/ec1baf055c7f1c383ce8dfa5d27998463ef5be59/pemFioi/blocklyRobot_lib-1.1.js#L3383-L3386">blocklyRobot_lib-1.1.js
     *      L3383-L3386</a>
     */
    public boolean platformAbove()
    {
        return hasOn(row - 1, col, item -> item.isObstacle());
    }

    /**
     * @see <a href=
     *      "https://github.com/France-ioi/bebras-modules/blob/ec1baf055c7f1c383ce8dfa5d27998463ef5be59/pemFioi/blocklyRobot_lib-1.1.js#L3084-L3102">blocklyRobot_lib-1.1.js
     *      L3084-L3102</a>
     */
    private Movement fall(Coords from)
    {
        var mov = reportMovement("fall");
        int fallRow = from.getRow();
        int startRow = from.getRow();
        boolean canFall = context.canFall(fallRow + 1, from.getCol());
        while (canFall)
        {
            fallRow++;
            canFall = context.canFall(fallRow + 1, from.getCol());
        }
        if (!context.isInGrid(fallRow + 1, from.getCol()))
        {
            return mov.setError(ErrorMessages.FALL_FALLS);
        }
        if (fallRow - startRow > getTask().getMaxFallAltitude())
        {
            return mov.setError(ErrorMessages.FALL_WILL_FALL_AND_CRASH);
        }
        return mov.setTo(fallRow, from.getCol(), dir);
    }

    public Movement jump()
    {
        var mov = reportMovement("jump");
        if (!level.task.hasGravity())
        {
            return mov.setError(ErrorMessages.JUMP_WITHOUT_GRAVITY);
        }
        if (!context.isInGrid(row - 1, col))
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

    /**
     * @see <a href=
     *      "https://github.com/France-ioi/bebras-modules/blob/ec1baf055c7f1c383ce8dfa5d27998463ef5be59/pemFioi/blocklyRobot_lib-1.1.js#L3125-L3164">blocklyRobot_lib-1.1.js
     *      L3125-L3164</a>
     */
    public ItemRelocation withdraw()
    {
        Item item = getOnItems().withdraw();
        var action = reportItemRelocation("withdraw", item);
        if (item == null)
        {
            return (ItemRelocation) action
                    .setError(ErrorMessages.WITHDRAWABLES_NOTHING_TO_PICK_UP);
        }
        if (level.task.getBagSize() < context.getBag().size() + 1)
        {
            return (ItemRelocation) action
                    .setError(ErrorMessages.WITHDRAWABLES_TOO_MANY_OBJECTS);
        }
        item.withdraw();
        context.getBag().add(item);
        return action;
    }

    private ItemRelocation reportItemRelocation(String name, Item item)
    {
        var action = new ItemRelocation(name, item);
        actionLog.add(action);
        return action;
    }

    private ItemRelocation reportItemRelocation(String name)
    {
        var action = new ItemRelocation(name);
        actionLog.add(action);
        return action;
    }

    public Item dropWithdrawable(int itemNum)
    {
        Item item = null;
        var action = reportItemRelocation("dropWithdrawable", null);
        if (onContainer())
        {
            item = context.drop(row, col, itemNum);
        }
        action.setItem(item);
        return item;
    }

    public Item drop()
    {
        var item = context.drop(row, col);
        reportItemRelocation("dropWithdrawable", item);
        return item;
    }

    private ItemRelocation dropPlatform(Coords coords, String name)
    {
        ItemRelocation action = reportItemRelocation(name);
        if (getTask().getNbPlatforms() == 0)
        {
            return (ItemRelocation) action.setError(
                    ErrorMessages.PLATFORMS_FAILURE_NOT_ENOUGH_PLATFORM);
        }
        if (context.isObstacle(coords))
        {
            return (ItemRelocation) action
                    .setError(ErrorMessages.PLATFORMS_FAILURE_DROP_PLATFORM);
        }
        Item platform = context.drop(coords, "platform");
        return action.setItem(platform);
    }

    /**
     * @see <a href=
     *      "https://github.com/France-ioi/bebras-modules/blob/ec1baf055c7f1c383ce8dfa5d27998463ef5be59/pemFioi/blocklyRobot_lib-1.1.js#L2312-L2328">blocklyRobot_lib-1.1.js
     *      L2312-L2328</a>
     */
    public ItemRelocation dropPlatformInFront()
    {
        return dropPlatform(coordsInFront(dir).south(), "dropPlatformInFront");
    }

    /**
     * @see <a href=
     *      "https://github.com/France-ioi/bebras-modules/blob/ec1baf055c7f1c383ce8dfa5d27998463ef5be59/pemFioi/blocklyRobot_lib-1.1.js#L2330-L2346">blocklyRobot_lib-1.1.js
     *      L2330-L2346</a>
     */
    public ItemRelocation dropPlatformAbove()
    {
        return dropPlatform(getPoint().north(), "dropPlatformInFront");
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
        actionLog.add(mov);
        return mov;
    }

    public String[] reportActions()
    {
        return actionLog.toArray();
    }

    public void printActions()
    {
        actionLog.printActions();
    }

    private Movement forOrBackwards(String name, Compass direction)
    {
        var mov = reportMovement(name);
        if (tryToBeOn(dir))
        {
            Coords inFront = coordsInFront(direction);
            if (getTask().hasGravity())
            {
                Movement fallMov = fall(inFront);
                if (fallMov.getTo() != null
                        && fallMov.getTo().getRow() != inFront.getRow())
                {
                    mov.next = fallMov;
                    row = fallMov.getTo().getRow();
                    col = fallMov.getTo().getCol();
                    return mov.setTo(inFront);
                }
            }
            row = inFront.getRow();
            col = inFront.getCol();
            numberOfMovements++;
        }
        return mov.setTo();
    }

    /**
     * @see <a href=
     *      "https://github.com/France-ioi/bebras-modules/blob/ec1baf055c7f1c383ce8dfa5d27998463ef5be59/pemFioi/blocklyRobot_lib-1.1.js#L3280-L3297">
     *      blocklyRobot_lib-1.1.js L3280-L3297</a>
     */
    public Movement forward()
    {
        return forOrBackwards("forward", dir);
    }

    /**
     * @see <a href=
     *      "https://github.com/France-ioi/bebras-modules/blob/ec1baf055c7f1c383ce8dfa5d27998463ef5be59/pemFioi/blocklyRobot_lib-1.1.js#L3299-L3316">
     *      blocklyRobot_lib-1.1.js L3299-L3316</a>
     *
     */
    public Movement backwards()
    {
        return forOrBackwards("backwards", WEST);
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
        return context.get(row, col);
    }

    public boolean onContainer()
    {
        return getOnItems().isContainer();
    }

    public boolean onWithdrawable()
    {
        return getOnItems().isWithdrawable();
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
