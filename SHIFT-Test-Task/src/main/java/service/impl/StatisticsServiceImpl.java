package service.impl;

import domain.Employee;
import dto.DepartmentStatsDto;
import service.StatisticsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StatisticsServiceImpl implements StatisticsService {

    public List<DepartmentStatsDto> collectStats (Map<Integer, List<Employee>> departmentsStaff) {
        List<DepartmentStatsDto> collectedStats = new ArrayList<>();
    }
}
