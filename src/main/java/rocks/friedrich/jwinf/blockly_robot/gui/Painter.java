package rocks.friedrich.jwinf.blockly_robot.gui;

import ea.Scene;
import ea.actor.Text;
import rocks.friedrich.jwinf.blockly_robot.logic.level.Difficulty;

public class Painter
{
    public static void paintVersionHeading(Scene scene, float x, float y,
            Difficulty difficulty)
    {
        Text text = TextMaker.createText(
                "Version " + "*".repeat(difficulty.getIndex() + 2), 1);
        text.setPosition(x, y);
        scene.add(text);
    }
}
