package service;

import repo.ProcessedDataRepo;
import repo.RawDataRepo;

public interface StatisticsService {
    void collectStats (RawDataRepo rawDataRepo, ProcessedDataRepo processedDataRepo);
}
