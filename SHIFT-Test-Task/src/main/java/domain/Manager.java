package domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Manager extends Person{
    private String department;

    public Manager (int id, String name, Double salary, String department) {
        super(id, name, salary);
        this.department = department;
    }
}
