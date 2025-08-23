package repo;

import domain.Department;
import dto.DepartmentStatsDto;

import java.util.List;

public interface ProcessedDataRepo {
    List<Department> getDepartmentsList();
    List<DepartmentStatsDto> getCollectedStats();
}
