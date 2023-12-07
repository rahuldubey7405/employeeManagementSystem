package com.springBootProject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springBootProject.model.Employee;
import com.springBootProject.repository.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;

	public Employee AddEmployee(Employee emp) {
		Employee save = employeeRepository.save(emp);
		return save;
	}

	public List<Employee> GetAllEmployee() {
		List<Employee> allEmployee = employeeRepository.findAll();
		return allEmployee;
	}

	public Employee UpdateEmployee(Employee emp, int empId) {
		emp.setId(empId);
		return employeeRepository.save(emp);
	}

	// HardDeleted
	public void DeleteEmployee(int empId) {
		employeeRepository.deleteById(empId);
	}

	public Employee GetEmployeeById(int empId) {
		return employeeRepository.findById(empId).get();

	}
}
