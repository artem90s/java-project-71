package hexlet.code;

import org.junit.jupiter.api.Test;

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
        String file3Path = "src/test/resources/file3.json"; // создайте такой файл

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
}
