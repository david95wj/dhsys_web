package com.dhsys.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhsys.web.entity.TModule;
import com.dhsys.web.persistent.SysModuleDao;

/**
 * 系统模块服务层
 * @author wangyuxiang
 *
 */
@Service
public class ModuleService {

	@Autowired
	private SysModuleDao moduleDao;
	/**
	 * 根据用户类别加载模块列表
	 * @return
	 */
	public List getModuleTreeService(long adminFlag){
		
	    //管理员加载所有模块列表
	    return moduleDao.getModuleTree(adminFlag);
	 }
	
	 
}
