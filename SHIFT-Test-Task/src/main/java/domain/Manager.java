package domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public final class Manager extends Person{
    private String department;

    public Manager (int id, String name, Double salary, String department) {
        super(id, name, salary);
        this.department = department;
    }
}
