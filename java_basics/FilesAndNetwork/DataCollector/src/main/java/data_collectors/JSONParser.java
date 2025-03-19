package data_collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JSONParser<T> {

    public List<T> parseToList(String path, Class<T> elementClass) throws IOException {
        File file = new File(path);
        ObjectMapper objectMapper = new ObjectMapper();
        CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(List.class, elementClass);
        return objectMapper.readValue(file, listType);
    }
}
