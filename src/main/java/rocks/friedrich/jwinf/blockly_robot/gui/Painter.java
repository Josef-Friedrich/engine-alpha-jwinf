package rocks.friedrich.jwinf.blockly_robot.gui;

import ea.Scene;
import ea.actor.Image;
import ea.actor.Text;
import rocks.friedrich.jwinf.blockly_robot.logic.level.Level;

public class Painter
{
    public static void paintVersionHeading(Scene scene, float x, float y,
            Level level)
    {
        x -= 0.3f;
        Text text = TextMaker.createText("Version");
        text.setPosition(x, y);
        scene.add(text);
        x += 2.3f;
        for (int i = 0; i < level.getDifficulty().getIndex() + 2; i++)
        {
            scene.add(createStar(x, y));
            x += 1f;
        }
    }

    private static Image createStar(float x, float y)
    {
        Image star = new Image("images/star.png", 0.8f, 0.8f);
        star.setPosition(x, y + 0.1f);
        return star;
    }
}
