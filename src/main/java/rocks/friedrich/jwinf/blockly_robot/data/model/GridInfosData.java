package rocks.friedrich.jwinf.blockly_robot.data.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "hideSaveOrLoad", "conceptViewer",
        "languageStrings", "limitedUses", "includeBlocks", "blocklyColourTheme",
        "checkEndEveryTurn", "ignoreInvalidMoves", "actionDelay",
        "multiple_marbles", "additionalBlocksByLevel" })
public class GridInfosData
{
    public boolean hasGravity;

    /**
     * <a href=
     * "https://github.com/France-ioi/bebras-modules/blob/ec1baf055c7f1c383ce8dfa5d27998463ef5be59/pemFioi/blocklyRobot_lib-1.1.js#L1619-L1620">blocklyRobot_lib-1.1.js
     * L1619-L1620"</a>
     */
    public int maxFallAltitude;

    /**
     * Ist in
     * https://jwinf.de/tasks/jwinf/_common/modules/pemFioi/blocklyRobot_lib-1.1.js
     * definiert
     */
    public String backgroundColor;

    /**
     * @see <a href=
     *      "https://github.com/France-ioi/bebras-modules/blob/ec1baf055c7f1c383ce8dfa5d27998463ef5be59/pemFioi/blocklyRobot_lib-1.1.js#L2580-L2582">blocklyRobot_lib-1.1.js
     *      L2580-L2582</a>
     */
    public String borderColor;

    /**
     * @see <a href=
     *      "https://github.com/France-ioi/bebras-modules/blob/ec1baf055c7f1c383ce8dfa5d27998463ef5be59/pemFioi/blocklyRobot_lib-1.1.js#L3140-L3140">blocklyRobot_lib-1.1.js
     *      L3140</a>
     */
    public int bagSize;

    public boolean autoWithdraw;

    /**
     * For example „labyrinth“.
     */
    public String contextType;

    public int nbPlatforms;

    public MaxInstructionsData maxInstructions;

    public Map<String, ItemData> itemTypes;
}