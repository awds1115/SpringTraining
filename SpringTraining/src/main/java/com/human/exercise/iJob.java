package com.human.exercise;

import java.util.ArrayList;

public interface iJob {
	ArrayList<Job> jobList();
	ArrayList<EmpInfo> getList(String job_id);
	ArrayList<deptInfo> getdeptList(int deptid);
	ArrayList<dept> dept1();
	ArrayList<mlist> manager();
	ArrayList<ManagerList> ManagerList(int deptid);
}
