package runner;

import args.ApplicationArgs;
import args.fields.StatsOutputMethod;
import com.google.inject.Inject;
import com.google.inject.Provider;
import domain.Department;
import dto.DepartmentStatsDto;
import exception.StaffIsNotInitialized;
import input.DataReader;
import output.CustomPathDataWriter;
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

public class ApplicationRunner {
    private final DataReader reader;
    private final PersonParser personParser;
    private final RawDataRepo rawDataRepo;
    private final ProcessedDataRepo processedDataRepo;
    private final Provider<SortService> sortServiceProvider;
    private final Provider<StatisticsService> statisticsServiceProvider;
    private final DepartmentBuildService departmentBuildService;
    private final DataWriter<Department> departmentWriter;
    private final DataWriter<List<String>> errorLinesWriter;
    private final Provider<DataWriter<DepartmentStatsDto>> statsConsoleWriterProvider;
    private final Provider<CustomPathDataWriter<List<DepartmentStatsDto>>> statsFileWriterProvider;

    @Inject
    public ApplicationRunner(DataReader reader, PersonParser personParser,
                             RawDataRepo rawDataRepo, ProcessedDataRepo processedDataRepo,
                             Provider<SortService> sortServiceProvider, Provider<StatisticsService> statisticsServiceProvider,
                             DepartmentBuildService departmentBuildService, DataWriter<Department> departmentWriter,
                             DataWriter<List<String>> errorLinesWriter, Provider<DataWriter<DepartmentStatsDto>> statsConsoleWriterProvider, Provider<CustomPathDataWriter<List<DepartmentStatsDto>>> statsFileWriterProvider) {
        this.reader = reader;
        this.personParser = personParser;
        this.rawDataRepo = rawDataRepo;
        this.processedDataRepo = processedDataRepo;
        this.sortServiceProvider = sortServiceProvider;
        this.statisticsServiceProvider = statisticsServiceProvider;
        this.departmentBuildService = departmentBuildService;
        this.departmentWriter = departmentWriter;
        this.errorLinesWriter = errorLinesWriter;
        this.statsConsoleWriterProvider = statsConsoleWriterProvider;
        this.statsFileWriterProvider = statsFileWriterProvider;
    }

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
            SortService sortService = sortServiceProvider.get();
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
            StatisticsService statisticsService = statisticsServiceProvider.get();

            statisticsService.collectStats(
                    rawDataRepo.getManagerList(),
                    processedDataRepo.getDepartmentsList(),
                    processedDataRepo
            );

            SortService sortService = sortServiceProvider.get();
            sortService.sortStatistics(processedDataRepo.getCollectedStats());
            if (args.getStatsOutputMethod().equals(StatsOutputMethod.console)) {
                DataWriter<DepartmentStatsDto> statsConsoleWriter = statsConsoleWriterProvider.get();
                System.out.println("department, min, max, mid");
                for (DepartmentStatsDto departmentStatsDto : processedDataRepo.getCollectedStats()) {
                    statsConsoleWriter.write(departmentStatsDto);
                }
            } else if (args.getStatsOutputMethod().equals(StatsOutputMethod.file)) {
                CustomPathDataWriter<List<DepartmentStatsDto>> statsFileWriter = statsFileWriterProvider.get();
                statsFileWriter.write(processedDataRepo.getCollectedStats(), args.getOutputPath());
            }
        }
        for (Department department : processedDataRepo.getDepartmentsList()) {
            departmentWriter.write(department);
        }
        errorLinesWriter.write(rawDataRepo.getErrorLines());
    }
}
