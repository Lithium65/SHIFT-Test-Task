package repo.impl;

import domain.Employee;
import dto.DepartmentStatsDto;
import lombok.Getter;
import repo.ProcessedDataRepo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Getter
public class ProcessedDataRepoImpl implements ProcessedDataRepo {
    private Map<Integer, List<Employee>> departmentsMap = new LinkedHashMap<>();
    private List<DepartmentStatsDto> collectedStats = new ArrayList<>();

    @Override
    public void addDepartmentStats (DepartmentStatsDto departmentStatsDto) {
        collectedStats.add(departmentStatsDto);
    }
}
