package hexlet.code;

import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class Differ {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static String generate(String content1, String content2) throws Exception {
        Map file1 = new TreeMap(getData(content1));
        Map file2 = new TreeMap(getData(content2));
        Map<String, String> result = new LinkedHashMap<>();
        for (Object key : file1.keySet()) {
            if (file2.containsKey(key)) {
                if (file1.get(key).equals(file2.get(key))) {
                    result.put((String) key, (String) file2.get(key).toString());
                } else {
                    String value1 = file1.get(key).toString();
                    String value2 = file2.get(key).toString();
                    result.put("- " + key, value1);
                    result.put("+ " + key, value2);
                }
            } else {
                result.put("- " + key, file1.get(key).toString());
            }
        }
        for (Object key : file2.keySet()) {
            if (!file1.containsKey(key)) {
                result.put("+ " + key, file2.get(key).toString());
            }
        }
        return print(result);
    }

    public static Map getData(String content) throws Exception {
        var path = Paths.get(content).toAbsolutePath().toString();
        return parse(path);
    }

    private static Map parse(String content) {
        return MAPPER.readValue(new File(content), Map.class);
    }

    private static String print(Map<String, String> res) {
        StringBuilder sb = new StringBuilder();
        sb.append("{" + "\n");
        for (Map.Entry<String, String> entry : res.entrySet()) {
            sb.append(" " + entry.getKey() + ": " + entry.getValue() + "\n");
        }
        sb.append("}");
        return sb.toString();
    }

}
