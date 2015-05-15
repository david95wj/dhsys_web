package com.dhsys.web.pagemodel;

import java.util.ArrayList;
import java.util.List;

public class P_DataGrid implements java.io.Serializable{

	private Integer total;
	
	private List dataList = new ArrayList();

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List getDataList() {
		return dataList;
	}

	public void setDataList(List dataList) {
		this.dataList = dataList;
	}

	public P_DataGrid(Integer total, List dataList) {
		 
		this.total = total;
		this.dataList = dataList;
	}

	public P_DataGrid() {
		 
	}
	
	
}
