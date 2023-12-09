package com.springBootProject.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springBootProject.model.Employee;
import com.springBootProject.service.EmployeeService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	@PostMapping(value = "/employee")
	public ResponseEntity addEmployee(@RequestBody Employee emp) {
		Employee employee = employeeService.AddEmployee(emp);
		return new ResponseEntity(employee, HttpStatus.CREATED);// status code 201
	}

	@GetMapping(value = { "/employee" })
	public ResponseEntity getAllEmployee(@RequestParam("department") Optional<String> department,
			HttpServletRequest request) {
		String token = request.getHeader("USER_API_TOKEN");
		System.out.println(token);
		List empList = new ArrayList<>();
		if (department.isPresent()) {
			empList = employeeService.GetEmployeeByDepatment(department.get());
			System.out.println(empList);
		} else {
			empList = employeeService.GetAllEmployee();
		}
		return new ResponseEntity(empList, HttpStatus.OK);// status code 200
	}

	@GetMapping(value = "/employee/{employeeId}")
	public ResponseEntity getEmployeeById(@PathVariable(required = true) int employeeId) {
		Employee getEmployeeById = employeeService.GetEmployeeById(employeeId);
		if (getEmployeeById != null) {
			return new ResponseEntity(getEmployeeById, HttpStatus.OK);
		} else {
			return new ResponseEntity("{}", HttpStatus.OK);// status code 200
		}
	}

	@PutMapping(value = "/employee/{employeeId}")
	public ResponseEntity updateEmployee(@RequestBody Employee emp, @PathVariable(required = true) int employeeId) {
		Employee employee = employeeService.UpdateEmployee(emp, employeeId);

		return new ResponseEntity(employee, HttpStatus.OK);
	}

	// This methods work with Soft DELETE
	@DeleteMapping(value = "/employee/{employeeId}")
	public ResponseEntity deleteEmployee(@PathVariable(required = true) int employeeId) {
		employeeService.DeleteEmployee(employeeId);
		return new ResponseEntity("", HttpStatus.OK);
	}

//	@DeleteMapping(value = "/employee/{employeeId}")
//	public ResponseEntity Harddelete(@PathVariable(required = true) int employeeId) {
//		employeeService.DeleteEmployee(employeeId);
//		return new ResponseEntity("", HttpStatus.OK);
//	}

}
