package rocks.friedrich.jwinf.platform.gui.map;

import ea.Scene;
import ea.actor.Image;
import rocks.friedrich.jwinf.platform.logic.map.Item;
import rocks.friedrich.jwinf.platform.logic.map.LevelMap;
import rocks.friedrich.jwinf.platform.logic.map.StackedItems;

public class ItemMapPainter
{
    private LevelMap map;

    private CoordinateSystemTranslator translate;

    public ItemMapPainter(LevelMap map)
    {
        this.map = map;
        translate = new CoordinateSystemTranslator(map.rows, map.cols);
    }

    public void paint(Scene scene, float x, float y)
    {
        translate.setPosition(x, y);
        for (int row = 0; row < map.getRows(); row++)
        {
            for (int col = 0; col < map.getCols(); col++)
            {
                paintStackedItems(scene, col, row, map.getStacked(row, col));
            }
        }
    }

    public void paint(Scene scene)
    {
        paint(scene, 0, 0);
    }

    private void paintStackedItems(Scene scene, int col, int row,
            StackedItems items)
    {
        var vector = translate.toVector(row, col);
        for (Item item : items)
        {
            paintItem(scene, vector.getX(), vector.getY(), item);
        }
    }

    private void paintItem(Scene scene, float x, float y, Item item)
    {
        Image image = createImage(item);
        image.setPosition(x, y);
        scene.add(image);
    }

    private Image createImage(Item item)
    {
        return new Image(item.getFilePath(), 1, 1);
    }
}
