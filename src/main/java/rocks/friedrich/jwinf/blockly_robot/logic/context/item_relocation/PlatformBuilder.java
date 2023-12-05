package rocks.friedrich.jwinf.blockly_robot.logic.context.item_relocation;

import rocks.friedrich.jwinf.blockly_robot.logic.context.Context;
import rocks.friedrich.jwinf.blockly_robot.logic.item.Item;

public class PlatformBuilder extends ItemRelocator
{
    public PlatformBuilder(Context context)
    {
        super(context);
    }

    /**
     * @see <a href=
     *      "https://github.com/France-ioi/bebras-modules/blob/ec1baf055c7f1c383ce8dfa5d27998463ef5be59/pemFioi/blocklyRobot_lib-1.1.js#L2312-L2328">blocklyRobot_lib-1.1.js
     *      L2312-L2328</a>
     */
    public Item dropPlatformInFront()
    {
        reportItemRelocation("dropPlatformInFront", null);
        // if (getTask().getNbPlatforms() == 0)
        // {
        // return null;
        // }
        return null;
    }

    /**
     * @see <a href=
     *      "https://github.com/France-ioi/bebras-modules/blob/ec1baf055c7f1c383ce8dfa5d27998463ef5be59/pemFioi/blocklyRobot_lib-1.1.js#L2330-L2346">blocklyRobot_lib-1.1.js
     *      L2330-L2346</a>
     */
    public Item dropPlatformAbove()
    {
        return null;
    }
}
