package repo;

import domain.Employee;
import dto.DepartmentStatsDto;

import java.util.List;
import java.util.Map;

public interface ProcessedDataRepo {
    void addDepartmentStats (DepartmentStatsDto departmentStatsDto);
    Map<Integer, List<Employee>> getDepartmentsMap();
    List<DepartmentStatsDto> getCollectedStats();
}
