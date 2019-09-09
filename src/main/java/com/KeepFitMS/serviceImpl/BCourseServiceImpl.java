package com.KeepFitMS.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.KeepFitMS.dao.BcourseDao;
import com.KeepFitMS.dao.CourseDao;
import com.KeepFitMS.dao.EmpDao;
import com.KeepFitMS.dao.MemberDao;
import com.KeepFitMS.entity.Bcourse;
import com.KeepFitMS.entity.Course;
import com.KeepFitMS.entity.Course_type;
import com.KeepFitMS.entity.Emp;
import com.KeepFitMS.entity.Member;
import com.KeepFitMS.service.BCourseService;
import com.KeepFitMS.service.EmpService;

@Service
public class BCourseServiceImpl implements BCourseService{

	@Resource
	private BcourseDao bCourseDao;
	
	@Resource
	private CourseDao courseDao;
	
	@Resource
	private EmpDao empDao;
	
	@Resource
	private MemberDao memberDao;
	
	@Override
	public List<Bcourse> findAllBC(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return bCourseDao.findBcourse(map);
	}

	@Override
	public long findTotalBcourse(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return bCourseDao.findTotalBcourse(map);
	}

	@Override
	public int addBcourse(Bcourse bcourse) {
		// TODO Auto-generated method stub
		return bCourseDao.addBcourse(bcourse);
	}

	@Override
	public int updateBcourse(Bcourse bcourse) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteBcourse(int id) {
		// TODO Auto-generated method stub
		return bCourseDao.deleteBcourse(id);
	}

	@Override
	public int addCT(Course_type ct) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 查询所有的课程信息 以及教练信息
	 * 开启事务
	 */
	@Override
	@Transactional
	public Map<String, Object> getInfoCE() {
		// TODO Auto-generated method stub
		
		List<Course> course = courseDao.findAllCourse();
		List<Emp> emp = empDao.selectEmpByDeptId(1);
		List<Member> members = memberDao.getAllMember();
		//包装
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("courses", course);
		map.put("emps", emp);
		map.put("members", members);
		return map;
	}

	
}
