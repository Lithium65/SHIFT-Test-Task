package repo.impl;

import domain.Employee;
import domain.Manager;
import lombok.Getter;
import lombok.Setter;
import repo.RawDataRepo;

import java.util.ArrayList;
import java.util.List;

@Getter
public class RawDataRepoImpl implements RawDataRepo {
    private List<Employee> employeeList = new ArrayList<>();
    private List<Manager> managerList = new ArrayList<>();
    private List<String> errorLines = new ArrayList<>();

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
}
