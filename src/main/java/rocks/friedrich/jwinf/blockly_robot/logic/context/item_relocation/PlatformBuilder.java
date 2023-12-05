package rocks.friedrich.jwinf.blockly_robot.logic.context.item_relocation;

import rocks.friedrich.jwinf.blockly_robot.logic.context.Context;
import rocks.friedrich.jwinf.blockly_robot.logic.context.Coords;
import rocks.friedrich.jwinf.blockly_robot.logic.item.Item;
import rocks.friedrich.jwinf.blockly_robot.logic.robot.ErrorMessages;
import rocks.friedrich.jwinf.blockly_robot.logic.robot.ItemRelocation;

public class PlatformBuilder extends ItemRelocator
{
    private int numberOfPlatforms;

    public PlatformBuilder(Context context)
    {
        super(context);
        numberOfPlatforms = context.getTask().getNbPlatforms();
    }

    /**
     * @see <a href=
     *      "https://github.com/France-ioi/bebras-modules/blob/ec1baf055c7f1c383ce8dfa5d27998463ef5be59/pemFioi/blocklyRobot_lib-1.1.js#L2312-L2328">blocklyRobot_lib-1.1.js
     *      L2312-L2328</a>
     */
    public ItemRelocation dropPlatform(Coords coords, String actionName)
    {
        ItemRelocation action = reportItemRelocation(actionName, null);
        if (numberOfPlatforms == 0)
        {
            return (ItemRelocation) action.setError(
                    ErrorMessages.PLATFORMS_FAILURE_NOT_ENOUGH_PLATFORM);
        }
        if (context.isObstacle(coords))
        {
            return (ItemRelocation) action
                    .setError(ErrorMessages.PLATFORMS_FAILURE_DROP_PLATFORM);
        }
        Item platform = drop(coords, "platform");
        if (platform != null)
        {
            numberOfPlatforms--;
        }
        return action.setItem(platform);
    }
}
