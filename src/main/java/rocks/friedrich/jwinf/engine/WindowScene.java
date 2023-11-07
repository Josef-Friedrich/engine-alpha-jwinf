package rocks.friedrich.jwinf.engine;

import ea.internal.Bounds;

/**
 * Szene, die Informationen mitliefert, in welchem Fenster die Szene geöffnet
 * werden soll.
 */
public interface WindowScene {

  /**
   * @return Eine Zeichenkette, die als Title im Fenster angezeigt werden soll.
   */
  public String getTitle();

  /**
   * @return Die Begrenzungen, die das Fenster umschließen soll.
   */
  public Bounds getWindowBounds();
}
