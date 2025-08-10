import domain.Employee;
import domain.Manager;
import reader.impl.SbFileReader;
import repo.DataRepoImpl;
import repo.impl.DataRepo;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Debug {

    protected static void showDebugData () {
        DataRepoImpl dataRepo = new DataRepo();
        SbFileReader sbFileReader = new SbFileReader();
        Path testPath = Paths.get("E:\\Sharaga\\SHIFT-Test-Task");
        try {
            sbFileReader.readDirectory(testPath, dataRepo);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        List<Employee> employeeList = dataRepo.getEmployeeList();
        System.out.println("Employees:");
        for (Employee employee : employeeList) {
            System.out.println(employee.getId() + " " + employee.getName() + " " + employee.getSalary() + " " + employee.getManagerId());
        }
        System.out.println("\nManagers:");
        List<Manager> managerList = dataRepo.getManagerList();
        for (Manager manager : managerList) {
            System.out.println(manager.getId() + " " + manager.getName() + " " + manager.getSalary() + " " + manager.getDepartment());
        }
        System.out.println("\nInvalid Lines:");
        List<String> errorLines = dataRepo.getErrorLines();
        for (String errorLine : errorLines) {
            System.out.println(errorLine);
        }
    }
}
