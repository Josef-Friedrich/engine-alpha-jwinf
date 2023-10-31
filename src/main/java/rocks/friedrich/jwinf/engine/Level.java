package rocks.friedrich.jwinf.engine;

import java.awt.event.KeyEvent;

import ea.event.KeyListener;
import ea.Scene;

/**
 * Ein Schwierigkeitsgrad bzw. eine Version einer Trainingsaufgabe.
 *
 * Eine Trainingsaufgabe kann mehrere Versionen unterschiedlicher
 * Schwierigkeitsgrade haben, z. B. eine Zweistern- (<code>Version**</code>,
 * <em>easy</em>), Dreistern-(<code>Version***</code>, <em>medium</em>), und
 * eine Vierstern-Version (<code>Version****</code>, <em>hard</em>).
 */
public class Level extends Scene implements KeyListener {

  DifficultyLevel difficulty;

  /**
   * Zum Beispiel „Der Roboter soll den Edelstein einsammeln. Sobald er das Feld
   * mit dem
   * Edelstein erreicht, wird dieser automatisch eingesammelt.“
   */
  String intro;

  public int width;

  public int height;

  protected Grid grid;

  /**
   * Der Haupt-Kachelsatz. Die Figur muss auf diesen Kachelsatz Zugriff haben,
   * um entscheiden zu können, ob sie sich vor einem Hindernis befindet oder
   * nicht.
   */
  protected TileMap map;

  Actor actor;

  /**
   * @param width  Grid width
   * @param height Grid height
   */
  public Level(int width, int height) {
    this.width = width;
    this.height = height;
  }

  public void setGrid(String gridColor, String backgroundColor) {
    grid = new Grid(width, height);
    grid.setColor(new Color(gridColor));
    grid.setBackground(new Color(backgroundColor));
    grid.setPosition(-0.5f, -0.5f);
    add(grid);
  }

  public Grid getGrid() {
    return grid;
  }

  public void setMap(TileMap map) {
    this.map = map;
    add(this.map);
  }

  public void setMap(String pathPrefix, String extension) {
    map = new TileMap(width, height, pathPrefix, extension);
    add(map);
  }

  public TileMap getMap() {
    return map;
  }

  public void controlActor(ActorAction action) {
    action.act(actor, this);
  }

  public void focus() {
    getCamera().setFocus(getGrid());
    getCamera().setZoom(Task.pixelPerMeter);
  }

  @Override
  public void onKeyDown(KeyEvent keyEvent) {
    switch (keyEvent.getKeyCode()) {

      case KeyEvent.VK_M:
        Task.toggleInterpolator();
        break;
    }
  }
}
