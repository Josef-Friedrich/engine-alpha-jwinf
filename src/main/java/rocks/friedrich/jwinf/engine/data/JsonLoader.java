package rocks.friedrich.jwinf.engine.data;

import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ea.internal.io.ResourceLoader;
import rocks.friedrich.jwinf.engine.data.model.TaskData;

public class JsonLoader {

  public static TaskData load(String filePath) throws StreamReadException, DatabindException, IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.readValue(ResourceLoader.loadAsStream(filePath), TaskData.class);
  }

}
