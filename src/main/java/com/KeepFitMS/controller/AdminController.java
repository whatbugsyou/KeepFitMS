package com.KeepFitMS.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.KeepFitMS.entity.Admin;
import com.KeepFitMS.service.AdminService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class AdminController {

	@Resource
	private AdminService adminService;
	
	@RequestMapping(value= "/login.do")
	@ResponseBody
	public String login(Admin admin,HttpServletRequest request) throws JsonProcessingException{
		
		System.out.println("这是控制层");
		System.out.println(admin.getUsername());
		System.out.println(admin.getPassword());
		ObjectMapper mapper = new ObjectMapper(); 
		Map<String,String> map = new HashMap<String, String>(); 
		String json = null;
		Admin reAdmin = adminService.loginUsername(admin);
		if(reAdmin == null) { 
			map.put("erroes","101"); 
			map.put("errmsg", "用户名不存在！");
		  }else{ 
			  Admin reAdminPassword = adminService.loginPassword(admin);
			  if(reAdminPassword == null) { 
				  map.put("erroes", "102"); 
				  map.put("errmsg","密码不正确！"); 
				  }else { 
					  HttpSession session = request.getSession();
					  session.setAttribute("currentAdmin", reAdminPassword);
					  map.put("erroes", "200"); 
					  } 
			  }
		 json = mapper.writeValueAsString(map);
		 System.out.println(json);
		 return json;
	}
	
	@RequestMapping("/getSessionUser.do")
	@ResponseBody
	public Admin getSessionUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Admin admin = (Admin) session.getAttribute("currentAdmin");
		
		return admin;
	}
	
}
