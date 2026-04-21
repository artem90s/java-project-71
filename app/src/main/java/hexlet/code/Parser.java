package hexlet.code;

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Parser {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final YAMLMapper YAML_MAPPER = new YAMLMapper();

    public static Map<String, Object> parse(String content) throws IOException {
        return switch (content.substring(content.indexOf("."))) {
            case ".yaml" -> YAML_MAPPER.readValue(new File(content), Map.class);
            case ".json" -> MAPPER.readValue(new File(content), Map.class);
            default -> throw new IllegalArgumentException("Неподдерживаемое расширение файла");
        };
    }

}
