package repo.impl;

import domain.Department;
import dto.DepartmentStatsDto;
import lombok.Getter;
import repo.ProcessedDataRepo;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ProcessedDataRepoImpl implements ProcessedDataRepo {
    private List<Department> departmentsList = new ArrayList<>();
    private List<DepartmentStatsDto> collectedStats = new ArrayList<>();
}
