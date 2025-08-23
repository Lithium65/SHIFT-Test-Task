package service.impl;

import domain.Department;
import service.DepartmentBuildService;
import domain.Employee;
import domain.Manager;

import java.util.*;
import java.util.stream.Collectors;

public class DepartmentBuildServiceImpl implements DepartmentBuildService {

    @Override
    public void formDepartments (List<Manager> managerList, List<Employee> employeeList, List<String> errorLines, List<Department> departmentList) {
        Set<Integer> validManagerIds = managerList.stream()
                .map(Manager::getId)
                .collect(Collectors.toSet());

        Map<Integer, List<Employee>> employeesByManagerId = new HashMap<>();
        for (Employee employee : employeeList) {
            if (validManagerIds.contains(employee.getManagerId())) {
                employeesByManagerId
                        .computeIfAbsent(employee.getManagerId(), id -> new ArrayList<>())
                        .add(employee);
            } else {
                errorLines.add(employee.toString());
            }
        }

        for (Manager manager : managerList) {
            List<Employee> employees = employeesByManagerId.getOrDefault(manager.getId(), List.of());
            departmentList.add(new Department(manager, employees));
        }
    }

}
