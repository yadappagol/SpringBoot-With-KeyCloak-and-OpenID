package com.javatechie.keycloak.controller;

import java.util.List;
import java.util.Optional;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javatechie.keycloak.entity.Employee;
import com.javatechie.keycloak.service.EmployeeServiceImpl;

@SpringBootApplication
@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeServiceImpl service;

	@PostMapping("/addEmployee")
	@RolesAllowed("user")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
		return ResponseEntity.ok(service.addEmployee(employee));
	}

	// this method can be accessed by user whose role is user
	@GetMapping("/{employeeId}")
	@RolesAllowed("user")
	public ResponseEntity<Optional<Employee>> getEmployee(@PathVariable int employeeId) {
		return ResponseEntity.ok(service.getById(employeeId));
	}

	// this method can be accessed by user whose role is admin
	@GetMapping
	@RolesAllowed("admin")
	public ResponseEntity<List<Employee>> findALlEmployees() {
		return ResponseEntity.ok(service.getAllEmployee());
	}

	@PutMapping("/updateEmployee")
	@RolesAllowed({ "user", "admin" })
	public ResponseEntity<?> updateEmployee(@RequestBody Employee employee) {
		return ResponseEntity.ok(service.updateEmployee(employee));
	}

	@DeleteMapping("/deleteEmployee/{empId}")
	@RolesAllowed("admin")
	public ResponseEntity<?> deleteEmployee(@PathVariable int empId) {
		service.deleteEmployee(empId);
		return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
	}

}
