package output.impl;

import dto.DepartmentStatsDto;
import output.DataWriter;

import java.io.IOException;
import java.util.List;

public class StatisticsFileWriter implements DataWriter<List<DepartmentStatsDto>> {
    @Override
    public void write(List<DepartmentStatsDto> data) throws IOException {

    }
}
