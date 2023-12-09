package rocks.friedrich.jwinf.blockly_robot.gui.map;

import ea.Game;
import ea.Scene;
import rocks.friedrich.jwinf.blockly_robot.logic.Task;

public class ItemMapPainterTest extends Scene
{
    public static void main(String[] args)
    {
        new ItemMapPainterTest();
    }

    public ItemMapPainterTest()
    {
        if (!Game.isRunning())
        {
            Game.start(1000, 1000, this);
            Game.setDebug(true);
        }
        else
        {
            Game.transitionToScene(this);
        }
        Task task = Task
                .loadByTaskPath("conditionals_excercises/find_the_destination");
        ItemMapPainter painter = new ItemMapPainter(task.getContextData());
        painter.paint(this);
    }
}
