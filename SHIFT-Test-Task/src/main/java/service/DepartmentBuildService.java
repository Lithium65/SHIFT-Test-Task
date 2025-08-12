package service;

import repo.ProcessedDataRepo;
import repo.RawDataRepo;

public interface DepartmentBuildService {
    void formDepartments (RawDataRepo rawDataRepo, ProcessedDataRepo processedDataRepo);
}
