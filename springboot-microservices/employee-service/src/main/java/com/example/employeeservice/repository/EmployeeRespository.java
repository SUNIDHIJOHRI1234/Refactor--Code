package com.example.employeeservice.repository;

import com.example.employeeservice.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRespository extends JpaRepository<Employee,Long> {

}
