package rocks.friedrich.jwinf.engine;

import rocks.friedrich.jwinf.engine.task.Level;

interface ActorAction {
  public void act(Actor actor, Level exercise);
}
