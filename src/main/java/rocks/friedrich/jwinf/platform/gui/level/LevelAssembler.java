package rocks.friedrich.jwinf.platform.gui.level;

import java.lang.reflect.InvocationTargetException;

import ea.Scene;
import ea.Vector;
import rocks.friedrich.jwinf.platform.data.model.ItemData;
import rocks.friedrich.jwinf.platform.gui.Color;
import rocks.friedrich.jwinf.platform.gui.map.Grid;
import rocks.friedrich.jwinf.platform.gui.map.TileMap;
import rocks.friedrich.jwinf.platform.gui.robot.ImageRobot;
import rocks.friedrich.jwinf.platform.logic.level.Level;
import rocks.friedrich.jwinf.platform.logic.robot.RobotWrapper;

/**
 * Klasse, die eine Version einer Trainingsaufgabe zusammenbaut.
 */
public class LevelAssembler
{
    Level level;

    public LevelAssembler(Level level)
    {
        this.level = level;
    }

    public TileMap createTileMap()
    {
        TileMap tileMap = new TileMap(level.cols, level.rows, "images");
        for (ItemData tile : level.task.items.all())
        {
            if (tile.relPath != null)
            {
                tileMap.registerImage(tile.letter, tile.relPath, tile.name);
            }
        }
        for (int row = 0; row < level.map.rows; row++)
        {
            for (int col = 0; col < level.map.cols; col++)
            {
                int num = level.data.tiles[row][col];
                ItemData tile = level.task.items.get(num);
                if (tile != null)
                {
                    tileMap.setTile(col, row, tile.letter);
                }
            }
        }
        return tileMap;
    }

    public Grid createGrid()
    {
        Grid grid = new Grid(level.cols, level.rows);
        grid.setColor(new Color(level.getGridColor()));
        grid.setBackground(new Color(level.task.getBackgroundColor()));
        return grid;
    }

    public RobotWrapper createRobot()
            throws InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException,
            NoSuchMethodException, SecurityException, ClassNotFoundException
    {
        String className = "rocks.friedrich.jwinf.tasks.en.conditionals.candle.Robot";
        if (level.task.data.packagePath != null)
        {
            className = "rocks.friedrich.jwinf.en.tasks.%s.Robot"
                    .formatted(level.task.data.packagePath.replace("/", "."));
        }
        RobotWrapper robot = RobotWrapper.class.getClassLoader()
                .loadClass(className).asSubclass(RobotWrapper.class)
                .getDeclaredConstructor().newInstance();
        var context = level.createContext();
        robot.actor = new ImageRobot("images/candle/robot.png", context.robot);
        return robot;
    }

    /**
     * @param x - x-Koordinate der linken unteren Ecke
     * @param y - y-Koordinate der linken unteren Ecke
     */
    public AssembledLevel placeActorsInScene(Scene scene, float x, float y)
    {
        AssembledLevel l = new AssembledLevel();
        l.level = level;
        l.x = x;
        l.y = y;
        l.scene = scene;
        l.grid = createGrid();
        l.grid.setPosition(x - 0.5f, y - 0.5f);
        scene.add(l.grid);
        l.tileMap = createTileMap().container;
        l.tileMap.setPosition(x - 0.5f, y - 0.5f);
        scene.add(l.tileMap);
        level.map.setPosition(x, y);
        try
        {
            l.robot = createRobot();
            Vector robotPosition = level.map.translateToVector(
                    level.getInitItem().row, level.getInitItem().col);
            ImageRobot robot = (ImageRobot) l.robot.actor;
            robot.setCenter(robotPosition.getX(), robotPosition.getY());
            scene.add(robot);
        }
        catch (InstantiationException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException
                | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return l;
    }
}
