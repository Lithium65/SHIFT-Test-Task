package parser.impl;

import domain.Employee;
import domain.Manager;
import parser.PersonParser;
import repo.RawDataRepo;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public final class PersonParserImpl implements PersonParser {

    public void parsePersons(List<String> lines, RawDataRepo rawDataRepo) {
        Set<Integer> employeeIds = new HashSet<>();
        Set<String> departmentsNames = new HashSet<>();
        for (String line : lines) {

            if (line.startsWith("Manager")) {
                Optional<Manager> manager = PersonParserImpl.parseManager(line);
                if (manager.isPresent() && !departmentsNames.contains(manager.get().getDepartment())) {
                    rawDataRepo.addManager(manager.get());
                    departmentsNames.add(manager.get().getDepartment());
                } else rawDataRepo.addInvalidLine(line);

            } else if (line.startsWith("Employee")) {
                Optional<Employee> employee = PersonParserImpl.parseEmployee(line);
                if (employee.isPresent() && !employeeIds.contains(employee.get().getId())) {
                    rawDataRepo.addEmployee(employee.get());
                    employeeIds.add(employee.get().getId());
                } else rawDataRepo.addInvalidLine(line);

            } else rawDataRepo.addInvalidLine(line);
        }
    }

    private static Optional<Manager> parseManager(String line) {
        String[] parts = line.split(",");
        if (parts.length != 5) return Optional.empty();
        try {
            int id = Integer.parseInt(parts[1].trim());
            String name = parts[2].trim();
            double salary = Double.parseDouble(parts[3].trim());
            if (salary <= 0)
                return Optional.empty();
            String department = parts[4].trim();
            return Optional.of(new Manager(id, name, salary, department));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private static Optional<Employee> parseEmployee(String line) {
        String[] parts = line.split(",");
        if (parts.length != 5) return Optional.empty();
        try {
            int id = Integer.parseInt(parts[1].trim());
            String name = parts[2].trim();
            double salary = Double.parseDouble(parts[3].trim());
            if (salary <= 0) return Optional.empty();
            int managerId = Integer.parseInt(parts[4].trim());
            return Optional.of(new Employee(id, name, salary, managerId));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
