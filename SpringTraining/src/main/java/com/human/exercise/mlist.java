package com.human.exercise;

public class mlist {
	private int employee_id;
	private String emp_name;
	public mlist() {
	}
	public mlist(int employee_id, String emp_name) {
		this.employee_id = employee_id;
		this.emp_name = emp_name;
	}
	public int getemployee_id() {
		return employee_id;
	}
	public void setemployee_id(int employee_id) {
		this.employee_id = employee_id;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	
}
