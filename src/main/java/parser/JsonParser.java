package parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Tree;

import java.io.IOException;

public class JsonParser implements Parser<Tree> {
    static JsonParser jsonParser;
    ObjectMapper mapper = new ObjectMapper();

    public static JsonParser getInstance() {
        return jsonParser == null ? jsonParser = new JsonParser() : jsonParser;
    }

    private JsonParser() {
    }

    public Tree read(String string) {
        try {
            return mapper.readValue(string, Tree.class);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public String write(Tree tree) {
        try {

            return mapper.writeValueAsString(tree);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
