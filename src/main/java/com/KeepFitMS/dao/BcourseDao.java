package com.KeepFitMS.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.KeepFitMS.entity.Bcourse;
import com.KeepFitMS.entity.Course_type;

/**
 * 	购课信息管理持久层
 * @author zsz
 *
 */
@Mapper
public interface BcourseDao {

	/**
	 * 	查询所有的购课记录
	 * @param map
	 * @return
	 */
	List<Bcourse> findBcourse(Map<String,Object> map);
	
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
	 * 	查询所有的类型
	 * @return
	 */
	List<Course_type> findAllCT();
	
	/**
	 * 	添加新类型
	 * @param ct
	 * @return
	 */
	int addCT(Course_type ct);
}
