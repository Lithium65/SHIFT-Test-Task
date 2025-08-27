package output.impl;

import dto.DepartmentStatsDto;
import output.CustomPathDataWriter;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class StatisticsFileWriter implements CustomPathDataWriter<List<DepartmentStatsDto>> {

    @Override
    public void write (List<DepartmentStatsDto> departmentsStatsList, Path outputDir) {
        try {
            Path parent = outputDir.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }
            try (BufferedWriter bufferedWriter = Files.newBufferedWriter(outputDir)) {
                bufferedWriter.write("department, min, max, mid");
                bufferedWriter.newLine();
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
