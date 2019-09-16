package com.KeepFitMS.entity;
/**
 * 	课程类别表
 * @author zsz
 *
 */
public class Course_type {

	private int ct_id;
	private String ct_name;
	
	public int getCt_id() {
		return ct_id;
	}
	public void setCt_id(int ct_id) {
		this.ct_id = ct_id;
	}
	public String getCt_name() {
		return ct_name;
	}
	public void setCt_name(String ct_name) {
		this.ct_name = ct_name;
	}
	@Override
	public String toString() {
		return "Course_type [ct_id=" + ct_id + ", ct_name=" + ct_name + "]";
	}
	
	
}
