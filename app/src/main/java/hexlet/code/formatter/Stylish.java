package hexlet.code.formatter;

import hexlet.code.DiffNode;

import java.util.List;

public class Stylish {
    public static String stylish(List<DiffNode> diff) {
        StringBuilder sb = new StringBuilder();
        sb.append("{" + "\n");
        for (DiffNode node : diff) {
            switch (node.getStatus()) {
                case ADDED -> sb.append("  + ")
                        .append(node.getKey())
                        .append(": ")
                        .append(formatValue(node.getNewValue()))
                        .append("\n");

                case REMOVED -> sb.append("  - ")
                        .append(node.getKey())
                        .append(": ")
                        .append(formatValue(node.getOldValue()))
                        .append("\n");

                case UPDATED -> {
                    sb.append("  - ")
                            .append(node.getKey())
                            .append(": ")
                            .append(formatValue(node.getOldValue()))
                            .append("\n");
                    sb.append("  + ")
                            .append(node.getKey())
                            .append(": ")
                            .append(formatValue(node.getNewValue()))
                            .append("\n");
                }

                case UNCHANGED -> sb.append("    ")
                        .append(node.getKey())
                        .append(": ")
                        .append(formatValue(node.getOldValue()))
                        .append("\n");

                default -> throw new IllegalArgumentException("Неизвестный тип состояния");
            }
        }
        sb.append("}");
        return sb.toString();
    }



    private static String formatValue(Object value) {
        if (value == null) {
            return "null";
        }
        return value.toString();
    }
}
