package com.KeepFitMS.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.KeepFitMS.entity.Dept;
import com.KeepFitMS.entity.Emp;
import com.KeepFitMS.entity.Job;
import com.KeepFitMS.exception.PersonnelServiceException;
import com.KeepFitMS.service.DeptService;
import com.KeepFitMS.service.EmpService;
import com.KeepFitMS.service.JobService;

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
	@Autowired
	private JobService jobService;
	
	// -----------------部门

	/**
	 * 添加部门
	 * 
	 * @param dept
	 * @return
	 */
	@PostMapping("/addDept.do")
	@ResponseBody
	public HashMap<String, Object> addDept(Dept dept) {
		HashMap<String, Object> result = new HashMap<>();
		try {
			if (deptService.addDept(dept)) {
				result.put("code", "ok");
				result.put("newDept", dept);
			} else {
				result.put("msg", "未知错误");
			}
		} catch (PersonnelServiceException e) {
			result.put("code", e.getErrorCode());
			result.put("msg", e.getMessage());
		}
		return result;
	}

	/**
	 * 修改部门
	 * 
	 * @param dept
	 * @return
	 */
	@PostMapping("/modifyDept.do")
	@ResponseBody
	public HashMap<String, Object> modifyDept(Dept dept) {
		HashMap<String, Object> result = new HashMap<>();
		System.out.println(dept);
		try {
			deptService.modifyDept(dept);
			result.put("code", "ok");
		} catch (PersonnelServiceException e) {
			result.put("code", e.getErrorCode());
			result.put("msg", e.getMessage());
		}
		return result;
	}

	/**
	 * 获取所有部门信息
	 * 
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

	/**
	 * 删除部门
	 * 
	 * @param dept_id
	 * @return
	 */
	@PostMapping("/deleteDept.do")
	@ResponseBody
	public HashMap<String, Object> deleteDept(int dept_id) {
		HashMap<String, Object> result = new HashMap<>();
		try {
			if (deptService.deleteDept(dept_id)) {
				result.put("code", "ok");
			} else {
				result.put("code", "false");
				result.put("msg", "未知错误");
			}
		} catch (PersonnelServiceException e) {
			result.put("code", e.getErrorCode());
			result.put("msg", e.getMessage());
		}
		return result;
	}

	// ------------员工

	/**
	 * 添加员工
	 * 
	 * @param emp
	 * @return
	 */
	@PostMapping("/addEmp.do")
	@ResponseBody
	public HashMap<String, Object> addEmp(@RequestBody Emp emp) {
		HashMap<String, Object> result = new HashMap<>();
		try {
			empService.addEmp(emp);
			result.put("code", "ok");
			result.put("newEmp", emp);
		} catch (PersonnelServiceException e) {
			result.put("code", e.getErrorCode());
			result.put("msg", e.getMessage());
		}
		return result;
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
	 * 修改员工
	 * 
	 * @param emp
	 * @return
	 */
	@PostMapping("/modifyEmp.do")
	@ResponseBody
	public HashMap<String, Object> modifyEmp(@RequestBody Emp emp) {
		HashMap<String, Object> result = new HashMap<>();
		empService.modifyEmp(emp);
		result.put("code", "ok");
		return result;
	}

	/**
	 * 删除员工
	 * 
	 * @param emp_id
	 * @return
	 */
	@PostMapping("/deleteEmp.do")
	@ResponseBody
	public HashMap<String, Object> deleteEmp(int emp_id) {
		HashMap<String, Object> result = new HashMap<>();
		if (empService.deleteEmp(emp_id)) {
			result.put("code", "ok");
		} else {
			result.put("code", "false");
			result.put("msg", "未知错误");
		}
		return result;
	}
	
	//-------------职位
	/**
	 * 获取所有员工信息
	 * 
	 * @return
	 */
	@GetMapping("/getAllJob.do")
	@ResponseBody
//	@Cacheable(cacheNames = "getAllJob", key = "", unless = "#result == null")
	public HashMap<String, Object> getAllJob() {
		List<Job> jobList = jobService.selectAllJob();
		HashMap<String, Object> result = new HashMap<>();
		result.put("code", "ok");
		result.put("allJobData", jobList);
		return result;
	}

}
