package domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
abstract class Person {
    protected int id;
    protected String name;
    protected Double salary;

    protected Person (int id, String name, Double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }
}
