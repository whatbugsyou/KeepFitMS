package com.KeepFitMS.entity;

import java.io.Serializable;

public class Emp implements Serializable,Cloneable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8771564728165324449L;
	
	private int emp_id;
	private String emp_name;
	private Job job;//表中为job_id
	private String emp_hiredate;
	private String emp_phone;
	private Dept dept;//表中为dept_id
	
	public String getEmp_phone() {
		return emp_phone;
	}
	public void setEmp_phone(String emp_phone) {
		this.emp_phone = emp_phone;
	}
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
	public Job getJob() {
		return job;
	}
	public void setJob(Job job) {
		this.job = job;
	}
	public String getEmp_hiredate() {
		return emp_hiredate;
	}
	public void setEmp_hiredate(String emp_hiredate) {
		this.emp_hiredate = emp_hiredate;
	}
	public Dept getDept() {
		return dept;
	}
	@Override
	public String toString() {
		return "Emp [emp_id=" + emp_id + ", emp_name=" + emp_name + ", job=" + job + ", emp_hiredate=" + emp_hiredate
				+ ", dept=" + dept + "]";
	}
	public void setDept(Dept dept) {
		this.dept = dept;
	}
	

}
