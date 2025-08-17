package service;

import args.fields.SortOrder;
import args.fields.SortType;
import domain.Employee;
import dto.DepartmentStatsDto;

import java.util.List;

public interface SortService {
    void sortEmployeeList (List<Employee> employeeList, SortType sortType, SortOrder sortOrder);
    void sortStatistics (List<DepartmentStatsDto> departmentStats);
}
