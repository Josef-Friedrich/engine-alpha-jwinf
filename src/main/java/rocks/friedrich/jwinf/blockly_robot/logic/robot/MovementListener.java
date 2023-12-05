package rocks.friedrich.jwinf.blockly_robot.logic.robot;

import rocks.friedrich.jwinf.blockly_robot.logic.Compass;

interface MovementListener
{
    /**
     * @param dir Die Richtung, in der sich die Figur bewegen will.
     *
     * @return Wahr, wenn sich die Figur bewegen darf, sonst falsch.
     */
    boolean allowMovement(Compass dir);
}
