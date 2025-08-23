package output.impl;

import output.DataWriter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ErrorLinesFileWriter implements DataWriter<List<String>> {

    private final Path outputDir;

    public ErrorLinesFileWriter() throws IOException {
        Path baseDir = Paths.get("").toAbsolutePath();
        this.outputDir = baseDir.resolve("departments");
        if (!Files.exists(outputDir)) {
            Files.createDirectories(outputDir);
        }
    }

    @Override
    public void write(List<String> errorLinesList) {
        Path filePath = outputDir.resolve("ErrorLines.sb");

        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(filePath)) {
            for (String line : errorLinesList) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}
