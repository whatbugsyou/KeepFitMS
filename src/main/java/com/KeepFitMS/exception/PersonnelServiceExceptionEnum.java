package com.KeepFitMS.exception;

public enum PersonnelServiceExceptionEnum {
	/**
	 * 部门异常
	 */
	DEPT_ALREADY_EXIST("101","该部门已经存在"),
	REFUSE_DELETEING_DEPT_COUSE_EMP_NOT_EMPTY("102","删除部门失败，由于部门中仍有员工存在"),
	/**
	 * 职位异常
	 */
	JOB_ALREADY_EXIST("201","该职位已经存在"),
	/**
	 * 员工异常
	 */
	EMP_ALREADY_EXIST("301","该员工已存在");
	;
	
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
