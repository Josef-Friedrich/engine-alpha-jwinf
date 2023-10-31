package rocks.friedrich.jwinf.engine;

public class Color extends java.awt.Color {

  /**
   * {@inheritDoc}
   */
  public Color(int r, int g, int b) {
    super(r, g, b);
  }

  public Color(String hexCode) {
    this(Integer.valueOf(hexCode.substring(0, 2), 16), Integer.valueOf(hexCode.substring(2, 4), 16),
        Integer.valueOf(hexCode.substring(4, 6), 16));
  }

}
