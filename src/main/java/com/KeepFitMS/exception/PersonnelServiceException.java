package com.KeepFitMS.exception;

public class PersonnelServiceException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8274700501535446697L;
	/**
     * 错误代码
     */
    private String errorCode;

    /**
     * 构造一个基本异常.
     *
     * @param message 信息描述
     */
    public PersonnelServiceException(String message) {
        super(message);
    }

    /**
     * 构造一个基本异常.
     *
     * @param errorCode 错误编码
     * @param message   信息描述
     */
    public PersonnelServiceException(String errorCode, String message) {
    	super(message);
    	this.setErrorCode(errorCode);
    }
  

	/**
     * 构造一个基本异常.
     *
     * @param errorCode 错误编码
     * @param message   信息描述
     */
    public PersonnelServiceException(String errorCode, String message, Throwable cause) {
    	super(message, cause);
    	this.setErrorCode(errorCode);
    	
    }
    public String getErrorCode() {
  		return errorCode;
  	}

  	public void setErrorCode(String errorCode) {
  		this.errorCode = errorCode;
  	}
}
