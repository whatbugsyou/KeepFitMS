package com.KeepFitMS.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.KeepFitMS.entity.Dept;
import com.KeepFitMS.entity.Emp;
import com.KeepFitMS.exception.PersonnelServiceException;
import com.KeepFitMS.service.DeptService;
import com.KeepFitMS.service.EmpService;

/**
 * 人事管理
 * 
 * @author Hzl
 *
 */
@Controller
@EnableCaching
public class PersonnelController {
	@Autowired
	private EmpService empService;
	@Autowired
	private DeptService deptService;
	/**
	 * 添加部门
	 * @param dept
	 * @return
	 */
	@PostMapping("/addDept.do")
	@ResponseBody
	public HashMap<String, Object> addDept(Dept dept) {
		HashMap<String, Object> result = new HashMap<>();
		try {
			deptService.addDept(dept);
			result.put("code", "ok");
			result.put("newDept", dept);
		} catch (PersonnelServiceException e) {
			result.put("code", e.getErrorCode());
			result.put("msg", e.getMessage());
		}
		return result;
	}
	/**
	 * 添加员工
	 * @param emp
	 * @return
	 */
	@GetMapping("/addEmp.do")
	@ResponseBody
	public String addEmp(Emp emp) {
		return null;
	}

	/**
	 * 获取所有员工信息
	 * 
	 * @return
	 */
	@GetMapping("/getAllEmp.do")
	@ResponseBody
//	@Cacheable(cacheNames = "getAllEmp", key = "", unless = "#result == null")
	public HashMap<String, Object> getAllEmp() {
		List<Emp> empList = empService.selectAllEmp();
		HashMap<String, Object> result = new HashMap<>();
		result.put("code", "ok");
		result.put("allEmpData", empList);
		return result;
	}
	/**
	 * 获取所有部门信息
	 * @return
	 */
	@GetMapping("/getAllDept.do")
	@ResponseBody
//	@Cacheable(cacheNames = "getAllDept", key = "", unless = "#result == null")
	public HashMap<String, Object> getAllDept() {
		List<Dept> deptList = deptService.selectAllDept();
		HashMap<String, Object> result = new HashMap<>();
		result.put("code", "ok");
		result.put("allDeptData", deptList);
		return result;
	}

}
