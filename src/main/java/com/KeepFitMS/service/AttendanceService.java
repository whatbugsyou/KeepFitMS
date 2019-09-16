package com.KeepFitMS.service;

import java.util.List;

import com.KeepFitMS.entity.Attendance;

public interface AttendanceService {
	public List<Attendance> selectAttendanceByEmpIdAndYearMonth(int emp_id,String Year_Month);
}
