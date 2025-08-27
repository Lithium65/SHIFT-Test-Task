package repo;

import domain.Employee;
import domain.Manager;

import java.util.List;
import java.util.Map;

public interface RawDataRepo {
    void addEmployee (Employee employee);
    void addManager (Manager manager);
    void addInvalidLine (String errorLine);
    List<Employee> getEmployeeList();
    List<Manager> getManagerList();
    List<String> getErrorLines();
}
