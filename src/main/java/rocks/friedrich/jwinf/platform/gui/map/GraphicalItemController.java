package rocks.friedrich.jwinf.platform.gui.map;

import ea.actor.Image;
import rocks.friedrich.jwinf.platform.logic.item.Item;
import rocks.friedrich.jwinf.platform.logic.item.ItemController;

public class GraphicalItemController implements ItemController
{
    Image image;

    Item item;

    CoordinateSystemTranslator translate;

    public GraphicalItemController(Item item, Image image,
            CoordinateSystemTranslator translator)
    {
        this.item = item;
        this.image = image;
        translate = translator;
    }

    @Override
    public void move(int row, int col)
    {
        // TODO Auto-generated method stub
    }
}
