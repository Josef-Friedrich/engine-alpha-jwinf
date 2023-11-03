package rocks.friedrich.jwinf.engine.json.model;

import java.util.Map;

public class Task {
  /**
   * Quelle HTML head title. Zum Beispiel: „Kerzen anzünden“
   */
  public String title;

  /**
   * In h1 HTML-Tags im HTML-Quelltext. Zum Beispiel: „Kerzen anzünden“
   */
  public String heading;

  /**
   * Quelle HTML DIV class taskIntro
   *
   * Zeilenumbrüche mit \n
   *
   * Zum Beispiel: „Programmiere den Roboter: Der Roboter soll alle Kerzen
   * anzünden.“
   */
  public String intro;

  /**
   * HTML DIV id=menu
   */
  public String[] menu;

  /**
   * Linkszu den Quellen der JSON-Datei:
   * https://jwinf.de/tasks/jwinf/jwinf-aufgaben/2020/20-DE-13-Kerzen-einfach/index_new.html?channelId=task
   * https://jwinf.de/tasks/jwinf/jwinf-aufgaben/2020/20-DE-13-Kerzen-einfach/task_new.js
   */
  public String[] sources;

  public Map<String, Tile> tiles;
  public Levels levels;

}
