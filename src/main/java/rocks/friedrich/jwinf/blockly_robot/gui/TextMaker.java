package rocks.friedrich.jwinf.blockly_robot.gui;

import java.awt.Font;

import ea.actor.Text;
import ea.internal.io.FontLoader;

public class TextMaker
{
    public static Font loadTitillium(String style)
    {
        return FontLoader.loadFromFile(
                "fonts/titilium/TitilliumWeb-%s.ttf".formatted(style));
    }

    public static Font regular = loadTitillium("Regular");

    public static Font bold = loadTitillium("Bold");

    public static Text createText(String content, float fontSize)
    {
        Text text = new Text(content, fontSize);
        text.setFont(regular);
        text.setColor(Color.BLACK);
        return text;
    }

    public static Text createText(String text)
    {
        return createText(text, 1);
    }
}
