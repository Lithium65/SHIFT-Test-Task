import domain.Employee;
import domain.Manager;
import reader.impl.SbFileReader;
import repo.ProcessedDataRepo;
import repo.RawDataRepo;
import repo.impl.ProcessedDataRepoImpl;
import repo.impl.RawDataRepoImpl;
import service.impl.DepartmentBuildServiceImpl;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Debug {

    protected static void showDebugData () {
        RawDataRepo rawDataRepo = new RawDataRepoImpl();
        ProcessedDataRepo processedDataRepo = new ProcessedDataRepoImpl();
        SbFileReader sbFileReader = new SbFileReader();
        DepartmentBuildServiceImpl departmentBuildService = new DepartmentBuildServiceImpl();
        Path testPath = Paths.get("E:\\Sharaga\\SHIFT-Test-Task");
        try {
            sbFileReader.readDirectory(testPath, rawDataRepo);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        List<Employee> employeeList = rawDataRepo.getEmployeeList();
        System.out.println("Employees:");
        for (Employee employee : employeeList) {
            System.out.println(employee.getId() + " " + employee.getName() + " " + employee.getSalary() + " " + employee.getManagerId());
        }
        System.out.println("\nManagers:");
        List<Manager> managerList = rawDataRepo.getManagerList();
        for (Manager manager : managerList) {
            System.out.println(manager.getId() + " " + manager.getName() + " " + manager.getSalary() + " " + manager.getDepartment());
        }
        System.out.println("\nInvalid Lines:");
        List<String> errorLines = rawDataRepo.getErrorLines();
        for (String errorLine : errorLines) {
            System.out.println(errorLine);
        }
    }
}
