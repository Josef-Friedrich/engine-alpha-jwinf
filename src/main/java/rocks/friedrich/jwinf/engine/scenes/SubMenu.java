package rocks.friedrich.jwinf.engine.scenes;

import ea.Game;
import ea.Scene;
import ea.actor.Text;
import rocks.friedrich.jwinf.engine.Color;
import rocks.friedrich.jwinf.engine.Controller;
import rocks.friedrich.jwinf.engine.Fonts;
import rocks.friedrich.jwinf.engine.State;

public class SubMenu extends Scene {

  private final float FONT_SIZE = 0.8f;

  /**
   * aktuelle y-Position.
   */
  private float y = 8;

  /**
   * aktuelle x-Position.
   */
  private float x = -10;

  public SubMenu(String main) {
    State.menu.getSub(main).forEach((sub, id) -> {
      Text text = new Text(sub, FONT_SIZE);
      text.setFont(Fonts.regular);

      if (id != null) {
        text.setColor(Color.BLACK);
        text.addMouseClickListener((vector, mouseButton) -> {
          if (text.contains(vector)) {
            Controller.launchScene(new AllLevels(id));
          }
        });

      text.addFrameUpdateListener((deltaSeconds) -> {
        if (text.contains(Game.getMousePositionInCurrentScene())) {
          text.setOpacity(0.5f);
        } else {
          text.setOpacity(1f);
        }
      });

      } else {
        text.setColor(Color.GRAY);
      }

      text.setPosition(x, y);
      add(text);

      y -= 2 * FONT_SIZE;
    });
  }

  public static void main(String[] args) {
    Controller.launchScene(new SubMenu("Bedingte Anweisungen – Übungen"));
  }

}
