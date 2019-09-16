package com.KeepFitMS.entity;

/**
 * 	课程表
 * @author zsz
 *
 */
public class Course {

	//课程id
	private int c_id;
	//课程名
	private String c_name;
	//课程类型
	private String c_type;
	//课程教练
	private String c_coach;
	//课程节数
	private int c_num;
	//课程价格
	private int c_price;
	public int getC_id() {
		return c_id;
	}
	public void setC_id(int c_id) {
		this.c_id = c_id;
	}
	public String getC_name() {
		return c_name;
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	public String getC_type() {
		return c_type;
	}
	public void setC_type(String c_type) {
		this.c_type = c_type;
	}
	public String getC_coach() {
		return c_coach;
	}
	public void setC_coach(String c_coach) {
		this.c_coach = c_coach;
	}
	public int getC_num() {
		return c_num;
	}
	public void setC_num(int c_num) {
		this.c_num = c_num;
	}
	public int getC_price() {
		return c_price;
	}
	public void setC_price(int c_price) {
		this.c_price = c_price;
	}
	@Override
	public String toString() {
		return "Course [c_id=" + c_id + ", c_name=" + c_name + ", c_type=" + c_type + ", c_coach=" + c_coach
				+ ", c_num=" + c_num + ", c_price=" + c_price + "]";
	}
	
}
