package com.dhsys.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="DH_DEPT")
public class TDept implements java.io.Serializable{

	
	private Integer deptId;
	
	private String deptNo;
	
	private String deptName;

	private String createDate;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="DEPTID",nullable=false,unique=true)
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

	public TDept(Integer deptId, String deptNo, String deptName,
			String createDate) {
		 
		this.deptId = deptId;
		this.deptNo = deptNo;
		this.deptName = deptName;
		this.createDate = createDate;
	}

	public TDept() {
		  
	}

	 
	
}
