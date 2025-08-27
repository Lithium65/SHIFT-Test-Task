package output.impl;

import dto.DepartmentStatsDto;
import output.DataWriter;

public class StatisticsConsoleWriter implements DataWriter<DepartmentStatsDto> {
    @Override
    public void write (DepartmentStatsDto departmentStats) {
        System.out.println(departmentStats);
    }
}
