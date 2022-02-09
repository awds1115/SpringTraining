package com.human.exercise;

public class iType {
	private int typecode;
	private String name;
	public iType() {
	}
	public iType(int typecode, String name) {
		this.typecode = typecode;
		this.name = name;
	}
	public int getTypecode() {
		return typecode;
	}
	public void setTypecode(int typecode) {
		this.typecode = typecode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
