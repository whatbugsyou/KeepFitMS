package com.KeepFitMS.entity;

public class Wardrobe {
	
	private Integer wardrobe_id;
	private String wardrobe_name;
	private Integer wardrobe_status;
	public Integer getWardrobe_id() {
		return wardrobe_id;
	}
	public void setWardrobe_id(Integer wardrobe_id) {
		this.wardrobe_id = wardrobe_id;
	}
	public String getWardrobe_name() {
		return wardrobe_name;
	}
	public void setWardrobe_name(String wardrobe_name) {
		this.wardrobe_name = wardrobe_name;
	}
	public Integer getWardrobe_status() {
		return wardrobe_status;
	}
	public void setWardrobe_status(Integer wardrobe_status) {
		this.wardrobe_status = wardrobe_status;
	}
	@Override
	public String toString() {
		return "Wardrobe [wardrobe_id=" + wardrobe_id + ", wardrobe_name=" + wardrobe_name + ", wardrobe_status="
				+ wardrobe_status + "]";
	}
	
	

}
