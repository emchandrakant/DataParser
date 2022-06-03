package com.ms.dao;

import java.util.List;

import com.ms.entity.Employee;

public interface EmployeeDAO {
	List<Employee> getList(String url);
	List<Employee> getDataBaseData();
	Employee getEmployee(int e_id);
	void addEmployees(String url);
}
