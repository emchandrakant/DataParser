package com.ms.dao.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.ms.dao.EmployeeDAO;
import com.ms.entity.Employee;
import com.ms.util.Query;

@Service
public class EmployeeDAOImpl implements EmployeeDAO{
	
	@Autowired
	private JdbcTemplate jdbcTemplate; 
	
	String line="";

	private Query sqlquery= new Query();
	
	private final String INSERT_QUERY = sqlquery.readQueryAsString("employee_insert_query");
	private final String GET_EMPLOYEES_QUERY = sqlquery.readQueryAsString("get_employees_query");
	private final String GET_EMPLOYEE_QUERY = sqlquery.readQueryAsString("get_employee_query");
	
	@Override
	public List<Employee> getList(String url) {
		try(BufferedReader br = new BufferedReader(new FileReader(url))){
			line = br.readLine();
			List<Employee> lst=new ArrayList<>();
			
			while((line=br.readLine()) != null) {
				String [] data = line.split(",");
				
				lst.add(new Employee(Integer.parseInt(data[0]),data[1],data[2],data[3],Integer.parseInt(data[8])));
			}
			return lst;
		} catch (IOException e1) {
			e1.getMessage();
			e1.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Employee> getDataBaseData() {
		getList("E:\\work-personal\\employees.csv").forEach(e->
		jdbcTemplate.update(INSERT_QUERY, e.getE_id(),e.getFirst_name(),e.getLast_name(),e.getDate_of_birth(),e.getDept_id()));
		
		return jdbcTemplate.query(GET_EMPLOYEES_QUERY, new BeanPropertyRowMapper<Employee>(Employee.class));
	}

	@Override
	public Employee getEmployee(int e_id) throws DataAccessException{
			return jdbcTemplate.queryForObject(GET_EMPLOYEE_QUERY, (rs, rowNum)->{
				return new Employee(rs.getInt("e_id"),rs.getString("first_name"),rs.getString("last_name"),rs.getString("date_of_birth"),rs.getInt("dept_id"));
			},e_id);
	}

	@Override
	public void addEmployees(String url) {
		getList(url).forEach(e->
		jdbcTemplate.update(INSERT_QUERY, e.getE_id(),e.getFirst_name(),e.getLast_name(),e.getDate_of_birth(),e.getDept_id()));
	}
		
}