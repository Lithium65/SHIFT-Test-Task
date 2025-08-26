package factory;

import domain.Department;
import dto.DepartmentStatsDto;
import input.DataReader;
import input.impl.SbDataReaderImpl;
import output.DataWriter;
import output.impl.DepartmentFileWriter;
import output.impl.ErrorLinesFileWriter;
import output.impl.StatisticsConsoleWriter;
import output.impl.StatisticsFileWriter;
import parser.PersonParser;
import parser.impl.PersonParserImpl;
import repo.ProcessedDataRepo;
import repo.RawDataRepo;
import repo.impl.ProcessedDataRepoImpl;
import repo.impl.RawDataRepoImpl;
import runner.ApplicationRunner;
import service.DepartmentBuildService;
import service.SortService;
import service.StatisticsService;
import service.impl.DepartmentBuildServiceImpl;
import service.impl.SortServiceImpl;
import service.impl.StatisticsServiceImpl;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public final class ApplicationFactory {

    private ApplicationFactory() {
    }

    public static ApplicationRunner createAppRunner() throws IOException {
        return new ApplicationRunner (createReader(), createPersonParser(),
                createRawDataRepo(), createProcessedDataRepo(),
                createSortService(), createDeptBuildService(),
                createDeptWriter(), createErrorLinesWriter()
        );
    }

    public static DataReader createReader() throws IOException {
        return new SbDataReaderImpl();
    }

    public static RawDataRepo createRawDataRepo () {
        return new RawDataRepoImpl();
    }

    public static ProcessedDataRepo createProcessedDataRepo() {
        return new ProcessedDataRepoImpl();
    }

    public static DepartmentBuildService createDeptBuildService() {
        return new DepartmentBuildServiceImpl();
    }

    public static SortService createSortService() {
        return new SortServiceImpl();
    }

    public static PersonParser createPersonParser() {
        return new PersonParserImpl();
    }

    public static DataWriter<DepartmentStatsDto> createStatsConsoleWriter() {
        return new StatisticsConsoleWriter();
    }

    public static StatisticsService createStatsService() {
        return new StatisticsServiceImpl();
    }

    public static DataWriter<List<DepartmentStatsDto>> createStatsFileWriter(Path outputDir) {
        return new StatisticsFileWriter(outputDir);
    }

    public static DataWriter<Department> createDeptWriter() throws IOException {
        return new DepartmentFileWriter();
    }

    public static DataWriter<List<String>> createErrorLinesWriter() throws IOException {
        return new ErrorLinesFileWriter();
    }
}
