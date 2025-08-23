package output.impl;

import domain.Department;
import domain.Employee;
import output.DataWriter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DepartmentFileWriter implements DataWriter<Department> {

    private final Path outputDir;

    public DepartmentFileWriter() throws IOException {
        Path baseDir = Paths.get("").toAbsolutePath();
        this.outputDir = baseDir.resolve("departments");
        if (!Files.exists(outputDir)) {
            Files.createDirectories(outputDir);
        }
    }

    @Override
    public void write (Department department) {
        String filename = department.getManager().getDepartment() + ".sb";
        Path filePath = outputDir.resolve(filename);

        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(filePath)) {
            bufferedWriter.write(department.getManager().toString());
            bufferedWriter.newLine();

            if (department.getEmployeeList() != null && !department.getEmployeeList().isEmpty()) {
                for (Employee employee : department.getEmployeeList()) {
                    bufferedWriter.write(employee.toString());
                    bufferedWriter.newLine();
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}
