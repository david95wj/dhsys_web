package com.dhsys.web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhsys.web.entity.TDept;
import com.dhsys.web.entity.TUser;
import com.dhsys.web.pagemodel.P_Dept;
import com.dhsys.web.pagemodel.P_SysUser;
import com.dhsys.web.persistent.DeptDao;
import com.dhsys.web.persistent.SysUserDao;

/**
 * 用户部门服务层
 * @author wangyuxiang
 *
 */
@Service
public class DeptService {

	@Autowired
	private DeptDao deptDao;
	
	/**
	 * 获取部门列表
	 * @return
	 */
	public List<TDept> deptListService(){
		return deptDao.deptListDao();
	}
	
	/**
	 * 加载部门实体
	 * @param deptId
	 * @return
	 */
	public P_Dept getDeptByIdService(Integer deptId){
		P_Dept p_Dept = new P_Dept();
		TDept dept = deptDao.getDeptByIdDao(deptId);
		BeanUtils.copyProperties(dept, p_Dept);
		
		return p_Dept;
	}
	
	/**
	 * 部门信息查询 支持分页查询
	 * @param page
	 * @param rows
	 * @param dept
	 * @return
	 */
	public List findDeptsService(String page, String rows, P_Dept dept){
		List deptList = new ArrayList();
	    List resultList = new ArrayList();
		try{
		  String hql = "from TDept d where 1 = 1";	
		  if(dept!=null){
			  if(dept.getDeptNo()!=null&&!dept.getDeptNo().equals("")){
				  hql += " and d.deptNo = '" + dept.getDeptNo() + "'"; 
			  }
			  if(dept.getDeptName()!=null&&!dept.getDeptName().equals("")){
				  hql += " and d.deptName = '" + dept.getDeptName() + "'"; 
			  }
			  if(dept.getCreateDate()!=null&&!dept.getCreateDate().equals("")){
				  hql += " and d.createDate = '" + dept.getCreateDate() + "'"; 
			  }
			  hql += " order by d.deptId";
			  int currentPage = Integer
						.parseInt((page == null || page == "0") ? "1" : page); 
			  int pageSize = Integer
						.parseInt((rows == null || rows == "0") ? "10" : rows);
			  deptList = deptDao.findDeptsDao(hql, currentPage, pageSize);
			  if(deptList.size() > 0){
				  P_Dept p_Dept = null;
				  for(int i=0;i<deptList.size();i++){
					p_Dept = new P_Dept();
					TDept d = (TDept) deptList.get(i);
					p_Dept.setDeptId(d.getDeptId());
					p_Dept.setDeptName(d.getDeptName());
					p_Dept.setDeptNo(d.getDeptNo());
					p_Dept.setCreateDate(d.getCreateDate());
					resultList.add(p_Dept);
				  }
			  }
		  }
		}catch(Exception e){
			e.printStackTrace();
		}
		return resultList;
		
	}
	
	/**
	 * 新增部门信息
	 * @param dept
	 * @return
	 */
	public boolean addDeptService(P_Dept p_Dept){
		boolean addFlag = false;
		try{
			TDept dept = new TDept();
		    BeanUtils.copyProperties(p_Dept, dept);	
		    addFlag = deptDao.addDeptDao(dept);
		}catch(Exception e){
			e.printStackTrace();
		}
		return addFlag;
		
	}
	
	/**
	 * 删除部门信息
	 * @param deptId
	 * @return
	 */
	public boolean deleteDeptService(String deptId){
		 return deptDao.deleteDeptDao(deptId);
	}
	
	public boolean updateDeptService(P_Dept dept){
		 TDept d = new TDept();
		 BeanUtils.copyProperties(dept, d);
		 return deptDao.updateDeptDao(d);
	}
	
}
