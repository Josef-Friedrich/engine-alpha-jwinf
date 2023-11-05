package rocks.friedrich.jwinf.engine.scenes;

import rocks.friedrich.jwinf.engine.Color;

import ea.Scene;
import ea.actor.Text;
import ea.actor.Rectangle;
import rocks.friedrich.jwinf.engine.Controller;
import rocks.friedrich.jwinf.engine.State;

public class MainMenu extends Scene {

  private int y;

  private int x = -10;

  public MainMenu() {
    State.menu.getMain().forEach((main, submenu) -> {
      Rectangle rectangle = new Rectangle(20, 2);
      rectangle.setBorderRadius(0.3f);
      rectangle.setPosition(x - 1, y - 0.5f);
      rectangle.setColor(new Color("#99d422"));
      add(rectangle);

      Text text = new Text(main, 1);
      text.setColor(Color.BLACK);
      text.setPosition(x, y);
      add(text);

      y -= 3;
    });
  }

  public static void main(String[] args) {
    Controller.launchScene(new MainMenu());
  }

}
