package com.KeepFitMS.exception;

public enum PersonnelServiceExceptionEnum {
	/**
	 * 部门异常
	 */
	DEPT_ALREADY_EXIST("101","部门已经存在"),

	/**
	 * 职位异常
	 */
	JOB_ALREADY_EXIST("201","职位已经存在");
	
	
	private String code;
    private String msg;
    
    PersonnelServiceExceptionEnum(String code, String text) {
        this.code = code;
        this.msg = text;
    }
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
