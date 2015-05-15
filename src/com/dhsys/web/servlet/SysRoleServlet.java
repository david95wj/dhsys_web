package com.dhsys.web.servlet;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dhsys.web.entity.TRole;
import com.dhsys.web.entity.TUser;
import com.dhsys.web.pagemodel.P_SysRole;
import com.dhsys.web.pagemodel.P_SysUser;
import com.dhsys.web.service.SysRoleService;
import com.dhsys.web.service.SysUserService;
import com.dhsys.web.utils.CommonsUtils;
import com.dhsys.web.utils.RespJson;
import com.dhsys.web.utils.SessionInfo;
 
 

@RequestMapping("/dhsys/sysrole")
@Controller
public class SysRoleServlet {
    
	 @Autowired
	 private SysRoleService sysRoleService;
	 
	 /**
	  * 获取角色信息列表
	  * @return
	  */
	 @RequestMapping("/roleList.html")
	 @ResponseBody
	 public List<TRole> roleList(){
	     List list = null;
		 try{
			 list = sysRoleService.roleListService();
		 }catch(HibernateException e){
			 e.printStackTrace();
		 }
		return list;
	 }
	 
	 /**
	  * 查询系统角色
	  * @param req
	  * @param role
	  * @return
	  */
	 @RequestMapping("/findSysRoles.html")
	 @ResponseBody
	 public List<TRole> findSysRoles(HttpServletRequest req,P_SysRole role){
		 List roleList = null;
		 try{
			 String page = req.getParameter("page");
			 String rows = req.getParameter("rows");
			 roleList = sysRoleService.findSysRoleService(page, rows, role); 
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		return roleList;
	 }
	 
	   /**
		 * 系统角色页面初始化
		 * @return
		 */
		@RequestMapping("/initSysRole.html")
	    public String initSysUser(){
			
		   return "sys/sysrole/role";
			
		}
		
		/**
		 * 新增初始化
		 * @param req
		 * @return
		 */
		@RequestMapping("/addInit.html")
		public String addInit(HttpServletRequest req){
			 String nowDate = CommonsUtils.getNowDate(new Date());
			 req.setAttribute("nowDate", nowDate);
			 return "sys/sysrole/addrole";
		   	
		}
		
		/**
		 * 新增系统角色
		 * @param role
		 * @return
		 */
		@RequestMapping("/addrole.html")
		@ResponseBody
		public Map addRole(P_SysRole role){
			Map respMap = new HashMap();
			try{
				//sysRoleService.	
			}catch(Exception e){
				e.printStackTrace();
			}
			return respMap;
		}
		
		/**
		 * 获取应用模块列表
		 * @return
		 */
		@RequestMapping("/moduleList.html")
		@ResponseBody
		public List moduleList(){
			
			List moduleList = sysRoleService.getModuleListService();
			return moduleList;
			
		}
}
