package service;

import domain.Department;
import domain.Manager;
import repo.ProcessedDataRepo;

import java.util.List;

public interface StatisticsService {
    void collectStats (List<Manager> managerList, List<Department> departmentList, ProcessedDataRepo processedDataRepo);
}
