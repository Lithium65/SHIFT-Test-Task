package dto;

public record DepartmentStatsDto (String department, Double minSalary,
                                  Double maxSalary, Double midSalary) {
    @Override
    public String toString() {
        return (department + ", " + minSalary + ", " + maxSalary + ", " + midSalary);
    }
}
