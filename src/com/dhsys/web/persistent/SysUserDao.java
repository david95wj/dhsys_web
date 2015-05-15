package com.dhsys.web.persistent;

 
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
 

import com.dhsys.web.entity.TRole;
import com.dhsys.web.entity.TUser;
import com.dhsys.web.pagemodel.P_SysUser;
 
 
 
@Repository
public class SysUserDao extends IHibernateDao<TUser>{

	/**
	 * 查询系统用户唯一性
	 * @param userName
	 * @param password
	 * @return
	 */
	public TUser isExistsDao(String userName,String password){
		TUser user = null;
		try{
		  List list = this.getHibernateDao().find("From TUser tuser where tuser.userAccount = " + "'" + userName + "'" + " and tuser.password = " + "'" + password + "'");
		  if(list.size() > 0){
			 user = (TUser) list.get(0);
		  }
		}catch(HibernateException e){
			e.printStackTrace();
		}
		
		return user;
	}
	
	 /**
	  * 查询系统用户 分页
	  * @param hql
	  * @param currentPage
	  * @param pageSize
	  * @return
	  */
	 public List<TUser> findUsersDao(String hql, int currentPage, int pageSize) {
	    	List list = null;
			try {
				list = this.getHibernateDao().getSessionFactory().openSession()
						.createSQLQuery(hql)
						.setFirstResult((currentPage - 1) * pageSize)
						.setMaxResults(pageSize).list();
			} catch (HibernateException e) {
				
				e.printStackTrace();
			}

			return list;
	}
	 
	 /**
	  * 删除系统用户
	  * @param userId
	  * @return
	  */
	 public boolean deleteUserByIdDao(String userId){
		 boolean delFlag = true;
		 try{
		   TUser user = this.getHibernateDao().get(TUser.class,Integer.valueOf(userId));	 
		   if(!user.getRoles().isEmpty()){
			   delFlag = false;
		   }else{
			   this.getHibernateDao().delete(user);
		   }
		 }catch(HibernateException e){
			 e.printStackTrace();
		 }
		return delFlag;
	 }
	 
	 /**
	  * 使用系统用户的角色
	  * @param userId
	  * @return
	  */
	 public boolean releaseRoleDao(String userId){
		 boolean releaseFlag = false;
		 try{
		 /* Query q = this.getHibernateDao().getSessionFactory().openSession().createSQLQuery("select userid from dh_user_role where userid = " + Integer.valueOf(userId));
		  if(q.list().size() > 0){
			  
		  }*/
		  int flag = this.getHibernateDao().getSessionFactory().openSession().createSQLQuery("delete from dh_user_role where dh_user_role.userid = " + Integer.valueOf(userId)).executeUpdate();
		  if(flag==1){
			  releaseFlag = true;
		  }
		 }catch(HibernateException e){
			 e.printStackTrace();
		 }
		return releaseFlag;
	 }
	 
	 /**
	  * 新增系统用户
	  * @param u
	  * @return
	  */
	 public boolean addUserDao(TUser u){
		boolean addFlag = false;
		try{
			this.getHibernateDao().save(u);
			addFlag = true;
		}catch(HibernateException e){
			e.printStackTrace();
		}
		  return addFlag;
		 
	 }
	 
	 /**
	  * 加载系统用户实体
	  * @param userId
	  * @return
	  */
	 public TUser getUserByIdDao(Integer userId){
		 TUser user = null;
		 try{
		      user = this.get(userId);
		      System.out.println(user.getUserId());
		   }catch(HibernateException e){
				e.printStackTrace();
			}
			return user;
	 }
	 
	 /**
	  * 更新系统用户
	  * @param u
	  * @return
	  */
	 public boolean updateUserDao(TUser u){
		 boolean updateFlag = false;
		 try{
			 this.getHibernateDao().update(u);
			 updateFlag = true;
		 }catch(HibernateException e){
			 e.printStackTrace();
		 }
		return updateFlag;
	 }
	
	 
}
