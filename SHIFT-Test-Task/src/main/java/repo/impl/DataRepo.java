package repo.impl;

import domain.Employee;
import domain.Manager;
import lombok.Getter;
import repo.DataRepoImpl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Getter
public class DataRepo implements DataRepoImpl {
    private List<Employee> employeeList = new ArrayList<>();
    private List<Manager> managerList = new ArrayList<>();
    private List<String> errorLines = new ArrayList<>();
    private Map<Integer, List<Employee>> departmentsMap = new LinkedHashMap<>();

    @Override
    public void addEmployee (Employee employee) {
        employeeList.add(employee);
    }

    @Override
    public void addManager (Manager manager) {
        managerList.add(manager);
    }

    @Override
    public void addInvalidLine (String errorLine) {
        errorLines.add(errorLine);
    }

    @Override
    public void addDepartment (Integer managerId, List<Employee> departmentEmployees) {
        departmentsMap.put(managerId, departmentEmployees);
    }
}
