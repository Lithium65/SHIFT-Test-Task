package service;

import domain.Employee;
import domain.Manager;

public interface PersonValidationService {
    boolean isValid(Manager manager);
    boolean isValid(Employee employee);
}
