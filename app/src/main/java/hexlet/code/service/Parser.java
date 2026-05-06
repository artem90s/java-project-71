package hexlet.code.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.util.Map;

public class Parser {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final ObjectMapper YAML_MAPPER = new ObjectMapper(new YAMLFactory());

    public static Map<String, Object> parse(String content) throws IOException {
        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException("Пустое содержимое: " + content);
        }
        TypeReference<Map<String, Object>> typeRef = new TypeReference<>() {
        };

        if (content.trim().startsWith("{") || content.trim().startsWith("[")) {
            return MAPPER.readValue(content, typeRef);
        }
        return YAML_MAPPER.readValue(content, typeRef);
    }

}
