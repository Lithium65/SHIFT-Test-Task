package service.impl;

import domain.Department;
import domain.Employee;
import domain.Manager;
import dto.DepartmentStatsDto;
import repo.ProcessedDataRepo;
import service.StatisticsService;

import java.util.DoubleSummaryStatistics;
import java.util.List;

public class StatisticsServiceImpl implements StatisticsService {

    @Override
    public void collectStats (List<Manager> managerList, List<Department> departmentList, ProcessedDataRepo processedDataRepo) {
            for (Department department : departmentList) {
                DoubleSummaryStatistics departmentStatistics = new DoubleSummaryStatistics();
                if (department.getEmployeeList() != null && !department.getEmployeeList().isEmpty()) {
                    for (Employee employee : department.getEmployeeList()) {
                        departmentStatistics.accept(employee.getSalary());
                    }
                    processedDataRepo.addDepartmentStats(new DepartmentStatsDto(
                            department.getManager().getDepartment(),
                            departmentStatistics.getMin(),
                            departmentStatistics.getMax(),
                            departmentStatistics.getAverage())
                    );
                } else {
                    processedDataRepo.addDepartmentStats(new DepartmentStatsDto(
                            department.getManager().getDepartment(), 0.00, 0.00, 0.00));
                }
            }
    }
}
