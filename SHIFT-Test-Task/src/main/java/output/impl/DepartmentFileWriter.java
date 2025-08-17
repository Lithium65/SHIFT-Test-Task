package output.impl;

import domain.Employee;
import domain.Manager;
import output.DataWriter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class DepartmentFileWriter implements DataWriter<Map<Integer, List<Employee>>> {
    @Override
    public void write (Map<Integer, List<Employee>> departmentsMap) throws IOException {

    }
}
