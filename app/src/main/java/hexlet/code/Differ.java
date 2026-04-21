package hexlet.code;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Differ {

    public static String generate(String content1, String content2, String format) throws Exception {
        Map<String, Object> file1 = new TreeMap<>(getData(content1));
        Map<String, Object> file2 = new TreeMap<>(getData(content2));
        List<DiffNode> result = buildDiff(file1, file2);
        return Formatter.format(result, format);
    }

    public static String generate(String content1, String content2) throws Exception {
        return generate(content1, content2, null);
    }

    private static List<DiffNode> buildDiff(Map<String, Object> file1, Map<String, Object> file2) {
        Set<String> keys = new TreeSet<>();
        keys.addAll(file1.keySet());
        keys.addAll(file2.keySet());
        List<DiffNode> result = new ArrayList<>();

        for (String key : keys) {
            if (!file1.containsKey(key)) {
                result.add(new DiffNode(key, null, file2.get(key), Status.ADDED));
            } else if (!file2.containsKey(key)) {
                result.add(new DiffNode(key, file1.get(key), null, Status.REMOVED));
            } else if (Objects.equals(file1.get(key), file2.get(key))) {
                result.add(new DiffNode(key, file1.get(key), file2.get(key), Status.UNCHANGED));
            } else {
                result.add(new DiffNode(key, file1.get(key), file2.get(key), Status.UPDATED));
            }
        }

        return result;
    }

    public static Map<String, Object> getData(String content) throws Exception {
        var path = Paths.get(content).toAbsolutePath().toString();
        return Parser.parse(path);
    }



}
