package rocks.friedrich.jwinf.platform.gui;

import java.awt.Font;

import ea.internal.io.FontLoader;

public class Fonts
{
    public static Font loadTitillium(String style)
    {
        return FontLoader.loadFromFile(
                "fonts/titilium/TitilliumWeb-%s.ttf".formatted(style));
    }

    public static Font regular = loadTitillium("Regular");

    public static Font bold = loadTitillium("Bold");
}
