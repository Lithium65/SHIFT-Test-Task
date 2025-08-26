package service;

import domain.Employee;
import domain.Manager;
import repo.ProcessedDataRepo;

import java.util.List;

public interface DepartmentBuildService {
    void formDepartments (List<Manager> managerList, List<Employee> employeeList, List<String> errorLines, ProcessedDataRepo processedDataRepo);
}
