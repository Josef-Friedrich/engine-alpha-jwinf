package rocks.friedrich.jwinf.engine.json;

import java.io.IOException;

import ea.internal.io.ResourceLoader;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import rocks.friedrich.jwinf.engine.json.model.Task;

public class TaskLoader {

  public static Task load(String filePath) throws StreamReadException, DatabindException, IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.readValue(ResourceLoader.loadAsStream(filePath), Task.class);
  }

  public static void main(String[] args) {
    try {
      Task task = load("json/candle.json");
      System.out.println(task.tiles.get("robot").img);
    } catch (StreamReadException e) {
      e.printStackTrace();
    } catch (DatabindException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
