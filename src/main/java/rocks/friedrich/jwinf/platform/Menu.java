package rocks.friedrich.jwinf.platform;

import java.io.IOException;
import java.util.LinkedHashMap;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import rocks.friedrich.jwinf.platform.data.JsonLoader;

public class Menu {

  private LinkedHashMap<String, LinkedHashMap<String, String>> data;

  public Menu() {
    try {
      data = JsonLoader.loadMenu();
    } catch (StreamReadException e) {
      e.printStackTrace();
    } catch (DatabindException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public LinkedHashMap<String, LinkedHashMap<String, String>> getMain() {
    return data;
  }

  public LinkedHashMap<String, String> getSub(String menu) {
    return data.get(menu);
  }

  public String getId(String menu, String submenu) {
    return data.get(menu).get(submenu);
  }

}
