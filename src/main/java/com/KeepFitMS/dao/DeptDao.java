package com.KeepFitMS.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.KeepFitMS.entity.Dept;
/**
 * 部门管理接口
 * @author Hzl
 *
 */
@Mapper
public interface DeptDao {

	/**
	 * 	查询所有的部门
	 * @param map
	 * @return
	 */
	List<Dept> selectAllDept();
	
	/**
	 * 	查询部门总数
	 * @param map
	 * @return
	 */
	long selectCountOfDept();
	
	/**
	 * 查询员工所属部门
	 * @param emp_id 员工id
	 * @return
	 */
	Dept selectDeptByEmpId(int emp_id);
	
	/**
	 * 根据id查询部门
	 * @param dept_id 部门id
	 * @return
	 */
	Dept selectDeptByDeptId(int dept_id);
	
	/**
	 * 	添加部门
	 * @param Dept
	 * @return
	 */
	int addDept(Dept Dept);
	
	/**
	 * 	更新部门信息
	 * @param Dept
	 * @return
	 */
	int updateDept(Dept Dept);
	
	/**
	 * 	删除一个部门信息
	 * @param id
	 * @return
	 */
	int deleteDept(int dept_id);
}
