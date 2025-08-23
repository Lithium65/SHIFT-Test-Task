package service;

import domain.Department;
import domain.Employee;
import domain.Manager;
import java.util.List;

public interface DepartmentBuildService {
    void formDepartments (List<Manager> managerList, List<Employee> employeeList, List<String> errorLines, List<Department> departmentList);
}
