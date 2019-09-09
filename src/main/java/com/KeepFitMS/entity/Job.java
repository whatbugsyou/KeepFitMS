package com.KeepFitMS.entity;

import java.io.Serializable;

public class Job implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3625758683745220023L;
	private int job_id;
	private String job_name;
	private int job_sal;
	private int dept_id;
	private String dept_name;//表中没有此字段
	public int getDept_id() {
		return dept_id;
	}
	public void setDept_id(int dept_id) {
		this.dept_id = dept_id;
	}
	public int getJob_id() {
		return job_id;
	}
	public void setJob_id(int job_id) {
		this.job_id = job_id;
	}
	public String getJob_name() {
		return job_name;
	}
	public void setJob_name(String job_name) {
		this.job_name = job_name;
	}
	public int getJob_sal() {
		return job_sal;
	}
	public void setJob_sal(int job_sal) {
		this.job_sal = job_sal;
	}
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	@Override
	public String toString() {
		return "Job [job_id=" + job_id + ", job_name=" + job_name + ", job_sal=" + job_sal + ", dept_id=" + dept_id
				+ ", dept_name=" + dept_name + "]";
	}
	
}
