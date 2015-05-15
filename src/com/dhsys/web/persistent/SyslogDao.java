package com.dhsys.web.persistent;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import com.dhsys.web.entity.TDept;
import com.dhsys.web.entity.TModule;
import com.dhsys.web.entity.TSyslog;

 
/**
 * 系统日志持久层
 *  
 * @author wangyuxiang
 *
 */
@Repository
public class SyslogDao extends IHibernateDao<TModule>{

	/**
	 * 获取系统日志列表
	 * @return
	 */
	 public List<TSyslog> syslogListDao(){
		try{
		   List syslogList = this.getHibernateDao().find("from TSyslog d");	
		   if(syslogList.size() > 0){
			return syslogList;
		   }
		}catch(HibernateException e){
			e.printStackTrace();
		}
		return null;
	 }
	 
	 /**
	  * 根据系统日志编号加载系统日志实体
	  * @param syslogId
	  * @return
	  */
	 public TSyslog getSyslogByIdDao(Long syslogId){
		 try{
			 TSyslog syslog = this.getHibernateDao().get(TSyslog.class,syslogId); 
			return syslog; 
		 }catch(HibernateException e){
			 e.printStackTrace();
		 }
		return null;
	 }
	 
	 /**
	  * 查询系统日志 支持分页
	  * @param hql
	  * @param currentPage
	  * @param pageSize
	  * @return
	  */
	 public List<TSyslog> findSyslogsDao(String hql, int currentPage, int pageSize) {
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
	  * 新增系统日志信息
	  * @param syslog
	  * @return
	  */
	 public boolean addSyslogDao(TSyslog syslog){
		 boolean addFlag = false;
		 try{
			 this.getHibernateDao().save(syslog);
			 addFlag = true;
		 }catch(HibernateException e){
			 e.printStackTrace();
		 }
		  return addFlag;
		 
	 }

	 public boolean updateSyslogDao(TSyslog syslog){
			boolean updateFlag = false;
			try{
				this.getHibernateDao().update(syslog);
				updateFlag = true;
			}catch(HibernateException e){
				e.printStackTrace();
			}
			 return updateFlag;
			 
		 }

}
