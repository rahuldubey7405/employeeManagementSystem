package com.springBootProject.model;

import java.sql.Date;
import java.time.LocalDate;

import org.hibernate.annotations.DialectOverride.Where;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "employee")

public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "name", nullable = false)
	private String name;
	@NotEmpty(message = "Email cannot be empty")
	@Email(message = "Email should be valid")
	@Column(name = "email")
	private String email;
	@Column(name = "department", nullable = false)
	private String department;
	@JsonFormat(pattern = "yyyy-MM-dd") // "yyyy-MM-dd HH:mm:ss"
	@Column(name = "joiningdate", nullable = false)
	private LocalDate joiningDate;
//	@Column(name = "deletestatus", nullable = false)
//	private boolean deleteStatus = false;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public LocalDate getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(LocalDate joiningDate) {
		this.joiningDate = joiningDate;
	}

}
