package output.impl;

import dto.DepartmentStatsDto;
import output.DataWriter;

import java.util.List;

public class StatisticsConsoleWriter implements DataWriter<List<DepartmentStatsDto>> {
    @Override
    public void write (List<DepartmentStatsDto> departmentStats) {
        System.out.println("department, min, max, mid");
        for (DepartmentStatsDto departmentStatsDto : departmentStats) {
            System.out.println(departmentStatsDto);
        }
    }
}
