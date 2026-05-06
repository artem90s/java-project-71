package hexlet.code.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Extractor {
    public static String extract(String filepath) throws IOException {
        return Files.readString(Paths.get(filepath).toAbsolutePath());
    }
}
