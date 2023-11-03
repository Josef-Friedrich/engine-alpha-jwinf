package rocks.friedrich.jwinf.engine.scenes;

import ea.Scene;
import rocks.friedrich.jwinf.engine.map.TileMap;
import rocks.friedrich.jwinf.engine.task.Task;

public class AllLevels extends Scene {

  public Task task;

  int x = 0;

  public AllLevels(Task task) {
    this.task = task;
    task.getLevels().forEach((difficulty, levels) -> {
      levels.forEach((level) -> {
        TileMap map = level.createTileMap();
        map.container.setPosition(x, 0);
        add(map.container);
        x += 10;;
      });
    });
  }
}
