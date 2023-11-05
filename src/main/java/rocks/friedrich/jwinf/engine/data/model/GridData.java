package rocks.friedrich.jwinf.engine.data.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "hideSaveOrLoad", "conceptViewer", "languageStrings", "limitedUses", "includeBlocks", "blocklyColourTheme", "checkEndEveryTurn", "hasGravity", "ignoreInvalidMoves" })
public class GridData {
  /**
   * Ist in
   * https://jwinf.de/tasks/jwinf/_common/modules/pemFioi/blocklyRobot_lib-1.1.js
   * definiert
   */
  public String backgroundColor;

  /**
   * Ist in
   * https://jwinf.de/tasks/jwinf/_common/modules/pemFioi/blocklyRobot_lib-1.1.js
   * definiert (borderColor)
   */
  public String gridColor;

  /**
   * For example „labyrinth“.
   */
  public String contextType;

  public MaxInstructionsData maxInstructions;

  public Map<String, TileData> tiles;
}
