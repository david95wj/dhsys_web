package com.dhsys.web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhsys.web.entity.TDept;
import com.dhsys.web.entity.TSyslog;
import com.dhsys.web.pagemodel.P_Dept;
import com.dhsys.web.persistent.SyslogDao;

/**
 * 系统日志服务层
 * @author wangyuxiang
 *
 */
@Service
public class SyslogService {

	@Autowired
	private SyslogDao syslogDao;
	
	/**
	 * 获取系统日志列表
	 * @return
	 */
	public List<TSyslog> syslogListService(){
		return syslogDao.syslogListDao();
	}
	
	/**
	 * 加载系统日志实体
	 * @param syslogId
	 * @return
	 */
	public TSyslog getSyslogByIdService(Long syslogId){
		TSyslog syslog = syslogDao.getSyslogByIdDao(syslogId);
		return syslog;
	}
	
	/**
	 * 系统日志信息查询 支持分页查询
	 * @param page
	 * @param rows
	 * @param syslog
	 * @return
	 */
	public List findSyslogsService(String page, String rows, TSyslog syslog){
		
		List syslogList = new ArrayList();
		try{
		  String hql = "from TSyslog d where 1 = 1";	
		  if(syslog!=null){
			  if(syslog.getUserAccount()!=null&&!syslog.getUserAccount().equals("")){
				  hql += " and d.userAccount = '" + syslog.getUserAccount() + "'"; 
			  }
			  if(syslog.getModuleName()!=null&&!syslog.getModuleName().equals("")){
				  hql += " and d.moduleName = '" + syslog.getModuleName() + "'"; 
			  }
			  if(syslog.getActiveDate()!=null&&!syslog.getActiveDate().equals("")){
				  hql += " and SUBSTRING(d.activeDate,0,11) = '" + syslog.getActiveDate() + "'"; 
			  }
			  hql += " order by d.logId";
			  int currentPage = Integer
						.parseInt((page == null || page == "0") ? "1" : page); 
			  int pageSize = Integer
						.parseInt((rows == null || rows == "0") ? "10" : rows);
			  syslogList = syslogDao.findSyslogsDao(hql, currentPage, pageSize);
		  }
		}catch(Exception e){
			e.printStackTrace();
		}
		return syslogList;
	}
	
	/**
	 * 新增系统信息
	 * @param dept
	 * @return
	 */
	public boolean addSyslogService(TSyslog syslog){
		boolean addFlag = false;
		try{
		    addFlag = syslogDao.addSyslogDao(syslog);
		}catch(Exception e){
			e.printStackTrace();
		}
		return addFlag;
		
	}
	public boolean updateSyslogService(TSyslog syslog){
		 return syslogDao.updateSyslogDao(syslog);
	}
}
