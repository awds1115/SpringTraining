package com.human.exercise;

import java.util.ArrayList;

public interface iEmp {
	ArrayList<Employee> getEmpList(int keyword);
	ArrayList<iCountry> getCntList();
	ArrayList<Employee> getDpList();
	void addMenu(String name,int price);
	void addRoom(String name, int type, int howmany, int howmuch);
	void insertType(int code, String name);
	ArrayList<Employee> getRmList();
	ArrayList<iMenu> getMenuList();
	void deleteMenu(int code);
	void deleteRoom(int roomcode);
	ArrayList<iType> getRoomType();
	void updateMenu(int code, String name, int price);
	void updateRoom(int code2, String name, int type, int howmany, int howmuch);
	}
