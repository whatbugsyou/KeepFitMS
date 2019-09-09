package com.KeepFitMS.service;

import java.util.List;
import java.util.Map;

import com.KeepFitMS.entity.Bcourse;
import com.KeepFitMS.entity.Course_type;

/**
 * 购课service层
 * @author lenovo
 *
 */
public interface BCourseService {

	/**
	 * 查询所有的购课信息
	 * @return
	 */
	List<Bcourse> findAllBC(Map<String,Object> map);
	
	/**
	 * 	查询
	 * @param map
	 * @return
	 */
	long findTotalBcourse(Map<String,Object> map);
	
	/**
	 * 	添加购课记录
	 * @param bcourse
	 * @return
	 */
	int addBcourse(Bcourse bcourse);
	
	/**
	 * 	更新购课记录
	 * @param bcourse
	 * @return
	 */
	int updateBcourse(Bcourse bcourse);
	
	/**
	 * 	删除一条购课记录
	 * @param id
	 * @return
	 */
	int deleteBcourse(int id);
	
	/**
	 * 	添加新类型
	 * @param ct
	 * @return
	 */
	int addCT(Course_type ct);

	/**
	 * 获得所有课程信息 和 教练信息
	 * @return
	 */
	Map<String, Object> getInfoCE();
}
