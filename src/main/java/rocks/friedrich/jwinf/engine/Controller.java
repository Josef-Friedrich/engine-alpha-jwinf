package rocks.friedrich.jwinf.engine;

import ea.Game;
import ea.animation.interpolation.EaseInOutFloat;
import ea.animation.interpolation.LinearFloat;

public class Controller {

  public static void toggleInterpolator() {
    if (!(State.interpolator instanceof EaseInOutFloat)) {
      State.interpolator = new EaseInOutFloat(0, 1);
    } else {
      State.interpolator = new LinearFloat(0, 1);
    }
  }

    public static void launchLevel(Level level) {
    if (!Game.isRunning()) {
      Game.start(State.pixelPerMeter * level.width, State.pixelPerMeter * level.height, level);
      Game.setTitle("Zünde alle Kerzen an");
      // Game.setDebug(true);
    } else {
      Game.setFrameSize(State.pixelPerMeter * level.width, State.pixelPerMeter * level.height);
      Game.transitionToScene(level);
    }

    State.map = level.getMap();
    State.actor = level.getActor();

    level.focus();
  }
}
