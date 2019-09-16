package com.KeepFitMS.entity;

public class Attendance {
	private int att_id;
	private String  att_day; //日期
	private int emp_id; //员工号
	private String att_startTime; //打卡开始时间
	private String att_endTime; //打卡离开
	private AttendanceStatus att_status; //考勤状态：正常，迟到等
	
	public int getAtt_id() {
		return att_id;
	}
	public void setAtt_id(int att_id) {
		this.att_id = att_id;
	}
	@Override
	public String toString() {
		return "Attendance [att_id=" + att_id + ", att_day=" + att_day + ", emp_id=" + emp_id + ", att_startTime="
				+ att_startTime + ", att_endTime=" + att_endTime + ", att_status=" + att_status + "]";
	}
	public String getAtt_day() {
		return att_day;
	}
	public void setAtt_day(String att_day) {
		this.att_day = att_day;
	}
	public int getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}
	public String getAtt_startTime() {
		return att_startTime;
	}
	public void setAtt_startTime(String att_startTime) {
		this.att_startTime = att_startTime;
	}
	public String getAtt_endTime() {
		return att_endTime;
	}
	public void setAtt_endTime(String att_endTime) {
		this.att_endTime = att_endTime;
	}
	public AttendanceStatus getAtt_status() {
		return att_status;
	}
	public void setAtt_status(AttendanceStatus att_status) {
		this.att_status = att_status;
	}
}
