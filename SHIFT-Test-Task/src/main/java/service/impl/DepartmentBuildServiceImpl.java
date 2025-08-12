package service.impl;

import repo.ProcessedDataRepo;
import repo.RawDataRepo;
import service.DepartmentBuildService;
import domain.Employee;
import domain.Manager;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

public class DepartmentBuildServiceImpl implements DepartmentBuildService {

    @Override
    public void formDepartments (RawDataRepo rawDataRepo, ProcessedDataRepo processedDataRepo) {
        Set<Integer> managersIdSet = rawDataRepo.getManagerList()
                .stream()
                .map(Manager::getId)
                .collect(Collectors.toSet());

    for (Employee employee : rawDataRepo.getEmployeeList()) {
        if (managersIdSet.contains(employee.getManagerId())) {
            processedDataRepo.getDepartmentsMap()
                    .computeIfAbsent(employee.getManagerId(), id -> new ArrayList<>())
                    .add(employee);
            } else rawDataRepo.addInvalidLine(employee.toString());
        }
    }
}
