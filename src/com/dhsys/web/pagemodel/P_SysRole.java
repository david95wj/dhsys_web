package com.dhsys.web.pagemodel;

public class P_SysRole implements java.io.Serializable{

	private Integer roleId;
	
	private String roleName;
	
	private String roleBak;
	
	private String createDate;
	
	private Integer seq; // 排序号
	
	private String moduleIds;
	
	private String moduleNames;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleBak() {
		return roleBak;
	}

	public void setRoleBak(String roleBak) {
		this.roleBak = roleBak;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getModuleIds() {
		return moduleIds;
	}

	public void setModuleIds(String moduleIds) {
		this.moduleIds = moduleIds;
	}

	public String getModuleNames() {
		return moduleNames;
	}

	public void setModuleNames(String moduleNames) {
		this.moduleNames = moduleNames;
	}
	
	
}
