import args.ApplicationArgs;
import domain.Employee;
import domain.Manager;
import picocli.CommandLine;
import reader.impl.SbFileReader;
import repo.ProcessedDataRepo;
import repo.RawDataRepo;
import repo.impl.ProcessedDataRepoImpl;
import repo.impl.RawDataRepoImpl;
import service.impl.DepartmentBuildServiceImpl;
import service.impl.StatisticsServiceImpl;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Debug {

    protected static void showDebugData (String[] args) {
        RawDataRepo rawDataRepo = new RawDataRepoImpl();
        ProcessedDataRepo processedDataRepo = new ProcessedDataRepoImpl();
        SbFileReader sbFileReader = new SbFileReader();
        ApplicationArgs appArgs = new ApplicationArgs();
        CommandLine cmd = new CommandLine(appArgs);

        try {
            cmd.parseArgs(args);
            appArgs.validateArgs();
        } catch (CommandLine.ParameterException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

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
            System.out.println(employee);
        }
        System.out.println("\nManagers:");
        List<Manager> managerList = rawDataRepo.getManagerList();
        for (Manager manager : managerList) {
            System.out.println(manager);
        }
        System.out.println("\nInvalid Lines:");
        List<String> errorLines = rawDataRepo.getErrorLines();
        for (String errorLine : errorLines) {
            System.out.println(errorLine);
        }
        departmentBuildService.formDepartments(rawDataRepo, processedDataRepo);
        StatisticsServiceImpl statisticsService = new StatisticsServiceImpl();
        statisticsService.collectStats(rawDataRepo, processedDataRepo);
        System.out.println(processedDataRepo.getCollectedStats().get(4));
        System.out.println(processedDataRepo.getCollectedStats().get(3));
        System.out.println(appArgs.getIsStatsNeeded() + " " + appArgs.getSortOrder());
    }
}
