package input.impl;

import exception.StaffIsNotInitialized;
import input.DataReader;
import utils.SbFilesSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public final class SbDataReaderImpl implements DataReader {

    private final Path directory;

    public SbDataReaderImpl() {
        directory = Paths.get("").toAbsolutePath();
    }

    @Override
    public List<String> readDirectory () throws IOException, StaffIsNotInitialized {
        List<Path> foundFiles = SbFilesSearch.findSbFiles(directory);
        List<String> lines = new ArrayList<>();

        for (Path file : foundFiles) {
            try (BufferedReader reader = Files.newBufferedReader(file)) {
                String bufferLine;

                while ((bufferLine = reader.readLine()) != null) {
                    String line = bufferLine.trim();
                    if (line.isEmpty()) continue;
                    lines.add(line);
                }
            }
        }
        if (lines.isEmpty()) {
            throw new StaffIsNotInitialized("Staff list has not been initialized");
        }
        return lines;
    }
}
