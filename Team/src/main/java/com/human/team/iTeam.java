package com.human.team;

import java.util.ArrayList;

public interface iTeam {

	ArrayList<Member> getMember();
	void signCheck(String name, String gender, String userid, String passcode, String mobile, String type);
	ArrayList<Member> getMemberList();
	void UpdateMember(String userid);
	ArrayList<Member> getTypeList();
	void logoutMember(String userid);
	
}
