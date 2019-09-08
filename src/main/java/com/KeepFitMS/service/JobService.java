package com.KeepFitMS.service;

import java.util.List;


import com.KeepFitMS.entity.Job;

/**
 * 职位
 * @author Hzl
 *
 */
public interface JobService {
	/**
	 * 查询所有职位
	 * @return
	 */
	public List<Job> selectAllJob();
	/**
	 * 查询某一部门下的所有职位
	 * @param dept_id
	 * @return
	 */
	public List<Job> selectJobByDeptId(int dept_id);
}
