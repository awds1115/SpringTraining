package com.human.team;

import java.net.http.HttpRequest;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.human.team.Member;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	private SqlSession sqlSession;
	private ServletRequest session;
	
	@RequestMapping("/home") public String home() {return "home";}
	@RequestMapping("/login") public String login() {return "login";}
	@RequestMapping("/register") public String register() {return "register";}
	@RequestMapping("/admincheck") public String admincheck() {return "admincheck";}
	@RequestMapping("/bookcheck") public String bookcheck() {return "bookcheck";}
	
	   //---------------회원가입
	   @ResponseBody
	   @RequestMapping(value="/idCheck",method=RequestMethod.POST,produces="application/json;charset=utf-8") 
	   public String idCheck(HttpServletRequest hsr) {
	         iTeam team=sqlSession.getMapper(iTeam.class); 
	         ArrayList<Member> mem=team.getMember();
	         JSONArray ja=new JSONArray();
	            for(int i=0; i<mem.size(); i++) {
	               JSONObject jo=new JSONObject();
	               jo.put("userid",mem.get(i).getUserid());
	               ja.add(jo);
	            }
	            return ja.toString();
	   }
	   @RequestMapping(value="/signCheck",method=RequestMethod.POST,produces="application/jason;charset=utf-8")
	   public String signCheck(HttpServletRequest hsr) {
	      try {
	         String name=hsr.getParameter("name");
	            String gender=hsr.getParameter("gender");
	         String userid=hsr.getParameter("userid");
	         String passcode=hsr.getParameter("passcode");
	         String mobile=hsr.getParameter("mobile");
	         String type=hsr.getParameter("type");
	            
	         iTeam team=sqlSession.getMapper(iTeam.class); 
	         team.signCheck(name,gender,userid,passcode,mobile,type);

	         return "login";
	      } catch(Exception e) {
	         System.out.println(e.getMessage());
	         return "register";
	      }
	   }
//---------------------로그인
	   @ResponseBody
	   @RequestMapping(value="/type_check",method= RequestMethod.POST,
			   			produces="application/json; charset=utf-8")
	   public String type_check(HttpServletRequest hsr) {
		   iTeam member=sqlSession.getMapper(iTeam.class);
		   ArrayList<Member> ml=member.getTypeList();
		   
		   JSONArray ja=new JSONArray();
			for(int i=0; i<ml.size(); i++) {
				JSONObject jo = new JSONObject();
				jo.put("id",ml.get(i).getUserid());
				jo.put("type",ml.get(i).getType());
				ja.add(jo);
			}
		   return ja.toString();
	   }
	   			
	   @ResponseBody
		@RequestMapping(value="/login_check", method= RequestMethod.POST,
				produces="application/json; charset=utf-8")
		public String login_check(HttpServletRequest hsr) {
		   
			iTeam member=sqlSession.getMapper(iTeam.class);
			ArrayList<Member> ml=member.getMemberList();
			System.out.println(ml.size());
			JSONArray ja=new JSONArray();
			for(int i=0; i<ml.size(); i++) {
				JSONObject jo = new JSONObject();
				jo.put("id",ml.get(i).getUserid());
				jo.put("pw",ml.get(i).getPasscode());
				ja.add(jo);
			}
			return ja.toString();
		}
		@ResponseBody
		@RequestMapping(value="/login_update", method= RequestMethod.POST,
						produces="application/json; charset=utf-8")
		public String login_update(HttpServletRequest hsr, HttpServletRequest request) {
			String retval="";
			try {
			String userid=hsr.getParameter("id");
			iTeam member=sqlSession.getMapper(iTeam.class);
			member.UpdateMember(userid);
				retval="ok";
				
				HttpSession session = request.getSession(true);
				session.setAttribute("userid",hsr.getParameter("id"));
				session.setAttribute("type",hsr.getParameter("type"));
			} catch(Exception e) {
				retval="fail";
			}
			
			return retval;
		}
		
//-----------------------로그아웃
		@RequestMapping(value="/logout_update", method= RequestMethod.GET)
		public String logout_update(HttpServletRequest hsr, HttpServletRequest request) {
			HttpSession session = request.getSession(true);
			String userid = (String) session.getAttribute("userid");
			iTeam member=sqlSession.getMapper(iTeam.class);
			member.logoutMember(userid);
			session.invalidate();
			return "home";
		}
}
