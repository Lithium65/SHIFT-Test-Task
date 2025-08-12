package dto;

public record DepartmentStatsDto (String department, Integer minSalary, Integer maxSalary, Integer midSalary) {
    @Override
    public String toString() {
        return (department + ", " + minSalary + ", " + maxSalary + ", " + minSalary);
    }
}
