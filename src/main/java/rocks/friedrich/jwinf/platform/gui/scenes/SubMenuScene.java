package rocks.friedrich.jwinf.platform.gui.scenes;

import ea.Game;
import ea.Scene;
import ea.actor.Text;
import ea.internal.Bounds;
import rocks.friedrich.jwinf.platform.gui.Color;
import rocks.friedrich.jwinf.platform.gui.Controller;
import rocks.friedrich.jwinf.platform.gui.Fonts;
import rocks.friedrich.jwinf.platform.gui.State;

public class SubMenuScene extends Scene implements WindowScene
{
    private final float FONT_SIZE = 0.8f;

    private final float INITIAL_X = 0;

    private final float INITIAL_Y = 0;

    /**
     * aktuelle x-Position.
     */
    private float x = INITIAL_X;

    /**
     * aktuelle y-Position.
     */
    private float y = INITIAL_Y;

    private final String main;

    public SubMenuScene(String main)
    {
        this.main = main;
        State.menu.getSub(main).forEach((sub, id) -> {
            Text text = new Text(sub, FONT_SIZE);
            text.setFont(Fonts.regular);
            if (id != null)
            {
                text.setColor(Color.BLACK);
                text.addMouseClickListener((vector, mouseButton) -> {
                    if (text.contains(vector))
                    {
                        AllLevelsScene.launch(id);
                    }
                });
                text.addFrameUpdateListener((deltaSeconds) -> {
                    if (text.contains(Game.getMousePositionInCurrentScene()))
                    {
                        text.setOpacity(0.5f);
                    }
                    else
                    {
                        text.setOpacity(1f);
                    }
                });
            }
            else
            {
                text.setColor(Color.GRAY);
            }
            text.setPosition(x, y);
            add(text);
            y -= 2 * FONT_SIZE;
        });
    }

    public Bounds getWindowBounds()
    {
        return new Bounds(INITIAL_X - 2, y, 12, INITIAL_Y - y + 2);
    }

    public String getTitle()
    {
        return main;
    }

    public static void main(String[] args)
    {
        Controller.launchScene((WindowScene) new SubMenuScene(
                "Bedingte Anweisungen – Übungen"));
    }
}
