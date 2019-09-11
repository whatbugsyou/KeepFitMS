package com.KeepFitMS.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.KeepFitMS.entity.Bcourse;
import com.KeepFitMS.entity.PageBean;
import com.KeepFitMS.service.BCourseService;
import com.KeepFitMS.util.StringUtil;

@Controller
public class BCourseController {

	@Resource
	private BCourseService bCourseService;
	
	//初始化绑定浏览器时间格式
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); // true:允许输入空值，false:不能为空值
	}
	
	@RequestMapping("/getBCourseList.do")
	@ResponseBody
	public Map<String,Object> findBCourse(@RequestParam(value="page",required = false) String page,
			@RequestParam(value="rows",required = false) String rows,Bcourse bcourse){
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		System.out.println(rows);
		System.out.println(page);
		System.out.println(bcourse);
		//	数据分装到map中
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("bc_id", bcourse.getBc_id());
		map.put("bc_mid", bcourse.getBc_mid());
		map.put("bc_coachid", bcourse.getBc_coachid());
		map.put("bc_courseid", bcourse.getBc_courseid());
		map.put("bc_time", bcourse.getBc_time());
		
		map.put("emp_name", StringUtil.formatLike(bcourse.getEmp_name()));//教练名称
		map.put("mname", StringUtil.formatLike(bcourse.getMname()));//会员名称
		map.put("c_name", StringUtil.formatLike(bcourse.getC_name()));//课程名字
		
		map.put("starttime", bcourse.getStarttime());
		map.put("endtime",bcourse.getEndtime());
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		
		List<Bcourse> list = bCourseService.findAllBC(map);
		
		//查询所有的记录
		long totalresult = bCourseService.findTotalBcourse(map);
		
		System.out.println(totalresult);
		Map<String,Object> rmap = new HashMap<String, Object>();
		
		rmap.put("bcourses", list);
		rmap.put("total", totalresult);
		return rmap;
	} 
	
	/**
	 * 获得所有的教练信息 ,课程信息
	 * @return
	 */
	@RequestMapping("/bCourseManage.do")
	@ResponseBody
	public Map<String,Object> bCourseManage(){
		Map<String,Object> map = bCourseService.getInfoCE();
		return map;
	}
	
	@RequestMapping("/addBCourse.do")
	@ResponseBody
	public Map<String,Object> addBcourse(Bcourse bcourse){
		System.out.println(bcourse);
		int rows = bCourseService.addBcourse(bcourse);
		Map<String,Object> map = new HashMap<String, Object>();
		if(rows > 0) {
			map.put("msg", "添加成功！");
		}else {
			map.put("msg", "添加失败！");
		}
		return map;
	}
	
	@RequestMapping("/deleteBCourse.do")
	@ResponseBody
	public Map<String,Object> deleteBcourse(int id){
		
		int rows = bCourseService.deleteBcourse(id);
		Map<String,Object> map = new HashMap<String, Object>();
		if(rows > 0) {
			map.put("msg", "删除成功！");
		}else {
			map.put("msg", "删除失败！");
		}
		return map;
	}
}
