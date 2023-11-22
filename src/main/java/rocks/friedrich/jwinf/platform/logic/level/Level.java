package rocks.friedrich.jwinf.platform.logic.level;

import rocks.friedrich.jwinf.platform.data.model.ItemData;
import rocks.friedrich.jwinf.platform.data.model.LevelData;
import rocks.friedrich.jwinf.platform.logic.Task;
import rocks.friedrich.jwinf.platform.logic.robot.VirtualRobot;

/**
 * Ein Test bzw. eine Version einer Trainingsaufgabe in einer bestimmen
 * Schwierigkeit.
 *
 * Eine Trainingsaufgabe kann mehrere Versionen unterschiedlicher
 * Schwierigkeitsgrade haben, z. B. eine Zweistern- (<code>Version**</code>,
 * <em>easy</em>), Dreistern-(<code>Version***</code>, <em>medium</em>), und
 * eine Vierstern-Version (<code>Version****</code>, <em>hard</em>).
 */
public class Level {

  public LevelData data;

  public Task task;

  public Difficulty difficulty;

  public int testNo;

  /**
   * Zum Beispiel „Der Roboter soll den Edelstein einsammeln. Sobald er das Feld
   * mit dem
   * Edelstein erreicht, wird dieser automatisch eingesammelt.“
   */
  public String intro;

  public int cols;

  public int rows;

  public LevelMap map;

  public Level(LevelData data, Task task) {
    this.data = data;
    this.task = task;
    map = new LevelMap(data.tiles, task.items);
    cols = map.cols;
    rows = map.rows;
    difficulty = data.difficulty;
    testNo = data.testNo;
  }

  public LevelContext createContext() {
    var c = new LevelContext();
    c.level = this;
    c.robot = new VirtualRobot(map);
    c.robot.addDefaultMovementListener();
    c.robot.setInitPosition(getInitItem());
    return c;
  }

  public ItemData getInitItem() {
    return data.getInitItem();
  }

}
