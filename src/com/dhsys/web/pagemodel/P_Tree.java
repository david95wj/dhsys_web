package com.dhsys.web.pagemodel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

 
/**
 * @date 2015-04-17
 * @author wangyuxiang
 * @category 模块树封装类
 */
public class P_Tree implements java.io.Serializable{

	private Integer id;
	
	private String text;
	
	private String state = "open";// open,closed
	
	private boolean checked = false;
	
    private List<P_Tree> children;
	
	private String iconCls;
	
	private Integer pid;
	
	private String url;
	
	private Object attributes;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public List<P_Tree> getChildren() {
		return children;
	}

	public void setChildren(List<P_Tree> children) {
		this.children = children;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Object getAttributes() {
		return attributes;
	}

	public void setAttributes(Object attributes) {
		this.attributes = attributes;
	}

	public P_Tree(Integer id, String text, String state, boolean checked,
			List<P_Tree> children, String iconCls, Integer pid, String url,
			Object attributes) {
		 
		this.id = id;
		this.text = text;
		this.state = state;
		this.checked = checked;
		this.children = children;
		this.iconCls = iconCls;
		this.pid = pid;
		this.url = url;
		this.attributes = attributes;
	}

	public P_Tree() {
		 
	}
    
	 
}
