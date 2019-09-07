package com.KeepFitMS.service;

import java.util.List;

import com.KeepFitMS.entity.Dept;
import com.KeepFitMS.exception.PersonnelServiceException;

public interface DeptService {
	/**
	 * 查询所有部门
	 * @return
	 */
	public List<Dept> selectAllDept();
	/**
	 * 添加部门
	 * @param dept
	 * @return
	 * @throws PersonnelServiceException
	 */
	public boolean addDept(Dept dept) throws PersonnelServiceException;
	/**
	 * 修改部门
	 * @param dept
	 * @return
	 * @throws PersonnelServiceException
	 */
	public boolean modifyDept(Dept dept) throws PersonnelServiceException;
	/**
	 * 删除部门
	 * @param dept_id
	 * @return
	 * @throws PersonnelServiceException
	 */
	public boolean deleteDept(int dept_id) throws PersonnelServiceException;
	/**
	 * 部门人数加1
	 * @param dept_id
	 * @return
	 */
	public boolean increaseDeptMemberNumByDetpId(int dept_id);
	/**
	 * 部门人数减1
	 * @param dept_id
	 * @return
	 */
	public boolean decreaseDeptMemberNumByDetpId(int dept_id);
	
}
