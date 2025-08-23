package service;

import domain.Department;
import domain.Manager;
import dto.DepartmentStatsDto;

import java.util.List;

public interface StatisticsService {
    void collectStats (List<Manager> managerList, List<Department> departmentList, List<DepartmentStatsDto> departmentsStats);
}
