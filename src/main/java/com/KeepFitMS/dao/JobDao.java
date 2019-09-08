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
	@Select("select * from job")
	@Results(id = "jobMap", value = { 
			@Result(column = "job_id", property = "job_id", id = true),
			@Result(column = "job_name", property = "job_name"), 
			@Result(column = "job_sal", property = "job_sal"),
			@Result(column = "dept_id", property = "dept", one = @One(fetchType = FetchType.DEFAULT, select = "com.KeepFitMS.dao.DeptDao.selectDeptByDeptId")), 
			})
	List<Job> selectAllJob();
	
	/**
	 * 	查询职位总数
	 * @param map
	 * @return
	 */
	@Select("select count(*) from job")
	long selectCountOfJob();
	
	/**
	 * 查询某部门的所有职位
	 * @param dept_id 部门id
	 * @return
	 */
	@ResultMap("jobMap")
	@Select("select * from job where dept_id=#{dept_id}")
	List<Job> selectJobByDeptId(int dept_id);
	/**
	 * 根据职位id查询职位
	 * @param Job_id 职位id
	 * @return
	 */
	@ResultMap("jobMap")
	@Select("select * from job where job_id=#{job_id}")
	Job selectJobByJobId(int Job_id);
	
	@Select("select * from job where job_id=#{job_id}")
	Job selectJobByJobIdWithoutRecursion(int Job_id);
	/**
	 * 	添加职位
	 * @param Job
	 * @return
	 */
	@Options(useGeneratedKeys=true,keyProperty="job_id",keyColumn="job_id")
	@Insert("insert into job(job_name,job_hiredate,job_id,dept_id) values(#{job_name},#{job_hiredate},#{job.job_id},#{dept.dept_id})")
	int insertJob(Job Job);
	
	/**
	 * 	更新职位信息
	 * @param Job
	 * @return
	 */
	@Update("update job set job_name=#{job_name},job_hiredate=#{job_hiredate},job_id=#{job.job_id},dept_id={dept.dept_id} where job_id=#{job_id}")
	int updateJob(Job Job);
	
	/**
	 * 	删除一条职位
	 * @param id
	 * @return
	 */
	@Delete("delete from job where job_id=#{job_id}")
	int deleteJob(int Job_id);
}
