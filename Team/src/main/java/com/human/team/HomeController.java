package com.human.team;

import java.net.http.HttpRequest;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import org.springframework.web.bind.annotation.ModelAttribute;
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
	
	@RequestMapping("/login") public String login() {return "login";}
	@RequestMapping("/register") public String register() {return "register";}
	@RequestMapping("/admincheck") public String admincheck() {return "admincheck";}
	@RequestMapping("/bookcheck") public String bookcheck() {return "bookcheck";}
	@RequestMapping("/compose") public String compose() {return "write";}
	//--------------------------------home
	@RequestMapping("/home") 
	public String home(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String type1="";
		String userid="";
		if(session.getAttribute("userid")==null) {
			userid="null";
		} else {
			userid=(String) session.getAttribute("userid");
		}
		if(session.getAttribute("type")==null){
			type1="2";
		} else {
			type1=(String) session.getAttribute("type");
		}
		int type=Integer.parseInt(type1);
		model.addAttribute("type",type);
		model.addAttribute("userid",userid);
		return "home";
		}
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
			return "redirect:/home";
		}
		//-------------------공지사항
		@RequestMapping("/notice")
		public String notice(Model model, HttpServletRequest request,
							@ModelAttribute("cri") Criteria cri) {
			iTeam notice=sqlSession.getMapper(iTeam.class);
			ArrayList<Notice> alNotice=notice.getPaging();
			HttpSession session = request.getSession(true);
			List<Notice> list = notice.listpage(cri);
			String type1="";
			String userid="";
			if(session.getAttribute("userid")==null) {
				userid="null";
			} else {
				userid=(String) session.getAttribute("userid");
			}
			if(session.getAttribute("type")==null){
				type1="2";
			} else {
				type1=(String) session.getAttribute("type");
			}
			int type=Integer.parseInt(type1);
			model.addAttribute("type",type);
			model.addAttribute("userid",userid);
			model.addAttribute("alNotice",alNotice);
			model.addAttribute("list",list);
			
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);
			pageMaker.setTotalCount(notice.listCount());
			System.out.println(notice.listCount());
			model.addAttribute("pageMaker",pageMaker);
			return "notice";
		}
		@RequestMapping("/view")
		public String view(HttpServletRequest hsr, Model model) {
			int id=Integer.parseInt(hsr.getParameter("id"));
			iTeam notice=sqlSession.getMapper(iTeam.class);
			notice.plusViewCnt(id); 
			Notice view=notice.getView(id); 
			HttpSession session = hsr.getSession(true);
			String type1="";
			String userid="";
			if(session.getAttribute("userid")==null) {
				userid="null";
			} else {
				userid=(String) session.getAttribute("userid");
			}
			if(session.getAttribute("type")==null){
				type1="2";
			} else {
				type1=(String) session.getAttribute("type");
			}
			int type=Integer.parseInt(type1);
			model.addAttribute("type",type);
			model.addAttribute("userid",userid);
			model.addAttribute("notice",view);
			return "view";
		}
		@RequestMapping(value="/write", method=RequestMethod.GET)
		public String write(HttpServletRequest hsr) {
			String title=hsr.getParameter("title");
			String name=hsr.getParameter("name");
			String content=hsr.getParameter("content");
			
			iTeam notice=sqlSession.getMapper(iTeam.class);
			notice.writeNotice(title,name,content);
			return "redirect:/notice";
		}
		@RequestMapping("/update")
		public String update(HttpServletRequest hsr, Model model) {
			int id=Integer.parseInt(hsr.getParameter("id"));
			
			iTeam team=sqlSession.getMapper(iTeam.class);
			Notice notice=team.getView(id);
			model.addAttribute("notice",notice);
			return "update";
		}
		@RequestMapping("/delete")
		   public String delete(HttpServletRequest hsr) {
		      int notice_id=Integer.parseInt(hsr.getParameter("id"));
		      
		      iTeam notice=sqlSession.getMapper(iTeam.class);
		      notice.deleteNotice(notice_id);
		      return "redirect:/notice";
		   }
		@RequestMapping(value="/modify", method=RequestMethod.POST)
		public String Modify(HttpServletRequest hsr) {
			int id=Integer.parseInt(hsr.getParameter("id"));
			String title=hsr.getParameter("title");
			String name=hsr.getParameter("name");
			String content=hsr.getParameter("content");
			
			iTeam notice=sqlSession.getMapper(iTeam.class);
		      notice.updateNotice(id,title,name,content);
			return "redirect:/notice";
		}
		
}
