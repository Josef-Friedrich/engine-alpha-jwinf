package rocks.friedrich.jwinf.blockly_robot.gui.map;

import ea.Scene;
import ea.actor.Image;
import rocks.friedrich.jwinf.blockly_robot.logic.context.Context;
import rocks.friedrich.jwinf.blockly_robot.logic.item.Item;
import rocks.friedrich.jwinf.blockly_robot.logic.item.StackedItems;

public class ItemMapPainter
{
    private Context context;

    private CoordinateSystemTranslator translate;

    public ItemMapPainter(Context context)
    {
        this.context = context;
        translate = new CoordinateSystemTranslator(context.getRows(),
                context.getCols());
    }

    public void paint(Scene scene, float x, float y)
    {
        translate.setPosition(x, y);
        for (int row = 0; row < context.getRows(); row++)
        {
            for (int col = 0; col < context.getCols(); col++)
            {
                paintStackedItems(scene, col, row, context.get(row, col));
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
        Image image = createImage(item, scene);
        image.setPosition(x, y);
        scene.add(image);
    }

    private Image createImage(Item item, Scene scene)
    {
        Image image = new Image(item.getFilePath(), 1, 1);
        item.setController(
                new GraphicalItemController(item, image, translate, scene));
        return image;
    }
}
