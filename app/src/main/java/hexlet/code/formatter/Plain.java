package hexlet.code.formatter;

import hexlet.code.DiffNode;

import java.util.ArrayList;
import java.util.List;

public class Plain {
    public static String plain(List<DiffNode> diff) {
        List<String> formatting = new ArrayList<>();
        for (DiffNode node : diff) {
            String key = node.getKey();
            String newValue = formatValue(node.getNewValue());
            String oldValue = formatValue(node.getOldValue());
            switch (node.getStatus()) {
                case ADDED -> formatting.add("Property '%s' was added with value: %s".formatted(key, newValue));
                case REMOVED -> formatting.add("Property '" + node.getKey() + "' was removed");
                case UPDATED -> {
                    String format = "Property '%s' was updated. From %s to %s".formatted(key, oldValue, newValue);
                    formatting.add(format);
                }
                default -> throw new IllegalArgumentException("Неизвестный статус");
            }
        }
        return String.join("\n", formatting);
    }

    private static String formatValue(Object value) {
        if (value == null) {
            return "null";
        }
        if (value instanceof String) {
            return "'" + value + "'";
        }
        if (value instanceof Number
                || value instanceof Boolean) {
            return value.toString();
        }
        return "[complex value]";
    }

}
