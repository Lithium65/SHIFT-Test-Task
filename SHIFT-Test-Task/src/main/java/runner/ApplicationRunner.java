package runner;

import args.ApplicationArgs;
import args.fields.StatsOutputMethod;
import domain.Department;
import dto.DepartmentStatsDto;
import exception.StaffIsNotInitialized;
import factory.ApplicationFactory;
import input.DataReader;
import lombok.AllArgsConstructor;
import output.DataWriter;
import parser.PersonParser;
import repo.ProcessedDataRepo;
import repo.RawDataRepo;
import service.DepartmentBuildService;
import service.SortService;
import service.StatisticsService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class ApplicationRunner {
    private final DataReader reader;
    private final PersonParser personParser;
    private final RawDataRepo rawDataRepo;
    private final ProcessedDataRepo processedDataRepo;
    private final SortService sortService;
    private final DepartmentBuildService departmentBuildService;
    private final DataWriter<Department> departmentWriter;
    private final DataWriter<List<String>> errorLinesWriter;

    public void run(ApplicationArgs args) {
        List<String> foundLines = new ArrayList<>();

        try {
            foundLines = reader.readDirectory();
        } catch (IOException | StaffIsNotInitialized e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }

        personParser.parsePersons(
                foundLines,
                rawDataRepo
        );

        if (args.getSortType() != null) {
            sortService.sortEmployeeList(
                    rawDataRepo.getEmployeeList(),
                    args.getSortType(),
                    args.getSortOrder()
            );
        }

        departmentBuildService.formDepartments(
                rawDataRepo.getManagerList(),
                rawDataRepo.getEmployeeList(),
                rawDataRepo.getErrorLines(),
                processedDataRepo
        );

        if (args.getIsStatsNeeded()) {
            StatisticsService statisticsService = ApplicationFactory.createStatsService();

            statisticsService.collectStats(
                    rawDataRepo.getManagerList(),
                    processedDataRepo.getDepartmentsList(),
                    processedDataRepo
            );

            sortService.sortStatistics(processedDataRepo.getCollectedStats());
            if (args.getStatsOutputMethod().equals(StatsOutputMethod.console)) {
                DataWriter<DepartmentStatsDto> statsConsoleWriter = ApplicationFactory.createStatsConsoleWriter();
                System.out.println("department, min, max, mid");
                for (DepartmentStatsDto departmentStatsDto : processedDataRepo.getCollectedStats()) {
                    statsConsoleWriter.write(departmentStatsDto);
                }
            } else if (args.getStatsOutputMethod().equals(StatsOutputMethod.file)) {
                DataWriter<List<DepartmentStatsDto>> statsFileWriter = ApplicationFactory.createStatsFileWriter(
                        args.getOutputPath()
                );
                statsFileWriter.write(processedDataRepo.getCollectedStats());
            }
        }
        for (Department department : processedDataRepo.getDepartmentsList()) {
            departmentWriter.write(department);
        }
        errorLinesWriter.write(rawDataRepo.getErrorLines());
    }
}
