package com.KeepFitMS.entity;

import java.io.Serializable;

public class Dept implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6618840644252464676L;
	
	private int dept_id;
	private String dept_name;
	private int dept_memberNum;//部门人数
	
	public int getDept_id() {
		return dept_id;
	}
	public void setDept_id(int dept_id) {
		this.dept_id = dept_id;
	}
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	public int getDept_memberNum() {
		return dept_memberNum;
	}
	public void setDept_memberNum(int dept_memberNum) {
		this.dept_memberNum = dept_memberNum;
	}
	@Override
	public String toString() {
		return "Dept [dept_id=" + dept_id + ", dept_name=" + dept_name + ", dept_memberNum=" + dept_memberNum + "]";
	}

	
}
