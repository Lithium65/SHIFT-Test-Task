package service.impl;

import domain.Employee;
import domain.Manager;
import service.PersonValidationService;

import java.util.HashSet;
import java.util.Set;

public class PersonValidationServiceImpl implements PersonValidationService {

    private final Set<Integer> existingEmployeeIds = new HashSet<>();
    private final Set<String> existingDepartmentNames = new HashSet<>();

    @Override
    public boolean isValid(Manager manager) {
        if (manager.getSalary() <= 0) {
            return false;
        }
        if (existingEmployeeIds.contains(manager.getId())) {
            return false;
        }
        existingEmployeeIds.add(manager.getId());
        if (existingDepartmentNames.contains(manager.getDepartment())) {
            return false;
        }
        existingDepartmentNames.add(manager.getDepartment());
        return true;
    }

    @Override
    public boolean isValid(Employee employee) {
        if (employee.getSalary() <= 0) {
            return false;
        }
        if (existingEmployeeIds.contains(employee.getId())) {
            return false;
        }
        existingEmployeeIds.add(employee.getId());
        return true;
    }
}
