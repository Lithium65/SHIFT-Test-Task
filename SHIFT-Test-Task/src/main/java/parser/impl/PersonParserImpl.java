package parser.impl;

import com.google.inject.Inject;
import domain.Employee;
import domain.Manager;
import parser.PersonParser;
import repo.RawDataRepo;
import service.PersonValidationService;

import java.util.List;
import java.util.Optional;

public final class PersonParserImpl implements PersonParser {
    private static final int PERSON_PARTS_AMOUNT = 5;
    public static final int ID_POSITION = 1;
    public static final int NAME_POSITION = 2;
    public static final int SALARY_POSITION = 3;
    public static final int DEPARTMENT_NAME_POSITION = 4;
    public static final int MANAGER_ID_POSITION = 4;
    private final PersonValidationService validationService;

    @Inject
    public PersonParserImpl(PersonValidationService validationService) {
        this.validationService = validationService;
    }

    @Override
    public void parsePersons (List<String> lines, RawDataRepo rawDataRepo) {
        for (String line : lines) {

            if (line.startsWith("Manager")) {
                Optional<Manager> manager = PersonParserImpl.parseManager(line);
                if (manager.isPresent() && validationService.isValid(manager.get())) {
                    rawDataRepo.addManager(manager.get());
                } else rawDataRepo.addInvalidLine(line);

            } else if (line.startsWith("Employee")) {
                Optional<Employee> employee = PersonParserImpl.parseEmployee(line);
                if (employee.isPresent() && validationService.isValid(employee.get())) {
                    rawDataRepo.addEmployee(employee.get());
                } else rawDataRepo.addInvalidLine(line);

            } else rawDataRepo.addInvalidLine(line);
        }
    }

    private static Optional<Manager> parseManager (String line) {
        String[] parts = line.split(",");
        if (parts.length != PERSON_PARTS_AMOUNT) return Optional.empty();
        try {
            int id = Integer.parseInt(parts[ID_POSITION].trim());
            String name = parts[NAME_POSITION].trim();
            double salary = Double.parseDouble(parts[SALARY_POSITION].trim());
            String department = parts[DEPARTMENT_NAME_POSITION].trim();
            return Optional.of(new Manager(id, name, salary, department));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private static Optional<Employee> parseEmployee (String line) {
        String[] parts = line.split(",");
        if (parts.length != PERSON_PARTS_AMOUNT) return Optional.empty();
        try {
            int id = Integer.parseInt(parts[ID_POSITION].trim());
            String name = parts[NAME_POSITION].trim();
            double salary = Double.parseDouble(parts[SALARY_POSITION].trim());
            int managerId = Integer.parseInt(parts[MANAGER_ID_POSITION].trim());
            return Optional.of(new Employee(id, name, salary, managerId));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
