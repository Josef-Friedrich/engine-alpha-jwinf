package rocks.friedrich.jwinf.engine.scenes;

import ea.Scene;
import ea.actor.Rectangle;
import ea.actor.Text;
import ea.internal.io.FontLoader;
import rocks.friedrich.jwinf.engine.Color;
import rocks.friedrich.jwinf.engine.Controller;
import rocks.friedrich.jwinf.engine.State;

public class MainMenu extends Scene {

  private final float FONT_SIZE = 0.8f;

  private final Color AREA_COLOR = new Color("#99d422");

  private final float BORDER_RADIUS = 0.3f;

  /**
   * aktuelle y-Position.
   */
  private float y = 8;

  /**
   * aktuelle x-Position.
   */
  private float x = -10;

  class ColoredArea {
    private Rectangle rectangle;
    private String content;
    private Text text;

    public ColoredArea(String content, float x, float y) {
      this.content = content;
      rectangle = createRectangle();
      text = createText(content);
      rectangle.setPosition(x - 1, y - FONT_SIZE / 2);
      text.setPosition(x, y);
      add(rectangle, text);
    }

    private Rectangle createRectangle() {
      Rectangle rectangle = new Rectangle(20, FONT_SIZE * 2);
      rectangle.setBorderRadius(BORDER_RADIUS);
      rectangle.setColor(AREA_COLOR);
      rectangle.addMouseClickListener((vector, mouseButton) -> {
        System.out.println(content);
      });
      return rectangle;
    }

    private Text createText(String content) {
      Text text = new Text(content, FONT_SIZE);
      text.setFont(FontLoader.loadFromFile("fonts/titilium/TitilliumWeb-Bold.ttf"));
      text.setColor(Color.BLACK);
      return text;
    }
  }

  public MainMenu() {
    State.menu.getMain().forEach((main, submenu) -> {
      new ColoredArea(main, x, y);
      y -= 2.5 * FONT_SIZE;
    });
  }

  public static void main(String[] args) {
    Controller.launchScene(new MainMenu());
  }

}
