package com.task.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.task.dao.EmployeeDAO;
import com.task.model.Department;
import com.task.model.DepartmentEntity;
import com.task.model.Employee;
import com.task.model.EmployeeEntity;
import com.task.util.DateTimeFormatUtil;

@Component
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeDAO employeeDao;

	@Override
	public List<Employee> getAllEmployees() {
		List<EmployeeEntity> empList = employeeDao.getAllEmployees();
		List<Employee> employeeList = new ArrayList<Employee>();
		Employee employee = null;
		EmployeeEntity tempEmp = null;
		DepartmentEntity tempDeptEntity = null;
		Department dept = null;
		for (int i = 0, size = empList.size(); i <= size; i++) {
			employee = new Employee();
			tempEmp = empList.get(i);
			employee.setAddress(tempEmp.getAddress());
//			employee.setCreatedDate(tempEmp.getCreatedDate());
			employee.setCreatedDateAsString(DateTimeFormatUtil.DateAsDDMMYYYY(tempEmp.getCreatedDate()));
			tempDeptEntity = tempEmp.getDepartment();
			dept = new Department();
			dept.setId(tempDeptEntity.getId());
			dept.setLocation(tempDeptEntity.getLocation());
			dept.setName(tempDeptEntity.getName());
			employee.setDepartment(dept);
			employee.setDobAsString(DateTimeFormatUtil.DateAsDDMMYYYY(tempEmp.getDob()));
			employee.setEmail(tempEmp.getEmail());
			employee.setFirstName(tempEmp.getFirstName());
			employee.setLastName(tempEmp.getLastName());
			employeeList.add(employee);
		}
		return employeeList;
	}

	@Override
	public Employee getEmployeeById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateEmployeeById(Employee emp) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Department> getAllDepartments() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteEmployeeById(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addEmployee(Employee emp) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteEmployeeByIds(List<Integer> ids) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Employee> searchByCriteria(String searchCriteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> searchByEmpId(int empId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> searchEmployeeByNameEmail(String firstName, String lastName, String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee login(String userId, String password) {
		EmployeeEntity employeeEntity = employeeDao.login(userId, password);
		Employee employee = null;
		if(employeeEntity != null) {
			employee = new Employee();
			employee.setAddress(employeeEntity.getAddress());
			employee.setCreatedDateAsString(DateTimeFormatUtil.DateAsDDMMYYYY(employeeEntity.getCreatedDate()));
			employee.setFirstName(employeeEntity.getFirstName());
			employee.setLastName(employeeEntity.getLastName());
		}
		return employee;
	}

	@Override
	public List<Department> getAllDepartment() {
		List<Department> deptList = null;
		try {
			List<DepartmentEntity> depts = employeeDao.getAllDepartments();
			deptList = new ArrayList<Department>();
			DepartmentEntity tempDept = null;
			Department dept = null;
			for (int i = 0, size = depts.size(); i < size; i++) {
				tempDept = depts.get(i);
				dept = new Department();
				dept.setCreationDate(tempDept.getCreationDate());
				dept.setId(tempDept.getId());
				dept.setName(tempDept.getName());
				deptList.add(dept);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return deptList;
	}

	@Override
	public boolean register(Employee employeee) {
		EmployeeEntity employeeEntity = new EmployeeEntity();
		employeeEntity.setAddress(employeee.getAddress());
		employeeEntity.setCreatedDate(new Date());
		DepartmentEntity deptEntity = new DepartmentEntity();
		deptEntity.setId(employeee.getDepartment().getId());
		employeeEntity.setDepartment(deptEntity);
		employeeEntity.setDob(DateTimeFormatUtil.dateFromString(employeee.getDobAsString()));
		employeeEntity.setEmail(employeee.getEmail());
		employeeEntity.setFirstName(employeee.getFirstName());
		employeeEntity.setLastName(employeee.getLastName());
		employeeEntity.setPassword(employeee.getPassword());
		
		int id = employeeDao.register(employeeEntity);
		return (id != 0) ? true : false;
	}
	
	

}
