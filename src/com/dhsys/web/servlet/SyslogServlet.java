package com.dhsys.web.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dhsys.web.entity.TSyslog;
import com.dhsys.web.service.SyslogService;
 
 

@RequestMapping("/dhsys/syslog")
@Controller
public class SyslogServlet {
    
	@Autowired
	private SyslogService syslogService;
	
	/**
	 * 获取系统日志列表
	 * @return
	 */
	@RequestMapping("/syslogList.html")
	@ResponseBody
	public List<TSyslog> syslogList(){
		List syslogList = syslogService.syslogListService();
		return syslogList;
		
	}
	
	/**
	 * 系统日志页面初始化
	 * @return
	 */
	@RequestMapping("/initSyslog.html")
    public String initSyslog(){
		
		 return "sys/syslog/syslog";
		
	}
	
	/**
	 * 查询系统日志信息
	 * @return
	 */
	@RequestMapping("/findSyslogs.html")
	@ResponseBody
	public List<TSyslog> findSyslogs(HttpServletRequest req,TSyslog syslog){
		String page = req.getParameter("page");
		String rows = req.getParameter("rows");
		List syslogList = syslogService.findSyslogsService(page, rows, syslog); 
		return syslogList;
	}
}
