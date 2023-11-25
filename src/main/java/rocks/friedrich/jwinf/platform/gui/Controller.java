package rocks.friedrich.jwinf.platform.gui;

import ea.Camera;
import ea.Game;
import ea.Scene;
import ea.Vector;
import ea.animation.interpolation.EaseInOutFloat;
import ea.animation.interpolation.LinearFloat;
import ea.internal.Bounds;
import rocks.friedrich.jwinf.platform.gui.scenes.WindowScene;

public class Controller {

  public static void toggleInterpolator() {
    if (!(State.interpolator instanceof EaseInOutFloat)) {
      State.interpolator = new EaseInOutFloat(0, 1);
    } else {
      State.interpolator = new LinearFloat(0, 1);
    }
  }

  public static void launchScene(int width, int height, Scene scene, boolean debug) {
    scene.setBackgroundColor(Color.WHITE);
    if (!Game.isRunning()) {
      Game.start(width, height, scene);
      if (debug) {
        Game.setDebug(true);
      }
    } else {
      Game.transitionToScene(scene);
      Game.setFrameSize(width, height);
    }
  }

  public static void launchScene(WindowScene windowScene) {
    Scene scene = (Scene) windowScene;
    Camera camera = scene.getCamera();
    float pixelPerMeter = camera.getZoom();
    Bounds bounds = windowScene.getWindowBounds();
    Vector center = bounds.getCenter();
    camera.setPostion(center.getX(), center.getY());
    Game.setTitle(windowScene.getTitle());
    launchScene(Math.round(pixelPerMeter * bounds.getWidth()), Math.round(pixelPerMeter * bounds.getHeight()),
        (Scene) windowScene);
  }

  public static void launchScene(Scene scene, boolean debug) {
    launchScene(800, 600, scene, debug);
  }

  public static void launchScene(Scene scene) {
    launchScene(scene, false);
  }

  public static void launchScene(int width, int height, Scene scene) {
    launchScene(width, height, scene, false);
  }
}
