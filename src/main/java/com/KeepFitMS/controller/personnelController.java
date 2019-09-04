package com.KeepFitMS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.KeepFitMS.service.EmpService;

@Controller
public class PersonnelController {
	@Autowired
	private EmpService empService;
	
	@GetMapping("/personnel.html")
	public String testP() {
		return "personnel.html";
	}
	@GetMapping("/addDept.do")
	@ResponseBody
	public  String addDept() {
		return null;
	}
	
	@GetMapping("/addEmp.do")
	@ResponseBody
	public  String addEmp() {
		return null;
	}
}
