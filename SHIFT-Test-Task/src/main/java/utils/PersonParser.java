package utils;

import domain.Employee;
import domain.Manager;

import java.util.Optional;

public final class PersonParser {
    public static Optional<Manager> parseManager(String[] parts) {
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

    public static Optional<Employee> parseEmployee(String[] parts) {
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
