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
import javax.persistence.OneToMany;
 
import javax.persistence.Table;

 

 

@Entity
@Table(name="DH_SYSMODULE",schema="dhsysdb.dbo")
public class TModule implements java.io.Serializable{

	 
	private static final long serialVersionUID = 1L;

	private Integer moduleId;
	
	private String moduleName;
	
	private String moduleIcon;
	
	private String modulePath;
	
	private String moduleDesc;
	
	private String moduleState;
	
	private String moduleType;
	
	private Set<TModule> modules = new HashSet<TModule>(0); 
	
	private Set<TRole> roles = new HashSet<TRole>(0);
	
	private TModule pModule;
	
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="MODULEID",nullable=false,unique=true)
	public Integer getModuleId() {
		return moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}
    
	@Column(name="MODULENAME")
	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	
	 

	@Column(name="MODULEICON")
	public String getModuleIcon() {
		return moduleIcon;
	}

	public void setModuleIcon(String moduleIcon) {
		this.moduleIcon = moduleIcon;
	}
	@Column(name="MODULEPATH")
	public String getModulePath() {
		return modulePath;
	}

	public void setModulePath(String modulePath) {
		this.modulePath = modulePath;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "modules")
	public Set<TModule> getModules() {
		return modules;
	}

	public void setModules(Set<TModule> modules) {
		this.modules = modules;
	}

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "PMODULEID")
	public TModule getpModule() {
		return pModule;
	}

	public void setpModule(TModule pModule) {
		this.pModule = pModule;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "DH_ROLE_MODULE", joinColumns = { @JoinColumn(name = "MODULEID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "ROLEID", nullable = false, updatable = false) })
    public Set<TRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<TRole> roles) {
		this.roles = roles;
	}
	
	@Column(name="MODULEDESC")
	public String getModuleDesc() {
		return moduleDesc;
	}

	public void setModuleDesc(String moduleDesc) {
		this.moduleDesc = moduleDesc;
	}

	@Column(name="MODULESTATE")
	public String getModuleState() {
		return moduleState;
	}

	public void setModuleState(String moduleState) {
		this.moduleState = moduleState;
	}

	@Column(name="MODULETYPE")
	public String getModuleType() {
		return moduleType;
	}

	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}

	
	public TModule(Integer moduleId, String moduleName, String moduleIcon,
			String modulePath, String moduleDesc, String moduleState,
			String moduleType, TModule pModule) {
		 
		this.moduleId = moduleId;
		this.moduleName = moduleName;
		this.moduleIcon = moduleIcon;
		this.modulePath = modulePath;
		this.moduleDesc = moduleDesc;
		this.moduleState = moduleState;
		this.moduleType = moduleType;
		this.pModule = pModule;
	}

	public TModule() {
		 
	}

	 
	 
	 
}
