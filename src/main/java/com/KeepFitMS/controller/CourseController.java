package com.KeepFitMS.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.KeepFitMS.entity.Course;
import com.KeepFitMS.entity.PageBean;
import com.KeepFitMS.service.CourseService;

/**
 * 课程控制层
 * @author zsz
 *
 */
@Controller
public class CourseController {

	@Resource
	private CourseService courseService;
	
	@RequestMapping("/getCourseList.do")
	@ResponseBody
	public Map<String,Object> findCourseList(@RequestParam(value="page",required = false) String page,
			@RequestParam(value="rows",required = false) String rows,Course course){
		//	封装pagebean
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		System.out.println(rows);
		System.out.println(page);
		System.out.println(course);
		return null;
	}
	
	
}
