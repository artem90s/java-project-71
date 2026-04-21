package hexlet.code.formatter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import hexlet.code.DiffNode;

import java.util.List;

public class Json {
    public static String format(List<DiffNode> diff) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            return mapper.writeValueAsString(diff);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
