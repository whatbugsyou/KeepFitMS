package com.KeepFitMS.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.FetchType;

import com.KeepFitMS.entity.Emp;

/**
 * 员工管理接口
 * 
 * @author Hzl
 *
 */

@Mapper

public interface EmpDao {
	
	/**
	 * 查询所有的员工
	 * 
	 * @param map
	 * @return
	 */
	@Select("select * from emp")
	@Results(id = "empMap", value = { 
			//属性与字段相同可省略
			//@Result(column = "emp_id", property = "emp_id", id = true),
			//@Result(column = "emp_name", property = "emp_name"), 
			//@Result(column = "emp_hiredate", property = "emp_hiredate"),
			//@Result(column = "emp_phone", property = "emp_phone"),
			@Result(column = "job_id", property = "job", one = @One(fetchType = FetchType.DEFAULT, select = "com.KeepFitMS.dao.JobDao.selectJobByJobIdWithoutRecursion")),
			@Result(column = "dept_id", property = "dept", one = @One(fetchType = FetchType.DEFAULT, select = "com.KeepFitMS.dao.DeptDao.selectDeptByDeptId")), 
			})
	List<Emp> selectAllEmp();

	/**
	 * 查询员工总数
	 * 
	 * @param map
	 * @return
	 */
	@Select("select count(*) from emp")
	long selectCountOfEmp();

	/**
	 * 查询某部门的所有员工
	 * 
	 * @param dept_id 部门id
	 * @return
	 */
	@ResultMap("empMap")
	@Select("select * from emp where dept_id=#{dept_id}")
	List<Emp> selectEmpByDeptId(int dept_id);

	/**
	 * 根据员工id查询员工
	 * 
	 * @param emp_id 员工id
	 * @return
	 */
	@ResultMap("empMap")
	@Select("select * from emp where emp_id=#{emp_id}")
	Emp selectEmpByEmpId(int emp_id);

	/**
	 * 添加员工
	 * 
	 * @param Emp
	 * @return
	 */
	@Options(useGeneratedKeys=true,keyProperty="emp_id",keyColumn="emp_id")
	@Insert("insert into emp(emp_name,emp_hiredate,job_id,dept_id) values(#{emp_name},#{emp_hiredate},#{job.job_id},#{dept.dept_id})")
	int addEmp(Emp Emp);

	/**
	 * 更新员工信息
	 * 
	 * @param Emp
	 * @return
	 */
	@Update("update emp set emp_name=#{emp_name},emp_hiredate=#{emp_hiredate},job_id=#{job.job_id},dept_id={dept.dept_id}")
	int updateEmp(Emp Emp);

	/**
	 * 删除一条员工记录
	 * 
	 * @param id
	 * @return
	 */
	@Delete("delete from emp where emp_id=#{emp_id}")
	int deleteEmp(int emp_id);

}
