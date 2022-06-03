package com.ms.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ms.dao.DepartmentDAO;
import com.ms.dao.EmployeeDAO;
import com.ms.entity.Department;
import com.ms.entity.Employee;

@RestController
public class DataController {
	
	@Autowired
	DepartmentDAO departmentDAO;
	
	@Autowired
	EmployeeDAO employeeDAO;
	
	@RequestMapping("/")
	public String welcome() {
		return "welcome to Data Viewer";
	}
	//save and display saved database from Database
	@GetMapping("/data")
	public List<Object> getListFromDB(){
		List<Object> lst = new ArrayList<>();
		lst.add(departmentDAO.getDataBaseData());
		lst.add(employeeDAO.getDataBaseData());
		return lst;
	}
	  
	@GetMapping("/employee/{e_id}")
	public String getEmployeeRecord(@PathVariable int e_id){
		try {
			return employeeDAO.getEmployee(e_id).toString();
		} catch (Exception e) {
			return "Enter valid id";
		}
	}
	
	@GetMapping(path="/department/{dept_id}")
	public String getDepartmentRecord(@PathVariable int dept_id){
		try {
			return departmentDAO.getDepartment(dept_id).toString();
		} catch (Exception e) {
			return "Enter valid id";
		}
	}

	@PostMapping(path="/data/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String addEmployeeRecord(@RequestParam(name="url") String url) {
		try {
			employeeDAO.addEmployees(url);
			departmentDAO.addDepartments(url);
			return "Data Got Persisted ..";
		} catch (Exception e) {
			e.getMessage();
			return "Data Persistance Failed !";
		}
	}
	
}
