package com.springBootProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springBootProject.model.Employee;
import com.springBootProject.service.EmployeeService;

@RestController
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	@PostMapping(value = "/employee")
	public ResponseEntity addEmployee(@RequestBody Employee emp) {
		Employee employee = employeeService.AddEmployee(emp);

		return new ResponseEntity(employee, HttpStatus.CREATED);// status code 201
	}

	@GetMapping(value = "/employee")
	public ResponseEntity getAllEmployee() {
		List<Employee> employees = employeeService.GetAllEmployee();

		return new ResponseEntity(employees, HttpStatus.OK);// status code 200
	}

	@GetMapping(value = "/employee/{employeeId}")
	public ResponseEntity getEmployeeById(@PathVariable(required = true) int employeeId) {
		Employee getEmployeeById = employeeService.GetEmployeeById(employeeId);
		return new ResponseEntity(getEmployeeById, HttpStatus.OK);// status code 200
	}

	@PutMapping(value = "/employee/{employeeId}")
	public ResponseEntity updateEmployee(@RequestBody Employee emp, @PathVariable(required = true) int employeeId) {
		Employee employee = employeeService.UpdateEmployee(emp, employeeId);

		return new ResponseEntity(employee, HttpStatus.OK);
	}

	@DeleteMapping(value = "/employee/{employeeId}")
	public ResponseEntity deleteEmployee(int empId) {
		employeeService.DeleteEmployee(empId);

		return new ResponseEntity("", HttpStatus.OK);
	}

}
