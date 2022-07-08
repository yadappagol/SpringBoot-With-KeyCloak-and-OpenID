package com.javatechie.keycloak.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javatechie.keycloak.entity.Employee;
import com.javatechie.keycloak.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Employee addEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployee() {
		return employeeRepository.findAll();
	}

	@Override
	public Optional<Employee> getById(int empId) {
		return employeeRepository.findById(empId);

	}

	@Override
	public Employee updateEmployee(Employee employee) {
		Employee employee2 = employeeRepository.findById(employee.getId()).get();
		BeanUtils.copyProperties(employee, employee2);
		return employeeRepository.save(employee2);
	}

	@Override
	public void deleteEmployee(int empId) {
		employeeRepository.deleteById(empId);
	}
}
