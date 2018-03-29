package com.task.constants;

public interface SQLConstants {
	public static final String GET_ALL_EMPLOEES = "from EmployeeEntity";
	public static final String GET_ALL_EMPLOYEES_NATIVE = "select * from employee";
	public static final String GET_EMPLOEE_BY_ID = "SELECT e FROM Employee e WHERE e.id = :empId";
	public static final String UPDATE_EMP_BY_ID = "UPDATE Employee e SET e.firstName=:firstName,e.lastName=:lastName,e.dob=:dob,e.address=:address,e.email=:email,e.department=:department WHERE e.id=:id";
	public static final String GET_ALL_DEPTS = "SELECT d FROM DepartmentEntity d";
	public static final String DELETE_EMP_BY_ID = "DELETE FROM Employee e WHERE e.id=:id";
	public static final String DELETE_EMP_BY_IDS = "DELETE FROM Employee e WHERE e.id IN(:ids)";
	public static final String SELECT_USER_BY_USERID_PASSWORD = "SELECT e FROM EmployeeEntity e WHERE e.email=:userId AND e.password=:password";
}
