package service.impl;

import args.fields.SortOrder;
import args.fields.SortType;
import domain.Employee;
import dto.DepartmentStatsDto;
import service.SortService;

import java.util.Comparator;
import java.util.List;

public class SortServiceImpl implements SortService {

    @Override
    public void sortEmployeeList (List<Employee> employeeList, SortType sortType, SortOrder sortOrder) {
        Comparator<Employee> comparator;
        if (sortType == SortType.name) {
            comparator = Comparator.comparing(Employee::getName);
        }
        else if (sortType == SortType.salary) {
            comparator = Comparator.comparing(Employee::getSalary);
        }
        else throw new IllegalArgumentException("Unknown sort type " + sortType);

        if (sortOrder == SortOrder.desc) {
            comparator = comparator.reversed();
        } else if (sortOrder != SortOrder.asc) {
            throw new IllegalArgumentException("Unknown sort order " + sortOrder);
        }

        employeeList.sort(comparator);
    }

    @Override
    public void sortStatistics (List<DepartmentStatsDto> departmentStats) {
        Comparator<DepartmentStatsDto> comparator = Comparator.comparing(DepartmentStatsDto::department);
        departmentStats.sort(comparator);
    }
}
