package com.KeepFitMS.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.KeepFitMS.entity.Job;
/**
 * 职位管理接口
 * @author Hzl
 *
 */
@Mapper
public interface JobDao {
	/**
	 * 	查询所有的职位
	 * @param map
	 * @return
	 */
	List<Job> selectAllJob();
	
	/**
	 * 	查询职位总数
	 * @param map
	 * @return
	 */
	long selectCountOfJob();
	
	/**
	 * 查询某部门的所有职位
	 * @param dept_id 部门id
	 * @return
	 */
	List<Job> selectJobByDeptId(int dept_id);
	/**
	 * 根据职位id查询职位
	 * @param Job_id 职位id
	 * @return
	 */
	Job selectJobByJobId(int Job_id);
	
	/**
	 * 	添加职位
	 * @param Job
	 * @return
	 */
	int addJob(Job Job);
	
	/**
	 * 	更新职位信息
	 * @param Job
	 * @return
	 */
	int updateJob(Job Job);
	
	/**
	 * 	删除一条职位
	 * @param id
	 * @return
	 */
	int deleteJob(int Job_id);
}
