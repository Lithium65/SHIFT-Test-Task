package domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
abstract class Person {
    private int id;
    private String name;
    private Double salary;

    protected Person (int id, String name, Double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    @Override
    public String toString() {
       return id + ", " + name + ", " + salary;
    }
}
