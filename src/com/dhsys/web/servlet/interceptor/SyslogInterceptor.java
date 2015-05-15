package com.dhsys.web.servlet.interceptor;


import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.dhsys.web.entity.TSyslog;
import com.dhsys.web.persistent.SyslogDao;
import com.dhsys.web.service.SyslogService;
import com.dhsys.web.utils.SessionInfo;

public class SyslogInterceptor implements HandlerInterceptor {

	@Autowired
	private SyslogService syslogService;
	
	private Logger log = Logger.getLogger(SyslogInterceptor.class);
	
	public SyslogInterceptor() {
		// TODO Auto-generated constructor stub
	}

	private String mappingURL;//利用正则映射到需要拦截的路径  
	private String ignoreURLs;//忽略的url
    public void setMappingURL(String mappingURL) {  
           this.mappingURL = mappingURL;  
    }
    
    
	public void setIgnoreURLs(String ignoreURLs) {
		this.ignoreURLs = ignoreURLs;
	}


	/**
	 * 在业务处理器处理请求之前被调用
	 * 如果返回false
	 *     从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
	 * 
	 * 如果返回true
	 *    执行下一个拦截器,直到所有的拦截器都执行完毕
	 *    再执行被拦截的Controller
	 *    然后进入拦截器链,
	 *    从最后一个拦截器往回执行所有的postHandle()
	 *    接着再从最后一个拦截器往回执行所有的afterCompletion()
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		String url=request.getRequestURL().toString(); 
		String moduleName = request.getServletPath();
		long startTime = System.currentTimeMillis();
		//System.out.println("reqeust begin time:"+startTime);
		request.setAttribute("startTime",startTime);
		if (ignoreURLs.contains(moduleName)){
			return true;
		}
        /*if(mappingURL==null || url.matches(mappingURL)){  
        	request.getRequestDispatcher("/dhsys/sysInit.html").forward(request, response);
			return true; 
        }*/
        //获取session信息获取用户信息,如果项目没配filter也可能过此作权限校验.
		Object sessionObj = request.getSession().getAttribute("sessionInfo");
		if (sessionObj!=null){
			SessionInfo sessionInfo = (SessionInfo)sessionObj;//获取用户信息
			//写日志
	        TSyslog log = new TSyslog();
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        log.setModuleName(moduleName);
	        log.setType("1");
	        log.setUserAccount(sessionInfo.getUserName());
	        log.setActiveDate(sdf.format(new Date()));
	        log.setLogBak("");
	        log.setStartTime(System.currentTimeMillis());
	        syslogService.addSyslogService(log);
	        request.setAttribute("logId", log.getLogId());
		}else{
			//跳至登录页面
			request.getRequestDispatcher("/dhsys/sysInit.html").forward(request, response);
			return false; 
		}
		return true;
	}

	//在业务处理器处理请求执行完成后,生成视图之前执行的动作 
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		
		
	}

	/**
	 * 在DispatcherServlet完全处理完请求后被调用 
	 * 
	 *   当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		Object obj = request.getAttribute("logId");
		if (obj!=null){
			long logId = (Long)obj;
			//更新结束时间
			Long endTime = System.currentTimeMillis();
			TSyslog syslog = syslogService.getSyslogByIdService(logId);
			syslog.setEndTime(endTime);
			syslog.setCostTime(endTime - syslog.getStartTime());
			syslogService.updateSyslogService(syslog);
			//System.out.println("reqeust cost time:"+ (System.currentTimeMillis() - startTime));
		}
	}

}

