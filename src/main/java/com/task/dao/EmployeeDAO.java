package com.task.dao;
import java.util.List;

import com.task.model.DepartmentEntity;
import com.task.model.EmployeeEntity;

public interface EmployeeDAO {
	public List<EmployeeEntity> getAllEmployees();
	public EmployeeEntity getEmployeeById(int id);
	public boolean updateEmployeeById(EmployeeEntity emp);
	public List<DepartmentEntity> getAllDepartments();
	public boolean deleteEmployeeById(int id);
	public boolean addEmployee(EmployeeEntity emp);
	public boolean deleteEmployeeByIds(List<Integer> ids);
	public List<EmployeeEntity> searchByCriteria(String searchCriteria);
	public List<EmployeeEntity> searchByEmpId(int empId);
	public List<EmployeeEntity> searchEmployeeByNameEmail(String firstName, String lastName, String email);
	public EmployeeEntity login(String userId, String password);
	public int register(EmployeeEntity employeeEntity);
}
