package rocks.friedrich.jwinf.platform.robot;

import rocks.friedrich.jwinf.platform.logic.level.Level;

public interface RobotAction {
  public void act(Robot robot, Level level);
}
