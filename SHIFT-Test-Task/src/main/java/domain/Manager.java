package domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public final class Manager extends Person{
    private String department;

    public Manager (int id, String name, Double salary, String department) {
        super(id, name, salary);
        this.department = department;
    }

    @Override
    public String toString () {
        return super.toString() + ", " + department;
    }
}
