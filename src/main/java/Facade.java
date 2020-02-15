import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Facade {
    static Facade facade;
    ObjectMapper mapper = new ObjectMapper();

    public static Facade getInstance() {
        return facade == null ? facade = new Facade() : facade;
    }

    private Facade() {
    }

    public Tree ReadTree(String string) {
        try {
            return mapper.readValue(string, Tree.class);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public String WriteTree(Tree tree) {
        try {
            return mapper.writeValueAsString(tree);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
