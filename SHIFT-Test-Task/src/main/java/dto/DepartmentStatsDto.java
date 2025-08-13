package dto;

import java.util.Locale;

public record DepartmentStatsDto (String department, Double minSalary,
                                  Double maxSalary, Double midSalary) {

    public DepartmentStatsDto(String department, Double minSalary, Double maxSalary, Double midSalary) {
        this.department = department;
        this.minSalary = (double) Math.round(minSalary * 100.0) / 100;
        this.maxSalary = (double) Math.round(maxSalary * 100) / 100;
        this.midSalary = (double) Math.round(midSalary * 100) / 100;
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "%s, %.2f, %.2f, %.2f", department, minSalary, maxSalary, midSalary);
    }
}
