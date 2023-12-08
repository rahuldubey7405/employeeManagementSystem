package com.springBootProject.service;

import java.util.ArrayList;
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

	// with order By ASC
	public List<Employee> GetAllEmployee() {
		List<Employee> allEmployee = employeeRepository.findAllByOrderByJoiningDateAsc();
		return allEmployee;
	}

	public Employee UpdateEmployee(Employee emp, int empId) {
		emp.setId(empId);
		return employeeRepository.save(emp);
	}

	// HardDelete Or FostDelete
	public void DeleteEmployee(int empId) {
		employeeRepository.deleteById(empId);
	}

	public Employee GetEmployeeById(int empId) {
		try {
			return employeeRepository.findById(empId).get();
		} catch (Exception e) {
			return null;
		}
	}

	public List<Employee> GetEmployeeByDepatment(String department) {
		List<Employee> findByDepartment = employeeRepository.findByDepartment(department);
//		for(Employee e :findByDepartment) {
//			
//		}
		return findByDepartment;

	}
}
