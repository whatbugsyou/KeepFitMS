package com.KeepFitMS.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KeepFitMS.dao.AttendanceDao;
import com.KeepFitMS.entity.Attendance;
import com.KeepFitMS.service.AttendanceService;

@Service 
public class AttendanceServiceImpl implements AttendanceService {
	@Autowired
	private AttendanceDao attendanceDao;
	@Override
	public List<Attendance> selectAttendanceByEmpIdAndYearMonth(int emp_id, String Year_Month) {
		return attendanceDao.selectAttendanceByEmpIdAndYearMonth(emp_id, Year_Month);
	}

}
