package com.KeepFitMS.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.KeepFitMS.entity.Emp;

/**
 * 员工管理接口
 * @author Hzl
 *
 */
@Mapper
public interface EmpDao {

	/**
	 * 	查询所有的员工
	 * @param map
	 * @return
	 */
	List<Emp> selectAllEmp();
	
	/**
	 * 	查询员工总数
	 * @param map
	 * @return
	 */
	long selectCountOfEmp();
	
	/**
	 * 查询某部门的所有员工
	 * @param dept_id 部门id
	 * @return
	 */
	List<Emp> selectEmpByDeptId(int dept_id);
	/**
	 * 根据员工id查询员工
	 * @param emp_id 员工id
	 * @return
	 */
	Emp selectEmpByEmpId(int emp_id);
	
	/**
	 * 	添加员工
	 * @param Emp
	 * @return
	 */
	int addEmp(Emp Emp);
	
	/**
	 * 	更新员工信息
	 * @param Emp
	 * @return
	 */
	int updateEmp(Emp Emp);
	
	/**
	 * 	删除一条员工记录
	 * @param id
	 * @return
	 */
	int deleteEmp(int emp_id);
	
}
