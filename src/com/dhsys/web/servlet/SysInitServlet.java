package com.dhsys.web.servlet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dhsys.web.service.ModuleService;
import com.dhsys.web.utils.GlobalConstant;
import com.dhsys.web.utils.SessionInfo;
/**
 * 系统/模块初始化	
 * @author wangyuxiang
 *
 */
@Controller
@RequestMapping("/dhsys")
public class SysInitServlet {

	@Autowired
	private ModuleService moduleService;
	
	@RequestMapping("/sysInit")
	public String init(HttpServletRequest request) {
		SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute(GlobalConstant.SESSION_INFO);
		if ((sessionInfo != null) && (sessionInfo.getUserId() != null)) {
			return "/main";
		}
		return "/login";
	}
	
	
	@RequestMapping("/main.html")
	@ResponseBody
	public List sysInit(HttpServletRequest req){
		List modueTree = null;
		try{
	    
		  //组织该用户所拥有的模块资源树
	      //获取session中的相关用户信息 
	     //判断当前账号中是否包含管理员标志
		 SessionInfo sessionInfo = (SessionInfo) req.getSession().getAttribute("sessionInfo");
		 modueTree = moduleService.getModuleTreeService(sessionInfo.getAdminFlag());
		 sessionInfo.setModuleList(modueTree);
		 sessionInfo.setAdminFlag(sessionInfo.getAdminFlag());
		 req.getSession().setAttribute(GlobalConstant.SESSION_INFO, sessionInfo);
		 
		 
		}catch(Exception e){
			e.printStackTrace();
		}
		return modueTree;
	}
	
	 
}
