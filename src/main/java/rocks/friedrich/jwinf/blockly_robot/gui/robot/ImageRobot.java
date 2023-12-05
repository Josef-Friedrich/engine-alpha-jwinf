package rocks.friedrich.jwinf.blockly_robot.gui.robot;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

import ea.Vector;
import ea.actor.Image;
import ea.animation.Interpolator;
import ea.animation.ValueAnimator;
import ea.animation.interpolation.SinusFloat;
import rocks.friedrich.jwinf.blockly_robot.gui.State;
import rocks.friedrich.jwinf.blockly_robot.gui.level.AssembledLevel;
import rocks.friedrich.jwinf.blockly_robot.logic.context.Coords;
import rocks.friedrich.jwinf.blockly_robot.logic.item.Item;
import rocks.friedrich.jwinf.blockly_robot.logic.robot.ItemRelocation;
import rocks.friedrich.jwinf.blockly_robot.logic.robot.Movement;
import rocks.friedrich.jwinf.blockly_robot.logic.robot.Robot;
import rocks.friedrich.jwinf.blockly_robot.logic.robot.VirtualRobot;

public class ImageRobot extends Image implements Robot
{
    private VirtualRobot virtual;

    private AssembledLevel level;

    /**
     * Damit keine neue Bewegung gestartet werden kann, bevor nicht die alte
     * fertig abgelaufen ist.
     */
    private boolean inMotion = false;

    protected float speed = 1f;

    public ImageRobot(String filepath, VirtualRobot virtual,
            AssembledLevel level)
    {
        super(filepath, 1, 1);
        this.virtual = virtual;
        this.level = level;
    }

    public int getRow()
    {
        return virtual.getRow();
    }

    public int getCol()
    {
        return virtual.getCol();
    }

    public Coords getCoords()
    {
        return virtual.getCoords();
    }

    public String[] reportActions()
    {
        return virtual.reportActions();
    }

    public void printActions()
    {
        virtual.printActions();
    }

    public boolean onExit()
    {
        return virtual.onExit();
    }

    public boolean onPaint()
    {
        return virtual.onPaint();
    }

    public boolean obstacleInFront()
    {
        return virtual.obstacleInFront();
    }

    public boolean platformInFront()
    {
        return virtual.platformInFront();
    }

    public boolean platformAbove()
    {
        return virtual.platformAbove();
    }

    private void relocateAnimated(Vector to)
    {
        if (inMotion)
        {
            return;
        }
        inMotion = true;
        Vector from = getCenter();
        Vector vector = new Vector(from, to);
        float duration = (float) 1 / speed / 2;
        animate(duration, progress -> {
            setCenter(from.add(vector.multiply(progress)));
        });
        inMotion = false;
    }

    private Movement performMovement(Movement movement)
    {
        if (movement.getRotation() != 0)
        {
            rotateByAnimated(movement.getRotation() * -90);
        }
        if (movement.isRelocated())
        {
            relocateAnimated(level.translate.toVector(movement.getTo()));
        }
        if (movement.hasNext())
        {
            performMovement(movement.getNext());
        }
        if (movement.hasError())
        {
            wiggleAnimated();
        }
        return movement;
    }

    /**
     * Gehe einen Pixelmeter in Richtung der aktuellen Rotation.
     */
    public Movement forward()
    {
        return performMovement(virtual.forward());
    }

    public Movement backwards()
    {
        return performMovement(virtual.backwards());
    }

    public Movement east()
    {
        return performMovement(virtual.east());
    }

    public Movement south()
    {
        return performMovement(virtual.south());
    }

    public Movement west()
    {
        return performMovement(virtual.west());
    }

    public Movement north()
    {
        return performMovement(virtual.north());
    }

    public boolean onContainer()
    {
        return virtual.onContainer();
    }

    public boolean onWithdrawable()
    {
        return virtual.onWithdrawable();
    }

    private void wait(double seconds)
    {
        try
        {
            Thread.sleep((long) (1000 * seconds));
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
    }

    private void wiggleAnimated()
    {
        this.wiggleAnimated(0.3f);
    }

    private void wiggleAnimated(float duration)
    {
        if (inMotion)
        {
            return;
        }
        inMotion = true;
        float rotation = getRotation();
        Vector center = getCenter();
        wait(0.1);
        animate(duration, progress -> {
            setRotation(rotation + progress);
            setCenter(center);
        }, new SinusFloat(0, 45));
        wait(0.1);
        inMotion = false;
    }

    private void rotateByAnimated(double degree)
    {
        if (inMotion)
        {
            return;
        }
        inMotion = true;
        Vector center = getCenter();
        // case EAST: 0;
        // case NORTH: 90;
        // case WEST: 180;
        // case SOUTH: 270;
        // To avoid high rotation numbers
        float start = getRotation() % 360;
        setRotation(start);
        float duration = 1 / speed / 4;
        animate(duration, progress -> {
            setRotation(start + progress * (float) degree);
            setCenter(center);
        });
        inMotion = false;
    }

    public Movement jump()
    {
        return performMovement(virtual.jump());
    }

    public ItemRelocation withdraw()
    {
        return virtual.withdraw();
    }

    private Item paintItem(Item item)
    {
        if (item != null)
        {
            item.setController(level.getItemController(item));
            item.add();
        }
        return item;
    }

    private ItemRelocation performItemRelocation(ItemRelocation relocation)
    {
        if (relocation != null)
        {
            wait(0.5 / speed);
            paintItem(relocation.getItem());
            wait(0.5 / speed);
        }
        return relocation;
    }

    public Item dropWithdrawable(int itemNum)
    {
        return paintItem(virtual.dropWithdrawable(itemNum));
    }

    public Item drop()
    {
        Item item = virtual.drop();
        item.add();
        return item;
    }

    public ItemRelocation dropPlatformInFront()
    {
        return performItemRelocation(virtual.dropPlatformInFront());
    }

    public ItemRelocation dropPlatformAbove()
    {
        return performItemRelocation(virtual.dropPlatformAbove());
    }

    /**
     * Drehe um 90 Grad nach links.
     */
    public Movement turnLeft()
    {
        return performMovement(virtual.turnLeft());
    }

    /**
     * Drehe um 90 Grad nach rechts.
     */
    public Movement turnRight()
    {
        return performMovement(virtual.turnRight());
    }

    public Movement turnAround()
    {
        return performMovement(virtual.turnAround());
    }

    private void animate(float duration, Consumer<Float> setter,
            Interpolator<Float> interpolator)
    {
        CompletableFuture<Void> future = new CompletableFuture<>();
        ValueAnimator<Float> animator = new ValueAnimator<>(duration, setter,
                interpolator, this);
        animator.addCompletionListener(value -> {
            setter.accept(value);
            future.complete(null);
        });
        addFrameUpdateListener(animator);
        try
        {
            future.get();
        }
        catch (InterruptedException | ExecutionException e)
        {
            throw new RuntimeException(e);
        }
    }

    private void animate(float duration, Consumer<Float> setter)
    {
        animate(duration, setter, State.interpolator);
    }
}
