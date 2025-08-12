package service.impl;

import service.DepartmentBuildService;
import domain.Employee;
import domain.Manager;
import repo.DataRepo;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

public class DepartmentBuildServiceImpl implements DepartmentBuildService {

    @Override
    public void formDepartments (DataRepo dataRepo) {
        Set<Integer> managersIdSet = dataRepo.getManagerList()
                .stream()
                .map(Manager::getId)
                .collect(Collectors.toSet());

    for (Employee employee : dataRepo.getEmployeeList()) {
        if (managersIdSet.contains(employee.getManagerId())) {
            dataRepo.getDepartmentsMap()
                    .computeIfAbsent(employee.getManagerId(), id -> new ArrayList<>())
                    .add(employee);
            } else dataRepo.addInvalidLine(employee.toString());
        }
    }
}
