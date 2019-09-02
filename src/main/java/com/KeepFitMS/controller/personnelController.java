package com.KeepFitMS.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class personnelController {
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
