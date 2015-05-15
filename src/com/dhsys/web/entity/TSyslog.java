package com.dhsys.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="DH_SYSLOG")
public class TSyslog implements java.io.Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long logId;
	
	private String userAccount;
	
	private String moduleName;

	private String type;
	
	private String activeDate;
	
	private String logBak;
	
	private Long startTime;
	
	private Long endTime;
	
	private Long costTime;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="LOGID",nullable=false,unique=true)
	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}
	
	@Column(name="USERACCOUNT",nullable=false)
	public String getUserAccount() {
		return userAccount;
	}

	

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	@Column(name="MODULENAME",nullable=false)
	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	@Column(name="TYPE",nullable=false)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name="ACTIVEDATE",nullable=false)
	public String getActiveDate() {
		return activeDate;
	}

	public void setActiveDate(String activeDate) {
		this.activeDate = activeDate;
	}
	@Column(name="LOGBAK",nullable=false)
	public String getLogBak() {
		return logBak;
	}

	public void setLogBak(String logBak) {
		this.logBak = logBak;
	}
	@Column(name="STARTTIME",nullable=false)
	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}
	@Column(name="ENDTIME",nullable=true)
	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}
	@Column(name="COSTTIME",nullable=true)
	public Long getCostTime() {
		return costTime;
	}

	public void setCostTime(Long costTime) {
		this.costTime = costTime;
	}
	
}
