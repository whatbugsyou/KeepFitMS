package com.KeepFitMS.entity;

/**
 * 	购课信息表
 * @author zsz
 *
 */
public class Bcourse {

	private int bc_id;//购课id
	private int bc_mid;//会员id
	private int bc_coachid;//教练id
	private int bc_courseid;//课程id
	private String bc_time;//购课时间
	
	private String mname;// 会员名称
	private String emp_name;//教练名称
	private String c_name;// 课程名称
	
	private String starttime;//开始时间
	private String endtime;//结束时间
	public int getBc_id() {
		return bc_id;
	}
	public void setBc_id(int bc_id) {
		this.bc_id = bc_id;
	}
	public int getBc_mid() {
		return bc_mid;
	}
	public void setBc_mid(int bc_mid) {
		this.bc_mid = bc_mid;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public int getBc_coachid() {
		return bc_coachid;
	}
	public void setBc_coachid(int bc_coachid) {
		this.bc_coachid = bc_coachid;
	}
	public int getBc_courseid() {
		return bc_courseid;
	}
	public void setBc_courseid(int bc_courseid) {
		this.bc_courseid = bc_courseid;
	}
	public String getBc_time() {
		return bc_time;
	}
	public void setBc_time(String bc_time) {
		this.bc_time = bc_time;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getC_name() {
		return c_name;
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	@Override
	public String toString() {
		return "Bcourse [bc_id=" + bc_id + ", bc_mid=" + bc_mid + ", bc_coachid=" + bc_coachid + ", bc_courseid="
				+ bc_courseid + ", bc_time=" + bc_time + ", mname=" + mname + ", emp_name=" + emp_name + ", c_name="
				+ c_name + ", starttime=" + starttime + ", endtime=" + endtime + "]";
	}
}
