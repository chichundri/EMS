package com.task.util;

import java.beans.PropertyEditorSupport;

import com.task.model.Department;

public class DepartmentEditor extends PropertyEditorSupport {
	// This will be called when user HTTP Post to server a field bound to
	// DepartmentVO
	@Override
	public void setAsText(String id) {
		int deptId = Integer.parseInt(id);
		Department department = new Department();
		department.setId(deptId);
		this.setValue(department);
	}
}