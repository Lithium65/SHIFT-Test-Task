import domain.Employee;
import file.SbFileReader;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        SbFileReader sbFileReader = new SbFileReader();
        Path testPath = Paths.get("E:\\Sharaga\\SHIFT-Test-Task");
        try {
            sbFileReader.readDirectory(testPath);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        List<Employee> employeeList = sbFileReader.getEmployeeList();
        for (Employee employee : employeeList) {
            System.out.println(employee.getId() + employee.getName() + employee.getSalary() + employee.getManagerId());
        }
    }
}
