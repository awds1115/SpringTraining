package com.human.team;

import java.util.ArrayList;
import java.util.List;

public interface iTeam {

	ArrayList<Member> getMember();
	void signCheck(String name, String gender, String userid, String passcode, String mobile, String type);
	ArrayList<Member> getMemberList();
	void UpdateMember(String userid);
	ArrayList<Member> getTypeList();
	void logoutMember(String userid);
	ArrayList<Notice> getPaging();
	void plusViewCnt(int id);
	Notice getView(int id);
	void writeNotice(String title, String name, String content);
	void deleteNotice(int notice_id);
	void updateNotice(int id, String title, String name, String content);
	List<Notice> listpage(Criteria cri);
	int listCount();
	
}
