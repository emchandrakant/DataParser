package com.ms.dao.impl;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.ms.dao.DepartmentDAO;
import com.ms.entity.Department;
import com.ms.util.Query;

@Service
public class DepartmentDAOImpl implements DepartmentDAO{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private Query sqlquery= new Query();

	String line ="";
	
	private final String INSERT_QUERY = sqlquery.readQueryAsString("department_insert_query");
	private final String GET_DEPARTMENTS_QUERY = sqlquery.readQueryAsString("get_departments_query");
	private final String GET_DEPARTMENT_QUERY = sqlquery.readQueryAsString("get_department_query");
	
	@Override
	public List<Department> getList(String url) {
		try(BufferedReader br = new BufferedReader(new FileReader(url))){
			line = br.readLine();
			List<Department> lst=new ArrayList<>();
			
			while((line=br.readLine()) != null) {
				String [] data = line.split(",");
								
				lst.add(new Department(Integer.parseInt(data[8]),Integer.parseInt(data[0]),data[9]));
			}
			return lst;
		} catch (IOException e1) {
			e1.getMessage();
			e1.printStackTrace();
			return null;
		}
	}
	
	@Override
	public List<Department> getDataBaseData() {
		getList("E:\\work-personal\\employees.csv").forEach(e->
		jdbcTemplate.update(INSERT_QUERY, e.getDept_id(),e.getE_id(),e.getDept_name()));
		
		return jdbcTemplate.query(GET_DEPARTMENTS_QUERY, new BeanPropertyRowMapper<Department>(Department.class));
	}

	@Override
	public List<Department> getDepartment(int dept_id) throws DataAccessException{
		return jdbcTemplate.query(GET_DEPARTMENT_QUERY, (rs, rowNum)->{
			return new Department(rs.getInt("dept_id"),rs.getInt("e_id"),rs.getString("dept_name"));
		},dept_id);
	}

	@Override
	public void addDepartments(String url) {
		getList(url).forEach(e->
		jdbcTemplate.update(INSERT_QUERY, e.getDept_id(),e.getE_id(),e.getDept_name()));
	}

}
