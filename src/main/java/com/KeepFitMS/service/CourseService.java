package com.KeepFitMS.service;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.KeepFitMS.entity.Course;

/**
 * 	课程服务层
 * @author zsz
 *
 */
public interface CourseService {
	
	/**
	 * 	按分页要求显示课程
	 * @param map
	 * @return
	 */
	List<Course> findCourse(Map<String,Object> map);
	
	/**
	 * 	查询查询所有满足要求的总条数
	 * @param map
	 * @return
	 */
	long getTotalCourse(Map<String,Object> map);
	
	
	/**
	 * 	添加一条课程
	 * @param course
	 * @return
	 */
	int addCourse(Course course);
	
	/**
	 * 	删除一条课程
	 * @param id
	 * @return
	 */
	int deleteCourse(int id);
	
	/**
	 * 	修改课程信息
	 * @param course
	 * @return
	 */
	int updateCourse(Course course);
}
