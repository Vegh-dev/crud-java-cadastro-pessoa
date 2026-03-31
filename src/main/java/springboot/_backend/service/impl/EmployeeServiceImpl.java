package springboot._backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;  // <-- IMPORT
import org.springframework.stereotype.Service;
import springboot._backend.dto.EmployeeDto;
import springboot._backend.entity.Employee;
import springboot._backend.exception.ResourceNotFoundException;
import springboot._backend.mapper.EmployeeMapper;
import springboot._backend.repository.EmployeeRepository;
import springboot._backend.service.EmployeeService;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public EmployeeDto getEmployeeByID(long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Funcionário com esse Id não existe : " + employeeId));

        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
       List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map((employee) ->EmployeeMapper.mapToEmployeeDto(employee))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updateEmployee) {

       Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Não existe um funcionário com esse id:" + employeeId)
        );

       employee.setFirstName(updateEmployee.getFirstName());
       employee.setLastName(updateEmployee.getLastName());
       employee.setEmail(updateEmployee.getEmail());

       Employee updateEmployeeObj = employeeRepository.save(employee);

       return EmployeeMapper.mapToEmployeeDto(updateEmployeeObj);
    }

    @Override
    public void deleteEmployee(Long employeeId) {

        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Não existe um funcionário com esse id:" + employeeId)
        );

        employeeRepository.deleteById(employeeId);



    }
}