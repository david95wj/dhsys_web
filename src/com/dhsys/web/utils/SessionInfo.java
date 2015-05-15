package com.dhsys.web.utils;

import java.util.List;

public class SessionInfo {

	private Integer userId;// 用户ID
	
	private String userName;// 登录名
	
	private String name;// 姓名
	
	private String ip;// 用户IP
	
	private String pwd;
	
	//取值范围 1:系统管理员，2：非系统管理员
	private long adminFlag;
	
	private String deptName;
	
	private Integer deptId;
	
    private List<String> moduleList;// 用户可以访问的资源地址列表
	
	private List<String> moduleAllList;
	
	private String nowDate;
	
	

	public String getNowDate() {
		return nowDate;
	}

	public void setNowDate(String nowDate) {
		this.nowDate = nowDate;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public long getAdminFlag() {
		return adminFlag;
	}

	public void setAdminFlag(long adminFlag) {
		this.adminFlag = adminFlag;
	}

	public List<String> getModuleList() {
		return moduleList;
	}

	public void setModuleList(List<String> moduleList) {
		this.moduleList = moduleList;
	}

	public List<String> getModuleAllList() {
		return moduleAllList;
	}

	public void setModuleAllList(List<String> moduleAllList) {
		this.moduleAllList = moduleAllList;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

    
	 
}
