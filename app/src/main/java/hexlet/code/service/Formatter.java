package hexlet.code.service;

import hexlet.code.dto.DiffNode;
import hexlet.code.formatter.Json;

import java.util.List;

import static hexlet.code.formatter.Plain.plain;
import static hexlet.code.formatter.Stylish.stylish;

public class Formatter {


    public static String format(List<DiffNode> result, String format) throws Exception {
        if (format == null) {
            format = "stylish";
        }
        return switch (format) {
            case "plain" -> plain(result);
            case "json" -> Json.format(result);
            case "stylish" -> stylish(result);
            default -> throw new IllegalArgumentException("Неизвестный формат" + format);
        };
    }
}
