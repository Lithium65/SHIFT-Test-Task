package domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public final class Department {
    private final Manager manager;
    private final List<Employee> employeeList;
}
