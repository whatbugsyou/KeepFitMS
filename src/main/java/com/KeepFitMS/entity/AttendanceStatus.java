package com.KeepFitMS.entity;

public class AttendanceStatus {
	private int attS_id; // （1-7） 如下
	private String attS_desc; // 状态描述 （正常，请假，迟到，出差，早退，矿工，加班）

	@Override
	public String toString() {
		return "AttendanceStatus [attS_id=" + attS_id + ", attS_desc=" + attS_desc + "]";
	}

	public int getAttS_id() {
		return attS_id;
	}

	public void setAttS_id(int attS_id) {
		this.attS_id = attS_id;
	}

	public String getAttS_desc() {
		return attS_desc;
	}

	public void setAttS_desc(String attS_desc) {
		this.attS_desc = attS_desc;
	}
}
