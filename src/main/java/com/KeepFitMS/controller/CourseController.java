package com.KeepFitMS.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.KeepFitMS.entity.Course;
import com.KeepFitMS.entity.Course_type;
import com.KeepFitMS.entity.PageBean;
import com.KeepFitMS.service.CourseService;
import com.KeepFitMS.util.StringUtil;

/**
 * 课程控制层
 * @author zsz
 *
 */
@Controller
public class CourseController {

	@Resource
	private CourseService courseService;
	
	/**
	 * 	查询课程
	 * @param page
	 * @param rows
	 * @param course
	 * @return
	 */
	@RequestMapping("/getCourseList.do")
	@ResponseBody
	public Map<String,Object> findCourseList(@RequestParam(value="page",required = false) String page,
			@RequestParam(value="rows",required = false) String rows,Course course){
		//	封装pagebean
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		System.out.println(rows);
		System.out.println(page);
		System.out.println(course);
		//	数据分装到map中
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("c_name", StringUtil.formatLike(course.getC_name()));
		map.put("c_type", course.getC_type());
		map.put("c_coach", StringUtil.formatLike(course.getC_coach()));
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		//	分页查询
		List<Course> list = courseService.findCourse(map);
		System.out.println("输出了");
		for (Course c : list) {
			System.out.println(c);
		}
		//	查询满足要求条数
		long totalresult = courseService.getTotalCourse(map);
		System.out.println(totalresult);
		
		Map<String,Object> rmap = new HashMap<String, Object>();
		rmap.put("courses", list);
		rmap.put("total", totalresult);
		return rmap;
	}
	
	@RequestMapping("/deleteCourse.do")
	@ResponseBody
	public Map<String,Object> deleteCourse(int c_id){
		
		int resultrows = 0;
		Map<String,Object> map = new HashMap<String, Object>();
		resultrows = courseService.deleteCourse(c_id);
		if(resultrows > 0) {
			map.put("msg", "删除成功！");
		}else {
			map.put("msg", "删除失败");
		}
		return map;
	}
	
	@RequestMapping("/getCourseType.do")
	@ResponseBody
	public List<Course_type> getCouresType(){
		System.out.println("进入了 查询所有的课程类型");
		List<Course_type> list = courseService.getAllCourse_type();
		System.out.println("list");
		return list;
	}
	
	@RequestMapping("/addCourse.do")
	@ResponseBody
	public Map<String,Object> addCourse(Course course){
		
		int resultrows = courseService.addCourse(course);
		Map<String,Object> map = new HashMap<String, Object>();
		if(resultrows>0) {
			map.put("msg", "添加成功！");
		}else {
			map.put("msg","添加失败！");
		}
		
		return map;
	}
	
	@RequestMapping("/updateCourse.do")
	@ResponseBody
	public Map<String,Object> updateCourse(Course course){
		int resultrows = courseService.updateCourse(course);
		Map<String,Object> map = new HashMap<String, Object>();
		if(resultrows>0) {
			map.put("msg", "更新成功！");
		}else {
			map.put("msg","更新失败！");
		}
		return map;
	}
	
	@RequestMapping("/showCoursepie.do")
	@ResponseBody
	public Object[] showCoursepie(){
		
		//	查询所有的课程
		List<Course> courses = courseService.findAllCourse();
		//	查询所有的课程类型
		List<Course_type> ct = courseService.getAllCourse_type();
		//	遍历 放到数组中
		int totoalct = ct.size();
		int totoalc = courses.size();
		System.out.println("课程总数："+totoalc);
		System.out.println("类型总数："+totoalct);
		Object [] arr = new Object[totoalct];
		for (int i = 0; i < ct.size(); i++) {
			int tempCount = 0;
			for(int j = 0; j< courses.size();j++) {
				//	如果课程相同那么 加一
				if(ct.get(i).getCt_name().equals(courses.get(j).getC_type())) {
					tempCount++;
				}
			}
			//	将类型名称和 锁站所站比例放入数组中
			Object [] temparr = new Object[2];
			//	类型名称
			temparr[0] = ct.get(i).getCt_name();
			System.out.println(tempCount);
			System.out.println(temparr[0]);
			//	所占比例
			temparr[1] = (double) Math.round((tempCount*100)/totoalc);
			System.out.println(temparr[1]);
			//	放入数组中
			arr[i] = temparr;
		}
		return arr;
	}
	
	
}
