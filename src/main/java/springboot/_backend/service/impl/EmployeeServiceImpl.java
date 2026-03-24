package springboot._backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;  // <-- IMPORT
import org.springframework.stereotype.Service;
import springboot._backend.dto.EmployeeDto;
import springboot._backend.entity.Employee;
import springboot._backend.mapper.EmployeeMapper;
import springboot._backend.repository.EmployeeRepository;
import springboot._backend.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }
}