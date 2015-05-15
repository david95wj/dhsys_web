package com.dhsys.web.persistent;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import com.dhsys.web.entity.TRole;
import com.dhsys.web.entity.TUser;
 
@Repository
public class SysRoleDao extends IHibernateDao<TRole>{

	/**
	 * 获取角色列表
	 * @return
	 */
	public List<TRole> RoleListDao(){
		List<TRole> list = null;
		try{
           list = this.getHibernateDao().find("from TRole");   
			
		}catch(HibernateException e){
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 加载角色信息
	 * @param roleId
	 * @return
	 */
	public TRole getRoleById(Integer roleId ){
		TRole role = new TRole();
		try{
		 role = this.get(roleId);	
			
		}catch(HibernateException e){
			e.printStackTrace();
		}
		return role;
	}
	
	 public List<TRole> findRolesDao(String hql, int currentPage, int pageSize) {
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
	 
	 public boolean addRoleDao(TRole role){
		 boolean addFlag = false;
		 try{
			 this.getHibernateDao().save(role);
			 addFlag = true;
		 }catch(HibernateException e){
			 e.printStackTrace();
		 }
		return addFlag;
	 }
}
