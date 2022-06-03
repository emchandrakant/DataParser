package com.ms.entity;

public class Employee {
	
	private int e_id;
	private String first_name;
	private String last_name;
	private String date_of_birth;
	private int dept_id;

	public Employee() {
		super();
	}

	public Employee(int e_id, String first_name, String last_name, String date_of_birth, int dept_id) {
		this.e_id = e_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.date_of_birth = date_of_birth;
		this.dept_id = dept_id;
	}

	public int getE_id() {
		return e_id;
	}

	public void setE_id(int e_id) {
		this.e_id = e_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(String date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	public int getDept_id() {
		return dept_id;
	}

	public void setDept_id(int dept_id) {
		this.dept_id = dept_id;
	}

	@Override
	public String toString() {
		return "Employee [e_id=" + e_id + ", first_name=" + first_name + ", last_name=" + last_name + ", date_of_birth=" + date_of_birth
				+ ", dept_id=" + dept_id + "]";
	}
}
