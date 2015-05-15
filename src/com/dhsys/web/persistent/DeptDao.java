package com.dhsys.web.persistent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.dhsys.web.entity.TDept;
import com.dhsys.web.entity.TModule;
import com.dhsys.web.entity.TUser;
import com.dhsys.web.pagemodel.P_Tree;

 
/**
 * 部门持久层
 *  
 * @author wangyuxiang
 *
 */
@Repository
public class DeptDao extends IHibernateDao<TModule>{

	/**
	 * 获取部门列表
	 * @return
	 */
	 public List<TDept> deptListDao(){
		try{
		   List deptList = this.getHibernateDao().find("from TDept d");	
		   if(deptList.size() > 0){
			return deptList;
		   }
		}catch(HibernateException e){
			e.printStackTrace();
		}
		return null;
	 }
	 
	 /**
	  * 根据部门编号加载部门实体
	  * @param deptId
	  * @return
	  */
	 public TDept getDeptByIdDao(Integer deptId){
		 try{
			TDept dept = this.getHibernateDao().get(TDept.class,deptId); 
			return dept; 
		 }catch(HibernateException e){
			 e.printStackTrace();
		 }
		return null;
	 }
	 
	 /**
	  * 查询部门 支持分页
	  * @param hql
	  * @param currentPage
	  * @param pageSize
	  * @return
	  */
	 public List<TUser> findDeptsDao(String hql, int currentPage, int pageSize) {
	    	List list = null;
			try {
				list = this.getHibernateDao().getSessionFactory().openSession()
						.createQuery(hql)
						.setFirstResult((currentPage - 1) * pageSize)
						.setMaxResults(pageSize).list();
			} catch (HibernateException e) {
				
				e.printStackTrace();
			}

			return list;
	}
	 
	 /**
	  * 新增部门信息
	  * @param dept
	  * @return
	  */
	 public boolean addDeptDao(TDept dept){
		 boolean addFlag = false;
		 try{
			 this.getHibernateDao().save(dept);
			 addFlag = true;
		 }catch(HibernateException e){
			 e.printStackTrace();
		 }
		  return addFlag;
		 
	 }
	 
	 /**
	  * 删除部门信息
	  * @param deptId
	  * @return
	  */
	 public boolean deleteDeptDao(String deptId){
		 boolean delFlag = false;
		 try{
			 TDept d = this.getHibernateDao().get(TDept.class, Integer.valueOf(deptId));
			 if(d!=null){
				 this.getHibernateDao().delete(d);
				 delFlag = true;
			 }
			
		 }catch(HibernateException e){
			 e.printStackTrace();
		 }
		return delFlag;
	 }
	 
	 /**
	  * 修改部门信息
	  * @param dept
	  * @return
	  */
	 public boolean updateDeptDao(TDept dept){
		boolean updateFlag = false;
		try{
			this.getHibernateDao().update(dept);
			updateFlag = true;
		}catch(HibernateException e){
			e.printStackTrace();
		}
		 return updateFlag;
		 
	 }
}
