package com.KeepFitMS.entity;

import java.sql.Timestamp;
import java.util.Date;

/*
 * 会员卡实体
 * */
public class Card {
	@Override
	public String toString() {
		return "Card [cid=" + cid + ", ctype=" + ctype + ", cprice=" + cprice + ", cmoney=" + cmoney + ", cpoint="
				+ cpoints + ", mid=" + mid + ", sdate=" + sdate + ", edate=" + edate + "]";
	}
	private int cid;
	private String ctype;
	private int cprice;
	private int cmoney;
	private int cpoints;
	private int mid;
	private Date sdate;
	private Date edate;
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCtype() {
		return ctype;
	}
	public void setCtype(String ctype) {
		this.ctype = ctype;
	}
	public int getCprice() {
		return cprice;
	}
	public void setCprice(int cprice) {
		this.cprice = cprice;
	}
	public int getCmoney() {
		return cmoney;
	}
	public void setCmoney(int cmoney) {
		this.cmoney = cmoney;
	}
	public int getCpoints() {
		return cpoints;
	}
	public void setCpoints(int cpoint) {
		this.cpoints = cpoint;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public Date getSdate() {
		if(sdate==null) {
			return null;
		}
	        Timestamp sqlDate = new Timestamp(sdate.getTime());//uilt date转sql date
	     

		return sqlDate;
	}
	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}
	public Date getEdate() {
		if(edate==null) {
			return null;
		}
		   Timestamp sqlDate = new Timestamp(edate.getTime());//uilt date转sql date
		     

			return sqlDate;
	}
	public void setEdate(Date edate) {
		this.edate = edate;
	}
}
