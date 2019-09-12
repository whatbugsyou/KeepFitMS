package com.KeepFitMS.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.KeepFitMS.entity.AttendanceStatus;

@Mapper
public interface AttendanceStatusDao {
	/**
	 * 根据id查状态
	 * @param attS_id
	 * @return
	 */
	@Select("select * from AttendanceStatus where attS_id=#{attS_id}")
	public AttendanceStatus selectAttendanceStatusByAttSid(int attS_id);
}
