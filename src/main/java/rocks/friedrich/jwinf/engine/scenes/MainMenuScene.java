package rocks.friedrich.jwinf.engine.scenes;

import ea.Game;
import ea.Scene;
import ea.actor.Rectangle;
import ea.actor.Text;
import ea.internal.Bounds;
import rocks.friedrich.jwinf.engine.Color;
import rocks.friedrich.jwinf.engine.Controller;
import rocks.friedrich.jwinf.engine.Fonts;
import rocks.friedrich.jwinf.engine.State;

public class MainMenuScene extends Scene implements WindowScene {

  private final float FONT_SIZE = 0.8f;

  private final Color AREA_COLOR = new Color("#99d422");

  private final float INITIAL_X = 0;

  private final float INITIAL_Y = 0;

  private final float RECTANGLE_WIDTH = 20;

  /**
   * aktuelle y-Position.
   */
  private float y = INITIAL_X;

  /**
   * aktuelle x-Position.
   */
  private float x = INITIAL_Y;

  class ColoredArea {
    private final String main;

    public ColoredArea(String main, float x, float y) {
      this.main = main;
      Rectangle rectangle = createRectangle();
      Text text = createText(main);
      rectangle.setPosition(x - 1, y - FONT_SIZE / 2);
      text.setPosition(x, y);
      add(rectangle, text);
    }

    private Rectangle createRectangle() {
      Rectangle rectangle = new Rectangle(RECTANGLE_WIDTH, FONT_SIZE * 2);
      float BORDER_RADIUS = 0.3f;
      rectangle.setBorderRadius(BORDER_RADIUS);
      rectangle.setColor(AREA_COLOR);
      rectangle.addMouseClickListener((vector, mouseButton) -> {
        if (rectangle.contains(vector)) {
          Controller.launchScene((WindowScene) new SubMenuScene(main));
        }
      });

      rectangle.addFrameUpdateListener((deltaSeconds) -> {
        if (rectangle.contains(Game.getMousePositionInCurrentScene())) {
          rectangle.setOpacity(0.5f);
        } else {
          rectangle.setOpacity(1f);
        }
      });
      return rectangle;
    }

    private Text createText(String content) {
      Text text = new Text(content, FONT_SIZE);
      text.setFont(Fonts.bold);
      text.setColor(Color.BLACK);
      return text;
    }

  }

  public MainMenuScene() {
    State.menu.getMain().forEach((main, submenu) -> {
      new ColoredArea(main, x, y);
      y -= (float) (2.5 * FONT_SIZE);
    });
  }

  public Bounds getWindowBounds() {
    return new Bounds(INITIAL_X - 2, y, RECTANGLE_WIDTH + 2, INITIAL_Y - y + 2);
  }

  public String getTitle() {
    return "Trainingsaufgaben";
  }

  public static void launch() {
    Controller.launchScene((WindowScene) new MainMenuScene());
  }

  public static void main(String[] args) {
    launch();
  }

}
