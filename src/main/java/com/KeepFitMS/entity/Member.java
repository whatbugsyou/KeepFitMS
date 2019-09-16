package com.KeepFitMS.entity;

import java.sql.Date;

public class Member {
	private int mid;
	private String mname;
	private String mtelephone;
	private Date mdate;
	private String msfz;
	private String maddress;
	private String msex;
	private int coach_id;

	private String needs;
	private String icon;
	private Card card;

	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getMtelephone() {
		return mtelephone;
	}
	public void setMtelephone(String mtelephone) {
		this.mtelephone = mtelephone;
	}
	public Date getMdate() {
		return mdate;
	}
	public void setMdate(Date mdate) {
		this.mdate = mdate;
	}
	public String getMsfz() {
		return msfz;
	}
	public void setMsfz(String msfz) {
		this.msfz = msfz;
	}
	public String getMaddress() {
		return maddress;
	}
	public void setMaddress(String maddress) {
		this.maddress = maddress;
	}
	public String getMsex() {
		return msex;
	}
	public void setMsex(String msex) {
		this.msex = msex;
	}
	public int getCoach_id() {
		return coach_id;
	}
	public void setCoach_id(int coach_id) {
		this.coach_id = coach_id;
	}

	@Override
	public String toString() {
		return "Member [mid=" + mid + ", mname=" + mname + ", mtelephone=" + mtelephone + ", mdate=" + mdate + ", msfz="
				+ msfz + ", maddress=" + maddress + ", msex=" + msex + ", coach_id=" + coach_id + ", needs=" + needs
				+ ", icon=" + icon + ", card=" + card + "]";
	}
	public String getNeeds() {
		return needs;
	}
	public void setNeeds(String needs) {
		this.needs = needs;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Card getCard() {
		return card;
	}
	public void setCard(Card card) {
		this.card = card;
	}
}
