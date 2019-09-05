package com.KeepFitMS.entity;

import java.io.Serializable;

public class Emp implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8771564728165324449L;
	
	private int emp_id;
	private String emp_name;
	private int job_id;
	private String job_name;//表中没有此字段
	private String emp_hiredate;
	private int dept_id;
	private String  dept_name;//表中没有此字段
	public int getJob_id() {
		return job_id;
	}
	public void setJob_id(int job_id) {
		this.job_id = job_id;
	}
	public int getDept_id() {
		return dept_id;
	}
	public void setDept_id(int dept_id) {
		this.dept_id = dept_id;
	}
	private String emp_phone;
	public int getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getJob_name() {
		return job_name;
	}
	public void setJob_name(String job_name) {
		this.job_name = job_name;
	}
	public String getEmp_hiredate() {
		return emp_hiredate;
	}
	public void setEmp_hiredate(String emp_hiredate) {
		this.emp_hiredate = emp_hiredate;
	}
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	public String getEmp_phone() {
		return emp_phone;
	}
	public void setEmp_phone(String emp_phone) {
		this.emp_phone = emp_phone;
	}
	@Override
	public String toString() {
		return "Emp [emp_id=" + emp_id + ", emp_name=" + emp_name + ", job_id=" + job_id + ", job_name=" + job_name
				+ ", emp_hiredate=" + emp_hiredate + ", dept_id=" + dept_id + ", dept_name=" + dept_name
				+ ", emp_phone=" + emp_phone + "]";
	}


}
