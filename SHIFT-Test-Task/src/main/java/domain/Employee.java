package domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public final class Employee extends Person {
    private int managerId;

    public Employee (int id, String name, Double salary, int managerId) {
        super (id, name, salary);
        this.managerId = managerId;
    }
}
