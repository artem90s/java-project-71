package hexlet.code;

import hexlet.code.dto.DiffNode;
import hexlet.code.service.Extractor;
import hexlet.code.service.Formatter;
import hexlet.code.service.Matcher;
import hexlet.code.service.Parser;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        Map<String, Object> object1 = new TreeMap<>(getData(Extractor.extract(filepath1)));
        Map<String, Object> object2 = new TreeMap<>(getData(Extractor.extract(filepath2)));
        List<DiffNode> result = Matcher.match(object1, object2);
        return Formatter.format(result, format);
    }

    public static String generate(String content1, String content2) throws Exception {
        return generate(content1, content2, "stylish");
    }

    public static Map<String, Object> getData(String content) throws Exception {
        return Parser.parse(content);
    }

}
