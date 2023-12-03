package rocks.friedrich.jwinf.platform.logic.robot;

public enum ErrorMessages
{
    /**
     * @see <a href="https://github.com/France-ioi/bebras-modules/blob/ec1baf055c7f1c383ce8dfa5d27998463ef5be59/pemFioi/blocklyRobot_lib-1.1.js#L359">blocklyRobot_lib-1.1.js L359</a>
     */
    JUMP_WITHOUT_GRAVITY("", ""), JUMP_OUTSIDE_GRID("The robot tries to jump outside of the grid!", ""),
    JUMP_OBSTACLE_BLOCKING("The robot tries to jump but an obstacle blacks it",
            ""),
    JUMP_NO_PLATFORM("The robot tries to jump but there is no platform above!", ""), WITHDRAWABLES_NOTHING_TO_PICK_UP("", ""),
    WITHDRAWABLES_TOO_MANY_OBJECTS("The robot tries to transport too many objects at a time!", ""),
    FALL_FALLS("The robot will leap into the void", ""),
    FALL_WILL_FALL_AND_CRASH("The robot will jump from a high point and crash!",
            ""),
    PLATFORMS_FAILURE_NOT_ENOUGH_PLATFORM("Not enough platforms", ""),
    PLATFORMS_FAILURE_DROP_PLATFORM("You can't drop an object here", "");

    private String en;

    private String de;

    ErrorMessages(String en, String de)
    {
        this.en = en;
        this.de = de;
    }
}
