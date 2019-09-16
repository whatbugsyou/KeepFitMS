package com.KeepFitMS.entity;

import java.io.Serializable;

/**
 * 商品父类型
 * @author suyin
 *
 */
public class Ptype implements Serializable{
	private static final long serialVersionUID = 3625758683745220023L;
	private Integer ptype_id;//父类型id
	private String ptype_name;//父类型名
	public Integer getPtype_id() {
		return ptype_id;
	}
	public void setPtype_id(Integer ptype_id) {
		this.ptype_id = ptype_id;
	}
	public String getPtype_name() {
		return ptype_name;
	}
	public void setPtype_name(String ptype_name) {
		this.ptype_name = ptype_name;
	}
	@Override
	public String toString() {
		return "Ptype [ptype_id=" + ptype_id + ", ptype_name=" + ptype_name + "]";
	}
	
	
	
}
