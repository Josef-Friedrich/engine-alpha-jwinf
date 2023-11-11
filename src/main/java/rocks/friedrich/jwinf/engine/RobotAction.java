package rocks.friedrich.jwinf.engine;

import rocks.friedrich.jwinf.engine.task.Level;

interface RobotAction {
  public void act(Robot robot, Level level);
}
