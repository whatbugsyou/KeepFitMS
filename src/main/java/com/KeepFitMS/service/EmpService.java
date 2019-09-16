package com.KeepFitMS.service;

import java.util.List;

import com.KeepFitMS.entity.Emp;
import com.KeepFitMS.exception.PersonnelServiceException;

public interface EmpService {
	/**
	 * 添加员工
	 * @param emp
	 * @return
	 * @throws PersonnelServiceException
	 */
	public boolean addEmp(Emp emp) throws PersonnelServiceException;
	/**
	 * 查所有员工
	 * @return
	 */
	public List<Emp> selectAllEmp();
	/**
	 * 删除员工
	 * @param emp_id
	 * @return
	 */
	public boolean deleteEmp(int emp_id);
	/**
	 * 修改员工
	 * @param emp
	 * @return
	 */
	public boolean modifyEmp(Emp emp);
	/**
	 * 查询同一部门下的员工
	 * @param dept_id
	 * @return
	 */
	public List<Emp> selectEmpByDeptId(int dept_id);
	
}
