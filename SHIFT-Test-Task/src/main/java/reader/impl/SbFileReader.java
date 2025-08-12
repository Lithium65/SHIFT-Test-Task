package reader.impl;

import domain.Employee;
import domain.Manager;
import reader.FileReader;
import repo.RawDataRepo;
import utils.PersonParser;
import utils.SbFileSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public final class SbFileReader implements FileReader {

    @Override
    public void readDirectory (Path directory, RawDataRepo staffRepo) throws IOException {
        List<Path> foundFiles = SbFileSearch.findSbFiles(directory);

        for (Path file : foundFiles) {
            try (BufferedReader reader = Files.newBufferedReader(file)) {
                String bufferLine;

                while ((bufferLine = reader.readLine()) != null) {
                    String line = bufferLine.trim();
                    if (line.isEmpty()) continue;

                    String[] parts = line.split(",");

                    if (line.startsWith("Manager")) {
                        Optional<Manager> manager = PersonParser.parseManager(parts);
                        if (manager.isPresent()) {
                            staffRepo.addManager(manager.get());
                        }
                        else staffRepo.addInvalidLine(line);
                    }
                    else if (line.startsWith("Employee")) {
                        Optional<Employee> employee = PersonParser.parseEmployee(parts);
                        if (employee.isPresent()) {
                            staffRepo.addEmployee(employee.get());
                        }
                        else staffRepo.addInvalidLine(line);
                    }
                    else staffRepo.addInvalidLine(line);
                }
            }
        }
    }
}
