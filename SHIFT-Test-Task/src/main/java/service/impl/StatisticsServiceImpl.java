package service.impl;

import domain.Employee;
import domain.Manager;
import dto.DepartmentStatsDto;
import repo.ProcessedDataRepo;
import repo.RawDataRepo;
import service.StatisticsService;

import java.util.DoubleSummaryStatistics;

public class StatisticsServiceImpl implements StatisticsService {

    @Override
    public void collectStats (RawDataRepo rawDataRepo, ProcessedDataRepo processedDataRepo) {
        for (Manager manager : rawDataRepo.getManagerList()) {
            DoubleSummaryStatistics departmentStatistics = new DoubleSummaryStatistics();
            if (processedDataRepo.getDepartmentsMap().get(manager.getId()) != null) {
                for (Employee employee : processedDataRepo.getDepartmentsMap().get(manager.getId())) {
                    departmentStatistics.accept(employee.getSalary());
                }
                processedDataRepo.addDepartmentStats(new DepartmentStatsDto(manager.getDepartment(), departmentStatistics.getMin(),
                        departmentStatistics.getMax(), departmentStatistics.getAverage()));
            } else {
                processedDataRepo.addDepartmentStats(new DepartmentStatsDto(manager.getDepartment(), 0.00,
                        0.00, 0.00));
            }
        }
    }
}
