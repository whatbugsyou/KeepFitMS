package com.KeepFitMS.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import com.KeepFitMS.entity.Attendance;

@Mapper
public interface AttendanceDao {
	/**
	 * 查询某员工某月的考勤记录
	 * @return
	 */
	@Select("select * from attendance where emp_id=#{emp_id} and  substring(att_day,1,7)= ${Year_Month}")
	@Results(id = "attendanceMap", value = { 
			//属性与字段相同可省略
			@Result(column = "att_status", property = "att_status", one = @One(fetchType = FetchType.DEFAULT, select = "com.KeepFitMS.dao.AttendanceStatusDao.selectAttendanceStatusByAttSid"))
			})
	public List<Attendance> selectAttendanceByEmpIdAndYearMonth(int emp_id,String Year_Month);
}
