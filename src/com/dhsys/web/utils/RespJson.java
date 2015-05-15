package com.dhsys.web.utils;

/**
 * 消息返回格式封装
 * @author wangyuxiang
 * @category
 * @date 2015-04-17
 */
public class RespJson implements java.io.Serializable{

	private boolean success = false;
	
	private String msg;
	
	private String failReason;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getFailReason() {
		return failReason;
	}

	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}
	
	
}
