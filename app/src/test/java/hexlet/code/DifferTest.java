package hexlet.code;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {

    @Test
    void testIdenticalFiles() throws Exception {
        String file1Path = "src/test/resources/file1.json";
        String file2Path = "src/test/resources/file2.json";

        String result = Differ.generate(file1Path, file2Path);
        String expected = "{\n"
                + " follow: false\n"
                + " host: hexlet.io\n"
                + " proxy: 123.234.53.22\n"
                + " timeout: 50\n"
                + "}";
        assertEquals(expected.trim(), result.trim());
    }

    @Test
    void testAddedAndRemovedFields() throws Exception {
        String file1Path = "src/test/resources/file1.json";
        String file3Path = "src/test/resources/file3.json";

        String result = Differ.generate(file1Path, file3Path);
        String expected = "{\n"
                + " - follow: false\n"
                + " host: hexlet.io\n"
                + " - proxy: 123.234.53.22\n"
                + " - timeout: 50\n"
                + " + timeout: 20\n"
                + " + verbose: true\n"
                + "}";
        assertEquals(expected.trim(), result.trim());
    }

    @Test
    void testEmptyFiles() throws Exception {
        String emptyFile = "src/test/resources/empty.json";

        String result = Differ.generate(emptyFile, emptyFile);
        String expected = "{" + "\n" + "}";
        assertEquals(expected, result.trim());
    }

    @Test
    void testDifferentYmlFiles() throws Exception {
        String yml1 = "src/test/resources/f1le.yaml";
        String yml2 = "src/test/resources/f3le.yaml";

        String res = Differ.generate(yml1, yml2);
        String expected = "{\n"
                + " - follow: false\n"
                + " host: hexlet.io\n"
                + " - proxy: 123.234.53.22\n"
                + " - timeout: 50\n"
                + " + timeout: 20\n"
                + " + verbose: true\n"
                + "}";
        assertEquals(expected.trim(), res.trim());
    }

    @Test
    void test8DifferWithStylish() throws Exception {
        String file8 = "src/test/resources/file8.json";
        String file9 = "src/test/resources/file9.json";

        String res = Differ.generate(file8, file9);
        String expected = "{\n"
                + " chars1: [a, b, c]\n"
                + " - chars2: [d, e, f]\n"
                + " + chars2: false\n"
                + " - checked: false\n"
                + " + checked: true\n"
                + " - default: null\n"
                + " + default: [value1, value2]\n"
                + " - id: 45\n"
                + " + id: null\n"
                + " - key1: value1\n"
                + " + key2: value2\n"
                + " numbers1: [1, 2, 3, 4]\n"
                + " - numbers2: [2, 3, 4, 5]\n"
                + " + numbers2: [22, 33, 44, 55]\n"
                + " - numbers3: [3, 4, 5]\n"
                + " + numbers4: [4, 5, 6]\n"
                + " + obj1: {nestedKey=value, isNested=true}\n"
                + " - setting1: Some value\n"
                + " + setting1: Another value\n"
                + " - setting2: 200\n"
                + " + setting2: 300\n"
                + " - setting3: true\n"
                + " + setting3: none\n"
                + "}";
        assertEquals(res, expected);
    }

    @Test
    void plainTest() throws Exception {
        String file1 = "src/test/resources/file8.json";
        String file2 = "src/test/resources/file9.json";

        String res = Differ.generate(file1, file2, "plain");

        String expected = "Property 'chars2' was updated. From [complex value] to false\n"
                + "Property 'checked' was updated. From false to true\n"
                + "Property 'default' was updated. From null to [complex value]\n"
                + "Property 'id' was updated. From 45 to null\n"
                + "Property 'key1' was removed\n"
                + "Property 'key2' was added with value: 'value2'\n"
                + "Property 'numbers2' was updated. From [complex value] to [complex value]\n"
                + "Property 'numbers3' was removed\n"
                + "Property 'numbers4' was added with value: [complex value]\n"
                + "Property 'obj1' was added with value: [complex value]\n"
                + "Property 'setting1' was updated. From 'Some value' to 'Another value'\n"
                + "Property 'setting2' was updated. From 200 to 300\n"
                + "Property 'setting3' was updated. From true to 'none'";
        assertEquals(res, expected);
    }

    @Test
    void testJson() throws Exception {
        String file1Path = "src/test/resources/file1.json";
        String file3Path = "src/test/resources/file3.json";

        String result = Differ.generate(file1Path, file3Path, "json");

        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(result);

        List<DiffNode> expectedList = List.of(
                new DiffNode("follow", false, null, Status.REMOVED),
                new DiffNode("host", "hexlet.io", "hexlet.io", Status.UNCHANGED),
                new DiffNode("proxy", "123.234.53.22", null, Status.REMOVED),
                new DiffNode("timeout", "50", "20", Status.UPDATED),
                new DiffNode("verbose", null, true, Status.ADDED)
        );

        JsonNode expected = mapper.valueToTree(expectedList);

        assertEquals(expected, json);
    }
}
