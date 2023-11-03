package rocks.friedrich.jwinf.engine.json;

import java.io.File;
import java.util.Map;

import ea.internal.io.ResourceLoader;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TaskLoader {

  public static void main(String[] args) {

    ObjectMapper objectMapper = new ObjectMapper();

    try {
      Map<String, String> jsonMap = objectMapper.readValue(ResourceLoader.loadAsStream("json/candle.json"), Map.class);

      String test = jsonMap.get("test");
      System.out.println(test);
    } catch (Exception e) {
      // TODO: handle exception
    }

  }

}
