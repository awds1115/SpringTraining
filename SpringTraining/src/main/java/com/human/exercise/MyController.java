package com.human.exercise;

import java.io.IOException;
import java.rmi.AccessException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class MyController {

	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping("/manager")
	public String doManager(Model model) {
		iJob job=sqlSession.getMapper(iJob.class);
		ArrayList<mlist> jl=job.manager();
		model.addAttribute("jobs",jl);
		return "manager";
	}
	
	@ResponseBody
	@RequestMapping(value="/ManagerList",
					produces="application/json;charset=utf-8")
	public String doManagerList(HttpServletRequest hsr) {
		int deptid = Integer.parseInt(hsr.getParameter("mcode"));
		
		iJob job=sqlSession.getMapper(iJob.class);
		ArrayList<ManagerList> ml=job.ManagerList(deptid);  // jobid를 넣고 getList 호출
		JSONArray ja = new JSONArray();
		for(int i=0; i<ml.size(); i++) {
			JSONObject jo = new JSONObject();
			jo.put("emp_id",ml.get(i).getEmployee_id());
			jo.put("emp_name",ml.get(i).getEname());

			ja.add(jo);
		}
		return ja.toString();
	}
	
	
	
	
	
	@RequestMapping("/dept")
	public String doDept(Model model) {
		iJob job=sqlSession.getMapper(iJob.class);
		ArrayList<dept> jl=job.dept1();
		model.addAttribute("jobs",jl);
		return "departments";
	}
	@ResponseBody
	@RequestMapping(value="/deptlist",
					produces="application/json;charset=utf-8")
	public String doDeptlist(HttpServletRequest hsr) {
		int deptid = Integer.parseInt(hsr.getParameter("deptcode"));
		
		iJob job=sqlSession.getMapper(iJob.class);
		ArrayList<deptInfo> ml=job.getdeptList(deptid);  // jobid를 넣고 getList 호출
		JSONArray ja = new JSONArray();
		for(int i=0; i<ml.size(); i++) {
			JSONObject jo = new JSONObject();
			jo.put("emp_id",ml.get(i).getEmployee_id());
			jo.put("emp_name",ml.get(i).getEmp_name());
			jo.put("mobile",ml.get(i).getPhone_number());
			jo.put("salary",ml.get(i).getSalary());
			ja.add(jo);
		}
		return ja.toString();
	}
	@RequestMapping(value="/emp",method=RequestMethod.GET)
	public String doEmp() {
		return "emplist";
	}
	@RequestMapping(value="/job", method=RequestMethod.GET,produces="application/json;charset=utf-8" )
	public String job(Model model) {
		iJob job=sqlSession.getMapper(iJob.class);
		ArrayList<Job> jl=job.jobList();
		model.addAttribute("jobs",jl);
		return "job";
	}
	@ResponseBody
	@RequestMapping(value="/position",
					produces="application/json;charset=utf-8")
	public String doPosition(HttpServletRequest hsr) {
		String jobid = hsr.getParameter("jobcode");
		
		iJob job=sqlSession.getMapper(iJob.class);
		ArrayList<EmpInfo> ml=job.getList(jobid);  // jobid를 넣고 getList 호출
		JSONArray ja = new JSONArray();
		for(int i=0; i<ml.size(); i++) {
			JSONObject jo = new JSONObject();
			jo.put("emp_id",ml.get(i).getEmployee_id());
			jo.put("emp_name",ml.get(i).getEmp_name());
			jo.put("mobile",ml.get(i).getPhone_number());
			jo.put("dname",ml.get(i).getDepartment_name());
			ja.add(jo);
		}
		return ja.toString();
	}
	
	@ResponseBody
	@RequestMapping(value="/EmpView",method=RequestMethod.POST,
            produces="application/json;charset=utf-8")
	public String doEmpList(HttpServletRequest hsr) {
		int keyword=Integer.parseInt(hsr.getParameter("kw"));

		
		iEmp emp = sqlSession.getMapper(iEmp.class);// 인터페이스 이름 작성(iEmp)
		ArrayList<Employee> alEmp = emp.getEmpList(keyword);
//		System.out.println(alEmp.size());
		
		JSONArray ja = new JSONArray();
		for(int i=0; i<alEmp.size(); i++) {
			JSONObject jo = new JSONObject();
			jo.put("emp_id",alEmp.get(i).getEmployee_id());
			jo.put("emp_name",alEmp.get(i).getEmp_name());
			jo.put("mobile",alEmp.get(i).getPhone_number());
			jo.put("manager_id",alEmp.get(i).getManager_id());
			jo.put("hire_date",alEmp.get(i).getHire_date());
			ja.add(jo);
		}  //직원의사번/이름/전화번호/매니저아이디/입사일자
		return ja.toString();
	}
	@RequestMapping("/deleteRoom")
	public String doDeleteRoom(HttpServletRequest hsr) {
		int code=Integer.parseInt(hsr.getParameter("code"));
		iEmp Room = sqlSession.getMapper(iEmp.class);
		Room.deleteRoom(code);
		return "redirect:/Room";
	}
	
	@ResponseBody
	@RequestMapping(value="/MenuList",method=RequestMethod.GET,
            produces="application/json;charset=utf-8")
	public String doMenuList(Model model) {
		iEmp ml = sqlSession.getMapper(iEmp.class);// 인터페이스 이름 작성(iEmp)
		ArrayList<iMenu> alMenuList = ml.getMenuList();
		//메뉴명, 가격 이 들어가있음
		
		//JSONArray, JSONObject
		JSONArray ja = new JSONArray();
		for(int i=0; i<alMenuList.size(); i++) { //ArrayList -> JSON 으로 바꾸는 작업 해야함
			JSONObject jo = new JSONObject();
			jo.put("name",alMenuList.get(i).getName());
			jo.put("code",alMenuList.get(i).getCode());   // getter,setter 한 이름을 가져다 씀
			jo.put("price",alMenuList.get(i).getPrice());
			ja.add(jo);
		}
//		model.addAttribute("alMl", alMenuList);
		return ja.toString();   //  return 시 addMenu 의 ajax -> function(txt) 의 txt라는 변수에 들어간다.
	}
	@RequestMapping("/Menu")
	public String Menu1() {
		return "addMenu";
	}
	@RequestMapping("/deleteMenu")
	public String doDeleteMenu(HttpServletRequest hsr) {
		int code=Integer.parseInt(hsr.getParameter("code"));
		iEmp Menu = sqlSession.getMapper(iEmp.class);
		Menu.deleteMenu(code);
		return "redirect:/Menu";
	}
	@RequestMapping("/Room")
	public String Room1() {
		return "addRoom";
	}
	@ResponseBody
	@RequestMapping(value="/RoomList", 
					produces="application/json;charset=utf-8")
	public String doRmList(Model model) {
		iEmp rml = sqlSession.getMapper(iEmp.class);// 인터페이스 이름 작성(iEmp)
		ArrayList<Employee> alrml = rml.getRmList();
		
		JSONArray ja = new JSONArray();
		for(int i=0; i<alrml.size(); i++) {
			JSONObject jo = new JSONObject();
			jo.put("roomcode",alrml.get(i).getRoomcode());
			jo.put("roomname",alrml.get(i).getName());
			jo.put("name",alrml.get(i).getTypename());
			jo.put("howmany",alrml.get(i).getHowmany());
			jo.put("howmuch",alrml.get(i).getHowmuch());
			ja.add(jo);
		}
//		model.addAttribute("alRml", alrml);
		
//		ArrayList<iType> typeList=rml.getRoomType();
//		System.out.println("type size ["+typeList.size()+"]");
//		model.addAttribute("types",typeList);
		return ja.toString();
	}
	@ResponseBody
	@RequestMapping(value="/TypeList",
			produces="application/json;charset=utf-8")
	public String doTpList() {
		iEmp rml = sqlSession.getMapper(iEmp.class);
		ArrayList<iType> tpl=rml.getRoomType();
		
		JSONArray ja = new JSONArray();
		for(int i=0; i<tpl.size(); i++) {
			JSONObject jo = new JSONObject();
			jo.put("typecode",tpl.get(i).getTypecode());
			jo.put("typename",tpl.get(i).getName());
			ja.add(jo);
		}
		return ja.toString();
	}

	@RequestMapping("/addtype")
	public String typeadd(HttpServletRequest hsr) {
		return "addtype";
	}

	@RequestMapping("/insertType")
	public String insertType(HttpServletRequest hsr) {
		int code = Integer.parseInt(hsr.getParameter("type"));
		String name = hsr.getParameter("typename");

		iEmp room = sqlSession.getMapper(iEmp.class);
		room.insertType(code, name);
		return "addtype";
	}

	@RequestMapping("/addRoom")
	public String DoaddRoom(HttpServletRequest hsr) {
		String code = hsr.getParameter("code");
		String name = hsr.getParameter("roomname");
		int type = Integer.parseInt(hsr.getParameter("roomtype"));
		int howmany = Integer.parseInt(hsr.getParameter("howmany"));
		int howmuch = Integer.parseInt(hsr.getParameter("howmuch"));
		
		iEmp room = sqlSession.getMapper(iEmp.class);
		if(code.equals("")) {
			room.addRoom(name, type, howmany, howmuch);
		} else {
			int code2=Integer.parseInt(code);
			room.updateRoom(code2,name,type,howmany,howmuch);
		}
		
		
		return "redirect:/Room";
	}

	@RequestMapping("/addMenu")   
	   public void doAddMenu(HttpServletRequest hsr) {
	      String strCode=hsr.getParameter("code");
	      String name=hsr.getParameter("name");
	      int price=Integer.parseInt(hsr.getParameter("price"));
	      
	      iEmp emp=sqlSession.getMapper(iEmp.class);      
	      if(strCode.equals("")) {   //insert
	         emp.addMenu(name,price);
	      }       
	      else {               //update
	         int code=Integer.parseInt(strCode);
	         emp.updateMenu(code, name, price);
	      }
	      
	   }

	@RequestMapping("/department")
	public String doDpList(Model model) {
		iEmp dpl = sqlSession.getMapper(iEmp.class);// 인터페이스 이름 작성(iEmp)
		ArrayList<Employee> aldpl = dpl.getDpList();
		model.addAttribute("aldpl", aldpl);
		return "department";
	}

	@RequestMapping("/country")
	public String doCntList(Model model) {
		iEmp cnr = sqlSession.getMapper(iEmp.class);// 인터페이스 이름 작성(iEmp)
		ArrayList<iCountry> alcnr = cnr.getCntList();
		model.addAttribute("alcnr", alcnr);
		return "country";
	}

//	@RequestMapping("/emp")
//	public String doEmpList(Model model) {
//		iEmp emp = sqlSession.getMapper(iEmp.class);// 인터페이스 이름 작성(iEmp)
//		ArrayList<Employee> alEmp = emp.getEmpList();
//		model.addAttribute("alEmp", alEmp);
//		return "emp";
//	}

	@RequestMapping(value = "/look", method = RequestMethod.GET)
	public String look(HttpServletRequest hsr, Model model) {
		model.addAttribute("mobile", "010-5583-4032");
		model.addAttribute("address", "준빌리지 401호");
		return "look";
	}

	@RequestMapping("/view")
	public String view(HttpServletRequest hsr) {
		String nick = hsr.getParameter("nickname"); // client에서 보내진 data를 수집
		System.out.println("[" + nick + "]");
		return "redirect:/look";
	}

	@RequestMapping("/input")
	public String input(HttpServletRequest hsr, Model model) {
		return "input";
	}

	@RequestMapping("/plus")
	public String plus(HttpServletRequest hsr, Model model) {
		return "plus";
	}

	@RequestMapping("/multi")
	public String multi(HttpServletRequest hsr, Model model) {
		return "multi";
	}

	@RequestMapping("/minus")
	public String minus(HttpServletRequest hsr, Model model) {
		return "minus";
	}

	@RequestMapping("/divide")
	public String divide(HttpServletRequest hsr, Model model) {
		return "divide";
	}

	@RequestMapping("/calc")
	public String calc(HttpServletRequest hsr, Model model) {
		String jspName = "";
		try {
			int x1 = Integer.parseInt(hsr.getParameter("x1"));
			int x2 = Integer.parseInt(hsr.getParameter("x2"));
			String op = hsr.getParameter("op");
			if (op.equals("+")) {
				model.addAttribute("plus", (x1 + x2));
				jspName = "plus";
			} else if (op.equals("*")) {
				model.addAttribute("multi", (x1 * x2));
				jspName = "multi";
			} else if (op.equals("-")) {
				model.addAttribute("minus", (x1 - x2));
				jspName = "minus";
			} else if (op.equals("/")) {
				model.addAttribute("divide", (x1 / x2));
				jspName = "divide";
			} else {
				model.addAttribute("op2", "연산자 불명");
				jspName = "error";
			}
		} catch (Exception e) {
			model.addAttribute("num", "값 오류");
			jspName = "error";
		}
		return jspName;

//		try {
//			int x1 = Integer.parseInt(hsr.getParameter("x1"));
//			int x2 = Integer.parseInt(hsr.getParameter("x2"));
//			int result = x1 * x2;
//
//			if (result > 20) {
//				model.addAttribute("result", result);
//				return "calc";
//			} else {
//				model.addAttribute("result", result);
//				return "input";
//			}
//
//		} catch (Exception e) {
//			model.addAttribute("result", "error");
//			return "input";
//		}

//		if(hsr.getParameter("x1")=="" || hsr.getParameter("x1")==null ||
//		   hsr.getParameter("x2")=="" || hsr.getParameter("x2")==null ||
//		   hsr.getParameter("x1").equals("")) {
//			return "input";
//		} 

	}

}
