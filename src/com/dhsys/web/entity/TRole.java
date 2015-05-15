package com.dhsys.web.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
/**
 * 系统角色
 * @author wangyuxiang
 *
 */
@Entity
@Table(name="DH_SYSROLE")
public class TRole implements java.io.Serializable{

	private Integer roleId;
	
	private String roleName;
	
	private String roleBak;
	
	private String createDate;
	
	private Integer seq; // 排序号
	
	private Set<TModule> moduleSet = new HashSet<TModule>(0);
	
	private Set<TUser> userSet = new HashSet<TUser>();
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ROLEID",nullable=false,unique=true)
	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Column(name="ROLENAME")
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(name="ROLEBAK")
	public String getRoleBak() {
		return roleBak;
	}

	public void setRoleBak(String roleBak) {
		this.roleBak = roleBak;
	}

	@Column(name="CREATEDATE")
	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "DH_ROLE_MODULE", joinColumns = { @JoinColumn(name = "roleid", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "moduleid", nullable = false, updatable = false) })
	public Set<TModule> getModuleSet() {
		return moduleSet;
	}

	public void setModuleSet(Set<TModule> moduleSet) {
		this.moduleSet = moduleSet;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "DH_USER_ROLE", joinColumns = { @JoinColumn(name = "roleid", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "userid", nullable = false, updatable = false) })
    public Set<TUser> getUserSet() {
		return userSet;
	}

	public void setUserSet(Set<TUser> userSet) {
		this.userSet = userSet;
	}

	@Column(name="SEQ")
	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public TRole(Integer roleId, String roleName, String roleBak,
			String createDate, Set<TModule> moduleSet, Set<TUser> userSet) {
	 
		this.roleId = roleId;
		this.roleName = roleName;
		this.roleBak = roleBak;
		this.createDate = createDate;
		this.moduleSet = moduleSet;
		this.userSet = userSet;
	}

	public TRole() {
		 
	}

	
	
	
}
