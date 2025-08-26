package repo;

import domain.Department;
import dto.DepartmentStatsDto;

import java.util.List;

public interface ProcessedDataRepo {
    void addDepartment (Department department);
    void addDepartmentStats (DepartmentStatsDto departmentStatsDto);
    List<Department> getDepartmentsList();
    List<DepartmentStatsDto> getCollectedStats();
}
