package springboot._backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot._backend.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
