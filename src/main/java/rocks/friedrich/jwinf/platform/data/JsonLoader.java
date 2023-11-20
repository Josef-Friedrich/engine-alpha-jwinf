package rocks.friedrich.jwinf.platform.data;

import java.io.IOException;
import java.util.LinkedHashMap;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ea.internal.io.ResourceLoader;
import rocks.friedrich.jwinf.platform.data.model.MenuData;
import rocks.friedrich.jwinf.platform.data.model.TaskData;

public class JsonLoader {

  public static TaskData loadTask(String filePath) throws StreamReadException, DatabindException, IOException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(ResourceLoader.loadAsStream(filePath), TaskData.class);
  }

  public static LinkedHashMap<String, LinkedHashMap<String, String>> loadMenu() throws StreamReadException, DatabindException, IOException {
    ObjectMapper mapper = new ObjectMapper();
    var menu = mapper.readValue(ResourceLoader.loadAsStream("data/menu.json"), MenuData.class);
    return menu.menu;
  }

}
