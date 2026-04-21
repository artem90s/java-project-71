package hexlet.code.utils;

import hexlet.code.dto.DiffNode;
import hexlet.code.formatter.Json;

import java.util.List;

import static hexlet.code.formatter.Plain.plain;
import static hexlet.code.formatter.Stylish.stylish;

public class Formatter {


    public static String format(List<DiffNode> result, String format) {
        return switch (format) {
            case "plain" -> plain(result);
            case "json" -> Json.format(result);
            default -> stylish(result);
        };
    }
}
