package input.impl;

import input.DataReader;
import utils.SbFileSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public final class DataReaderImpl implements DataReader {

    @Override
    public List<String> readDirectory (Path directory) throws IOException {
        List<Path> foundFiles = SbFileSearch.findSbFiles(directory);
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
        return lines;
    }
}
