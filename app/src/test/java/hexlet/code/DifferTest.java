package hexlet.code;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.dto.DiffNode;
import hexlet.code.dto.Status;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {
    public static final int OLD_VALUE = 50;
    public static final int NEW_VALUE = 20;

    @Test
    void testIdenticalFiles() throws Exception {
        String file1Path = "src/test/resources/fixtures/file1.json";
        String file2Path = "src/test/resources/fixtures/file2.json";

        String result = Differ.generate(file1Path, file2Path);
        String expected = Files.readString(Path.of("src/test/resources/fixtures/expected_1.txt"));
        assertEquals(expected, result);
    }

    @Test
    void testAddedAndRemovedFields() throws Exception {
        String file1Path = "src/test/resources/fixtures/file1.json";
        String file3Path = "src/test/resources/fixtures/file3.json";

        String result = Differ.generate(file1Path, file3Path);
        String expected = Files.readString(Path.of("src/test/resources/fixtures/expected_2.txt")).trim();
        assertEquals(expected, result);
    }

    @Test
    void testEmptyFiles() throws Exception {
        String emptyFile = "src/test/resources/fixtures/empty.json";

        String result = Differ.generate(emptyFile, emptyFile);
        String expected = "{" + "\n" + "}";
        assertEquals(expected, result);
    }

    @Test
    void testDifferentYmlFiles() throws Exception {
        String yml1 = "src/test/resources/fixtures/f1le.yaml";
        String yml2 = "src/test/resources/fixtures/f3le.yaml";

        String res = Differ.generate(yml1, yml2);
        String expected = Files.readString(Path.of("src/test/resources/fixtures/expected_2.txt")).trim();
        assertEquals(expected, res);
    }

    @Test
    void test8DifferWithStylish() throws Exception {
        String file8 = "src/test/resources/fixtures/file8.json";
        String file9 = "src/test/resources/fixtures/file9.json";

        String res = Differ.generate(file8, file9);
        String expected = Files.readString(Path.of("src/test/resources/fixtures/expected_3.txt")).trim();
        assertEquals(res, expected);
    }

    @Test
    void plainTest() throws Exception {
        String file1 = "src/test/resources/fixtures/file8.json";
        String file2 = "src/test/resources/fixtures/file9.json";

        String res = Differ.generate(file1, file2, "plain");

        String expected = Files.readString(Path.of("src/test/resources/fixtures/expected_4.txt"));
        assertEquals(expected.trim(), res.trim());
    }

    @Test
    void testJson() throws Exception {
        String file1Path = "src/test/resources/fixtures/file1.json";
        String file3Path = "src/test/resources/fixtures/file3.json";

        String result = Differ.generate(file1Path, file3Path, "json");

        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(result);

        List<DiffNode> expectedList = List.of(
                new DiffNode("follow", false, null, Status.REMOVED),
                new DiffNode("host", "hexlet.io", "hexlet.io", Status.UNCHANGED),
                new DiffNode("proxy", "123.234.53.22", null, Status.REMOVED),
                new DiffNode("timeout", OLD_VALUE, NEW_VALUE, Status.UPDATED),
                new DiffNode("verbose", null, true, Status.ADDED)
        );

        JsonNode expected = mapper.valueToTree(expectedList);

        assertEquals(expected, json);
    }

    @Test
    void testHexletFirst() throws Exception {
        String fist = "src/test/resources/fixtures/file_1.json";
        String second = "src/test/resources/fixtures/file_2.json";

        String stylish = Differ.generate(fist, second);
        String plain = Differ.generate(fist, second, "plain");

        String expectedStylish = Files.readString(Path.of("src/test/resources/fixtures/result_stylish.txt"));
        String expectedPlain = Files.readString(Path.of("src/test/resources/fixtures/result_plain.txt"));

        assertEquals(stylish, expectedStylish);
        assertEquals(plain, expectedPlain);
    }

    @Test
    void testHexletSecond() throws Exception {
        String fist = "src/test/resources/fixtures/file1.yml";
        String second = "src/test/resources/fixtures/file2.yml";

        String stylish = Differ.generate(fist, second);
        String plain = Differ.generate(fist, second, "plain");

        String expectedStylish = Files.readString(Path.of("src/test/resources/fixtures/result_stylish.txt"));
        String expectedPlain = Files.readString(Path.of("src/test/resources/fixtures/result_plain.txt"));

        assertEquals(stylish, expectedStylish);
        assertEquals(plain, expectedPlain);
    }

    @Test
    void testHexletThird() throws Exception {
        String fist = "src/test/resources/fixtures/file1.yml";
        String second = "src/test/resources/fixtures/file_2.json";

        String stylish = Differ.generate(fist, second);
        String plain = Differ.generate(fist, second, "plain");

        String expectedStylish = Files.readString(Path.of("src/test/resources/fixtures/result_stylish.txt"));
        String expectedPlain = Files.readString(Path.of("src/test/resources/fixtures/result_plain.txt"));

        assertEquals(stylish, expectedStylish);
        assertEquals(plain, expectedPlain);
    }
}

