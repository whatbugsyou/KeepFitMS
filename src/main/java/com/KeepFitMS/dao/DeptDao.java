package com.KeepFitMS.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
	@Select("select * from dept")
	List<Dept> selectAllDept();
	
	/**
	 * 	查询部门总数
	 * @param map
	 * @return
	 */
	@Select("select count(*) from dept")
	long selectCountOfDept();

	/**
	 * 根据id查询部门
	 * @param dept_id 部门id
	 * @return
	 */

	@Select("select * from dept where dept_id=#{dept_id}")
	Dept selectDeptByDeptId(int dept_id);
	/**
	 * 根据dept_name查询部门
	 * @param dept_name 部门名
	 * @return
	 */

	@Select("select * from dept where dept_name=#{dept_name}")
	Dept selectDeptByDeptName(String dept_name);
	
	/**
	 * 	添加部门
	 * @param Dept
	 * @return
	 */
	@Options(useGeneratedKeys=true,keyProperty="dept_id",keyColumn="dept_id")
	@Insert("insert into dept(dept_name,dept_memberNum) values(#{dept_name},0)")
	int insertDept(Dept Dept);
	
	/**
	 * 	更新部门信息
	 * @param Dept
	 * @return
	 */
	@Update("update dept set dept_name=#{dept_name},dept_memberNum=#{dept_memberNum} where dept_id=#{dept_id}")
	int updateDept(Dept Dept);
	
	/**
	 * 	删除一个部门信息
	 * @param dept_id
	 * @return
	 */
	@Delete("delete from dept where dept_id=#{dept_id}")
	int deleteDept(int dept_id);
	/**
	 * 部门人数添加1
	 * @param dept_id
	 * @return
	 */
	@Update("update dept set dept_memberNum=dept_memberNum+1 where dept_id=#{dept_id}")
	int increaseDeptMemberNumByDetpId(int dept_id);
	/**
	 * 部门人数减少1
	 * @param dept_id
	 * @return
	 */
	@Update("update dept set dept_memberNum=dept_memberNum-1 where dept_id=#{dept_id}")
	int decreaseDeptMemberNumByDetpId(int dept_id);

}
