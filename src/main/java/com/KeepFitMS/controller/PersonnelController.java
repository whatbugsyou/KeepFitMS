package com.KeepFitMS.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.KeepFitMS.entity.Emp;
import com.KeepFitMS.service.EmpService;

@Controller
public class PersonnelController {
	@Autowired
	private EmpService empService;
	
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
	@GetMapping("/getAllEmp.do")
	@ResponseBody
	public  HashMap<String, Object> getAllEmp() {
		List<Emp> empList = empService.selectAllEmp();
		HashMap<String, Object> result = new HashMap<>();
		result.put("code", "ok");
		result.put("allEmpData",empList);
		return result;
	}
	
}
