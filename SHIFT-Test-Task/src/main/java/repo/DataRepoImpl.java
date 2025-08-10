package repo;

import domain.Employee;
import domain.Manager;

import java.util.List;
import java.util.Map;

public interface DataRepoImpl {
    void addEmployee (Employee employee);
    void addManager (Manager manager);
    void addInvalidLine (String errorLine);
    void addDepartment (Integer managerId, List<Employee> departmentEmployees);
    List<Employee> getEmployeeList();
    List<Manager> getManagerList();
    List<String> getErrorLines();
    Map<Integer, List<Employee>> getDepartmentsMap();
}
