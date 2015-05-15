package com.dhsys.web.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



/**
 * 系统用户
 * @author wangyuxiang
 * 
 */
@Entity
@Table(name="DH_SYSUSER")
public class TUser implements java.io.Serializable{

	private Integer userId;
	
	private String userAccount;
	
	private String password;
	
	private String adminFlag;
	
	private String createDate;
	
	private String userIp;
	
	private String userBak;
	
	private TDept dh_Dept;
	
	private Set<TRole> roles = new HashSet<TRole>(0);
	
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="USERID",nullable=false,unique=true)
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
    
	 
	@Column(name="USERACCOUNT")
	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	@Column(name="PASSWORD")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name="CREATEDATE")
	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	@Column(name="USERBAK")
	public String getUserBak() {
		return userBak;
	}

	public void setUserBak(String userBak) {
		this.userBak = userBak;
	}

	@Column(name="ADMINFLAG")
	public String getAdminFlag() {
		return adminFlag;
	}

	public void setAdminFlag(String adminFlag) {
		this.adminFlag = adminFlag;
	}

	
	 @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	 @JoinColumn(name = "DEPTID")
     public TDept getDh_Dept() {
		return dh_Dept;
	 }

	public void setDh_Dept(TDept dh_Dept) {
		this.dh_Dept = dh_Dept;
	}
	
	
	@Column(name="USERIP")
	public String getUserIp() {
		return userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "DH_USER_ROLE", joinColumns = { @JoinColumn(name = "USERID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "ROLEID", nullable = false, updatable = false) })
	public Set<TRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<TRole> roles) {
		this.roles = roles;
	}

	/**
	 * 默认构造方法
	 */
	public TUser() {
		 
	}

	public TUser(Integer userId, String userAccount, String password,
			String adminFlag, String createDate, String userIp, String userBak,
			TDept dh_Dept, Set<TRole> roles) {
		super();
		this.userId = userId;
		this.userAccount = userAccount;
		this.password = password;
		this.adminFlag = adminFlag;
		this.createDate = createDate;
		this.userIp = userIp;
		this.userBak = userBak;
		this.dh_Dept = dh_Dept;
		this.roles = roles;
	}
	
	 
	
}
