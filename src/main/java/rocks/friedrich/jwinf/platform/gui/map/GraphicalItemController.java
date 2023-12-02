package rocks.friedrich.jwinf.platform.gui.map;

import ea.Scene;
import ea.actor.Image;
import rocks.friedrich.jwinf.platform.gui.level.LevelAssembler;
import rocks.friedrich.jwinf.platform.logic.item.Item;
import rocks.friedrich.jwinf.platform.logic.item.ItemController;

public class GraphicalItemController implements ItemController
{
    Image image;

    Item item;

    Scene scene;

    CoordinateSystemTranslator translate;

    public GraphicalItemController(Item item, Image image,
            CoordinateSystemTranslator translator, Scene scene)
    {
        this.item = item;
        this.image = image;
        this.scene = scene;
        translate = translator;
    }

    public GraphicalItemController(Item item,
            CoordinateSystemTranslator translator, Scene scene)
    {
        this.item = item;
        this.scene = scene;
        translate = translator;
    }

    @Override
    public void add(int row, int col)
    {
        var vector = translate.toVector(row, col);
        if (image == null)
        {
            image = new Image(item.getFilePath(), 1, 1);
        }
        image.setPosition(Math.round(vector.getX()) - LevelAssembler.SHIFT,
                Math.round(vector.getY()) - LevelAssembler.SHIFT);
        scene.add(image);
    }

    public void remove()
    {
        scene.remove(image);
    }

    @Override
    public void move(int row, int col)
    {
        // TODO Auto-generated method stub
    }

    public void withdraw()
    {
        image.remove();
    }
}
