package springboot._backend.service;

import springboot._backend.dto.EmployeeDto;
import springboot._backend.entity.Employee;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);
}
