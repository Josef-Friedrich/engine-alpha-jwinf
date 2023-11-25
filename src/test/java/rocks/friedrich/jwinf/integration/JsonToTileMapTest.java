package rocks.friedrich.jwinf.integration;

import ea.Scene;
import rocks.friedrich.jwinf.platform.gui.Controller;
import rocks.friedrich.jwinf.platform.gui.level.LevelAssembler;
import rocks.friedrich.jwinf.platform.logic.Task;

public class JsonToTileMapTest extends Scene
{
    public static void main(String[] args)
    {
        new JsonToTileMapTest();
    }

    public JsonToTileMapTest()
    {
        Controller.launchScene(this);
        var task = Task
                .loadByRelPath("conditionals_excercises/light_all_candles");
        var map = new LevelAssembler(task.getLevel(0)).createTileMap();
        add(map.container);
    }
}
