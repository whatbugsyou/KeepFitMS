package com.KeepFitMS.entity;

import java.io.Serializable;

/**
 * 出售记录
 * @author suyin
 *
 */
public class Records  implements Serializable{
	private static final long serialVersionUID = 3625758683745220023L;
	private Integer records_id;
	private String records_uuid;
	private String start_time;
	private String end_time;
	private String records_time;
	private Integer records_money;
	private Goods goods;
	private Integer cid;
	public Integer getRecords_id() {
		return records_id;
	}
	public void setRecords_id(Integer records_id) {
		this.records_id = records_id;
	}
	public String getRecords_uuid() {
		return records_uuid;
	}
	public void setRecords_uuid(String records_uuid) {
		this.records_uuid = records_uuid;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getRecords_time() {
		return records_time;
	}
	public void setRecords_time(String records_time) {
		this.records_time = records_time;
	}
	public Integer getRecords_money() {
		return records_money;
	}
	public void setRecords_money(Integer records_money) {
		this.records_money = records_money;
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Records [records_id=" + records_id + ", records_uuid=" + records_uuid + ", start_time=" + start_time
				+ ", end_time=" + end_time + ", records_time=" + records_time + ", records_money=" + records_money
				+ ", goods=" + goods + ", cid=" + cid + "]";
	}

	 
	
	
}
