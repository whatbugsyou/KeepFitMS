package com.KeepFitMS.entity;



public class M_record {
	private int mrid;
	private int mid;
	private String sdate;
	private String edate;
	private String remarks;
	@Override
	public String toString() {
		return "M_record [mrid=" + mrid + ", mid=" + mid + ", sdate=" + sdate + ", edate=" + edate + ", remarks="
				+ remarks + "]";
	}
	public int getMrid() {
		return mrid;
	}
	public void setMrid(int mrid) {
		this.mrid = mrid;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public String getEdate() {
		return edate;
	}
	public void setEdate(String edate) {
		this.edate = edate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
