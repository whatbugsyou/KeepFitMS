package com.KeepFitMS.entity;

import java.io.Serializable;

public class Wardroberecord implements Serializable{

	private Integer wr_id;
	private Integer cid;
	private String mname;
	private String wardrobe_name;
	private String wr_regtime;
	private String wr_status;
	private String wr_starttime;
	private String wr_endtime;
	private Integer wr_deposit;
	private String wr_desc;
	public Integer getWr_id() {
		return wr_id;
	}
	public void setWr_id(Integer wr_id) {
		this.wr_id = wr_id;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getWardrobe_name() {
		return wardrobe_name;
	}
	public void setWardrobe_name(String wardrobe_name) {
		this.wardrobe_name = wardrobe_name;
	}
	public String getWr_regtime() {
		return wr_regtime;
	}
	public void setWr_regtime(String wr_regtime) {
		this.wr_regtime = wr_regtime;
	}
	public String getWr_status() {
		return wr_status;
	}
	public void setWr_status(String wr_status) {
		this.wr_status = wr_status;
	}
	public String getWr_starttime() {
		return wr_starttime;
	}
	public void setWr_starttime(String wr_starttime) {
		this.wr_starttime = wr_starttime;
	}
	public String getWr_endtime() {
		return wr_endtime;
	}
	public void setWr_endtime(String wr_endtime) {
		this.wr_endtime = wr_endtime;
	}
	public Integer getWr_deposit() {
		return wr_deposit;
	}
	public void setWr_deposit(Integer wr_deposit) {
		this.wr_deposit = wr_deposit;
	}
	public String getWr_desc() {
		return wr_desc;
	}
	public void setWr_desc(String wr_desc) {
		this.wr_desc = wr_desc;
	}
	@Override
	public String toString() {
		return "Wardroberecord [wr_id=" + wr_id + ", cid=" + cid + ", mname=" + mname + ", wardrobe_name="
				+ wardrobe_name + ", wr_regtime=" + wr_regtime + ", wr_status=" + wr_status + ", wr_starttime="
				+ wr_starttime + ", wr_endtime=" + wr_endtime + ", wr_deposit=" + wr_deposit + ", wr_desc=" + wr_desc
				+ "]";
	}
	
	
}
