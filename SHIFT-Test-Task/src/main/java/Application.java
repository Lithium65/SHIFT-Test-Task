import args.ApplicationArgs;
import args.fields.StatsOutputMethod;
import domain.Department;
import dto.DepartmentStatsDto;
import exception.StaffIsNotInitialized;
import input.DataReader;
import input.impl.DataReaderImpl;
import output.DataWriter;
import output.impl.DepartmentFileWriter;
import output.impl.ErrorLinesFileWriter;
import output.impl.StatisticsConsoleWriter;
import output.impl.StatisticsFileWriter;
import parser.PersonParser;
import parser.impl.PersonParserImpl;
import picocli.CommandLine;
import repo.ProcessedDataRepo;
import repo.RawDataRepo;
import repo.impl.ProcessedDataRepoImpl;
import repo.impl.RawDataRepoImpl;
import service.DepartmentBuildService;
import service.SortService;
import service.StatisticsService;
import service.impl.DepartmentBuildServiceImpl;
import service.impl.SortServiceImpl;
import service.impl.StatisticsServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        ApplicationArgs applicationArgs = new ApplicationArgs();
        CommandLine commandline = new CommandLine(applicationArgs);
        try {
            commandline.parseArgs(args);
            applicationArgs.validateArgs();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        List<String> foundLines = new ArrayList<>();
        PersonParser personParser = new PersonParserImpl();
        RawDataRepo rawDataRepo = new RawDataRepoImpl();
        ProcessedDataRepo processedDataRepo = new ProcessedDataRepoImpl();
        SortService sortService = new SortServiceImpl();
        DepartmentBuildService departmentBuildService = new DepartmentBuildServiceImpl();

        try {
            DataReader reader = new DataReaderImpl();
            foundLines = reader.readDirectory();
        } catch (IOException | StaffIsNotInitialized e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }

        personParser.parsePersons(
                foundLines,
                rawDataRepo
        );

        if (applicationArgs.getSortType() != null) {
            sortService.sortEmployeeList(
                    rawDataRepo.getEmployeeList(),
                    applicationArgs.getSortType(),
                    applicationArgs.getSortOrder()
            );
        }

        departmentBuildService.formDepartments(
                rawDataRepo.getManagerList(),
                rawDataRepo.getEmployeeList(),
                rawDataRepo.getErrorLines(),
                processedDataRepo.getDepartmentsList()
        );

        if (applicationArgs.getIsStatsNeeded()) {
            StatisticsService statisticsService = new StatisticsServiceImpl();
            statisticsService.collectStats(
                    rawDataRepo.getManagerList(),
                    processedDataRepo.getDepartmentsList(),
                    processedDataRepo.getCollectedStats()
            );
            sortService.sortStatistics(processedDataRepo.getCollectedStats());
            if (applicationArgs.getStatsOutputMethod().equals(StatsOutputMethod.console)) {
                System.out.println("department, min, max, mid");
                DataWriter<DepartmentStatsDto> statsConsoleWriter = new StatisticsConsoleWriter();
                for (DepartmentStatsDto departmentStatsDto : processedDataRepo.getCollectedStats()) {
                    statsConsoleWriter.write(departmentStatsDto);
                }
            } else if (applicationArgs.getStatsOutputMethod().equals(StatsOutputMethod.file)) {
                DataWriter<List<DepartmentStatsDto>> statsFileWriter = new StatisticsFileWriter(
                        applicationArgs.getOutputPath()
                );
                statsFileWriter.write(processedDataRepo.getCollectedStats());
            }
        }
        try {
            DataWriter<Department> departmentWriter = new DepartmentFileWriter();
            for (Department department : processedDataRepo.getDepartmentsList()) {
                departmentWriter.write(department);
            }
            DataWriter<List<String>> errorLinesWriter = new ErrorLinesFileWriter();
            errorLinesWriter.write(rawDataRepo.getErrorLines());
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}
