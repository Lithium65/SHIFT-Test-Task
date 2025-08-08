package file;

import domain.Employee;
import domain.Manager;
import utils.PersonParser;
import utils.SbFileSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class SbFileReader {
    private final List<Manager> managerList = new ArrayList<>();
    private final List<Employee> employeeList = new ArrayList<>();
    private final List<String> linesWithError = new ArrayList<>();
    private SbFileSearch sbFileSearch;
    private PersonParser personParser;

    public void readDirectory (Path directory) throws IOException {
        List<Path> foundFiles = sbFileSearch.findSbFiles(directory);

        for (Path file : foundFiles) {
            try (BufferedReader reader = Files.newBufferedReader(file)) {
                String bufferLine;

                while ((bufferLine = reader.readLine()) != null) {
                    String line = bufferLine.trim();
                    if (line.isEmpty()) continue;

                    String[] parts = line.split(",");

                    if (line.startsWith("Manager")) {
                        Optional<Manager> manager = personParser.parseManager(parts);
                        if (manager.isPresent()) {
                            managerList.add(manager.get());
                        }
                        else linesWithError.add(line);
                    }
                    else if (line.startsWith("Employee")) {
                        Optional<Employee> employee = personParser.parseEmployee(parts);
                        if (employee.isPresent()) {
                            employeeList.add(employee.get());
                        }
                        else linesWithError.add(line);
                    }
                    else linesWithError.add(line);
                }
            }
        }
    }

    public List<Manager> getManagerList() {
        return managerList;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public List<String> getLinesWithError() {
        return linesWithError;
    }
}
