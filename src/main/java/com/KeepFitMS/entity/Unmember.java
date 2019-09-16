package com.KeepFitMS.entity;

public class Unmember {
	private int umid;
	private String umname;
	private String umtelephone;
	public int getUmid() {
		return umid;
	}
	public void setUmid(int umid) {
		this.umid = umid;
	}
	public String getUmname() {
		return umname;
	}
	public void setUmname(String umname) {
		this.umname = umname;
	}
	public String getUmtelephone() {
		return umtelephone;
	}
	public void setUmtelephone(String umtelephone) {
		this.umtelephone = umtelephone;
	}
	@Override
	public String toString() {
		return "Unmember [umid=" + umid + ", umname=" + umname + ", umtelephone=" + umtelephone + "]";
	}
}
