package rocks.friedrich.jwinf.engine;

import java.awt.Color;
import java.awt.Graphics2D;

import ea.actor.Actor;
import ea.internal.FixtureBuilder;

public class Grid extends Actor {

  int numX;
  int numY;

  /**
   * In Pixel pro Meter. Ist beispielsweise die Einheit „Pixel pro Meter“ auf 60
   * Pixel und dieses Attribute auf 2 gesetzt,
   * dann werden die vom Gitter eingeschlossenen Rechtecke 120 auf 120 Pixel groß.
   */
  float size = 1;

  Color color = Color.GREEN;

  Color background;

  public Grid(int numX, int numY, float size) {
    super(() -> FixtureBuilder.createSimpleRectangularFixture(numX * size, numY * size));
    this.numX = numX;
    this.numY = numY;
    this.size = size;
  }

  public Grid(int numX, int numY) {
    this(numX, numY, 1);
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public void setBackground(Color color) {
    background = color;
  }

  @Override
  public void render(Graphics2D g, float pixelPerMeter) {
    int gridSize = Math.round(pixelPerMeter * size);

    if (background != null) {
      g.setColor(background);
      g.fillRect(0, -Math.round(gridSize * numY), Math.round(gridSize * numX), Math.round(gridSize * numY));
    }

    g.setColor(color);

    // Zeichnen der vertikalen Linien von links nach rechts.
    for (int gridX = 0; gridX <= numX; gridX++) {
      g.fillRect(Math.round(gridX * gridSize), -Math.round(numY * gridSize), 1, Math.round(numY * gridSize));
    }

    // Zeichnen der horizontalen Linien von unten nach oben.
    for (int gridY = 0; gridY <= numY; gridY++) {
      g.fillRect(0, -Math.round(gridY * gridSize), Math.round(numX * gridSize), 1);
    }

  }
}
