package hexlet.code.service;

import hexlet.code.dto.DiffNode;
import hexlet.code.dto.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Matcher {
    public static List<DiffNode> match(Map<String, Object> object1, Map<String, Object> object2) {
        Set<String> keys = new TreeSet<>();
        keys.addAll(object1.keySet());
        keys.addAll(object2.keySet());
        List<DiffNode> result = new ArrayList<>();

        for (String key : keys) {
            if (!object1.containsKey(key)) {
                result.add(new DiffNode(key, null, object2.get(key), Status.ADDED));
            } else if (!object2.containsKey(key)) {
                result.add(new DiffNode(key, object1.get(key), null, Status.REMOVED));
            } else if (Objects.equals(object1.get(key), object2.get(key))) {
                result.add(new DiffNode(key, object1.get(key), object2.get(key), Status.UNCHANGED));
            } else {
                result.add(new DiffNode(key, object1.get(key), object2.get(key), Status.UPDATED));
            }
        }

        return result;
    }
}
