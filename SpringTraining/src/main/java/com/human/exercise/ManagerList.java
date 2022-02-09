package com.human.exercise;

public class ManagerList {
	private int employee_id;
	private String ename;
	public ManagerList() {
	}
	public ManagerList(int employee_id, String ename) {
		this.employee_id = employee_id;
		this.ename = ename;
	}
	public int getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	
}
