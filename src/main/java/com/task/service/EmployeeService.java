package com.task.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.task.model.Department;
import com.task.model.Employee;

public interface EmployeeService {
	public List<Employee> getAllEmployees();
	public Employee getEmployeeById(int id);
	public boolean updateEmployeeById(Employee emp);
	public List<Department> getAllDepartments();
	public boolean deleteEmployeeById(int id);
	public boolean addEmployee(Employee emp);
	public boolean deleteEmployeeByIds(List<Integer> ids);
	public List<Employee> searchByCriteria(String searchCriteria);
	public List<Employee> searchByEmpId(int empId);
	public List<Employee> searchEmployeeByNameEmail(String firstName, String lastName, String email);
	public Employee login(String userId, String password);
	public List<Department> getAllDepartment();
	public boolean register(Employee employeee);
}
