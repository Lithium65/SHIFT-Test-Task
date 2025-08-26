package output.impl;

import dto.DepartmentStatsDto;
import output.DataWriter;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class StatisticsFileWriter implements DataWriter<List<DepartmentStatsDto>> {

    private final Path outputDir;

    public StatisticsFileWriter (Path outputDir) {
        this.outputDir = outputDir;
    }

    @Override
    public void write (List<DepartmentStatsDto> departmentsStatsList) {
        try {
            Path parent = outputDir.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }
            try (BufferedWriter bufferedWriter = Files.newBufferedWriter(outputDir)) {
                for (DepartmentStatsDto dto : departmentsStatsList) {
                    bufferedWriter.write(dto.toString());
                    bufferedWriter.newLine();
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}
