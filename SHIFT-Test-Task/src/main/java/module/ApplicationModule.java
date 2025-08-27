package module;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import domain.Department;
import dto.DepartmentStatsDto;
import input.DataReader;
import input.impl.SbDataReaderImpl;
import output.CustomPathDataWriter;
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
import service.DepartmentBuildService;
import service.SortService;
import service.StatisticsService;
import service.PersonValidationService;
import service.impl.DepartmentBuildServiceImpl;
import service.impl.SortServiceImpl;
import service.impl.StatisticsServiceImpl;
import service.impl.PersonValidationServiceImpl;

import java.util.List;

public class ApplicationModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(DepartmentBuildService.class).to(DepartmentBuildServiceImpl.class);
        bind(SortService.class).to(SortServiceImpl.class);
        bind(StatisticsService.class).to(StatisticsServiceImpl.class);
        bind(PersonValidationService.class).to(PersonValidationServiceImpl.class);
        bind(DataReader.class).to(SbDataReaderImpl.class);
        bind(PersonParser.class).to(PersonParserImpl.class);
        bind(RawDataRepo.class).to(RawDataRepoImpl.class);
        bind(ProcessedDataRepo.class).to(ProcessedDataRepoImpl.class);
        bind(new TypeLiteral<DataWriter<Department>>() {})
                .to(DepartmentFileWriter.class);
        bind(new TypeLiteral<DataWriter<List<String>>>() {})
                .to(ErrorLinesFileWriter.class);
        bind(new TypeLiteral<DataWriter<DepartmentStatsDto>>() {})
                .to(StatisticsConsoleWriter.class);
        bind(new TypeLiteral<CustomPathDataWriter<List<DepartmentStatsDto>>>() {})
                .to(StatisticsFileWriter.class);
    }
}
