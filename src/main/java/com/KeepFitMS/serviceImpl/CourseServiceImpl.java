package com.KeepFitMS.serviceImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.KeepFitMS.dao.CourseDao;
import com.KeepFitMS.entity.Course;
import com.KeepFitMS.entity.Course_type;
import com.KeepFitMS.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService{

	@Resource
	private CourseDao courseDao;
	
	@Override
	public List<Course> findCourse(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return courseDao.findCourse(map);
	}

	@Override
	public long getTotalCourse(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return courseDao.getTotalCourse(map);
	}

	@Override
	public int addCourse(Course course) {
		// TODO Auto-generated method stub
		return courseDao.addCourse(course);
	}

	@Override
	public int deleteCourse(int id) {
		// TODO Auto-generated method stub
		return courseDao.deleteCourse(id);
	}

	@Override
	public int updateCourse(Course course) {
		// TODO Auto-generated method stub
		return courseDao.updateCourse(course);
	}

	@Override
	public List<Course_type> getAllCourse_type() {
		// TODO Auto-generated method stub
		return courseDao.getAllCourse_type();
	}

	@Override
	public List<Course> findAllCourse() {
		// TODO Auto-generated method stub
		return courseDao.findAllCourse();
	}

	
}
