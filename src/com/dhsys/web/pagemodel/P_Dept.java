package com.dhsys.web.pagemodel;

 

public class P_Dept implements java.io.Serializable{

   
    private Integer deptId;
	
	private String deptNo;
	
	private String deptName;

	private String createDate;

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public String getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public P_Dept(Integer deptId, String deptNo, String deptName,
			String createDate) {
	 
		this.deptId = deptId;
		this.deptNo = deptNo;
		this.deptName = deptName;
		this.createDate = createDate;
	}

	public P_Dept() {
	 
	}
	
	
	
}
