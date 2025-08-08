package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class SbFileSearch {
    public static List<Path> findSbFiles (Path directory) throws IOException {
        try (Stream<Path> files = Files.list(directory)) {
            return files
                    .filter(path -> path.toString().endsWith(".sb"))
                    .collect(Collectors.toList());
        }
    }
}
