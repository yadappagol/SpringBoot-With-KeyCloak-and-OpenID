package com.javatechie.keycloak.service;

import java.util.List;
import java.util.Optional;

import com.javatechie.keycloak.entity.Employee;

public interface EmployeeService {

	Employee addEmployee(Employee employee);

	Employee updateEmployee(Employee employee);

	List<Employee> getAllEmployee();

	Optional<Employee> getById(int empId);

	void deleteEmployee(int empId);

}
