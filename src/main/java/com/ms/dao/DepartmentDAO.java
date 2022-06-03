package com.ms.dao;

import java.util.List;

import com.ms.entity.Department;

public interface DepartmentDAO {
	List<Department> getList(String url);
	List<Department> getDataBaseData();
	List<Department> getDepartment(int e_id);
	void addDepartments(String url);
}
