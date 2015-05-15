package com.dhsys.web.servlet;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dhsys.web.entity.TDept;
import com.dhsys.web.entity.TUser;
import com.dhsys.web.pagemodel.P_SysUser;
import com.dhsys.web.service.DeptService;
import com.dhsys.web.service.SysUserService;
import com.dhsys.web.utils.CommonsUtils;
import com.dhsys.web.utils.RespJson;
import com.dhsys.web.utils.SessionInfo;
 
 

@RequestMapping("/dhsys/sysuser")
@Controller
public class SysUserServlet {
    
	@Autowired
	private SysUserService userService;
	
	 
	//webservice服务声明
	//private DhService dhService = null;
	
	/**
	 * 系统用户登录
	 * @param req 页面请求对象
	 * @return
	 */
	@RequestMapping("/login.html")
	@ResponseBody
	public Map sysUserLogin(HttpServletRequest req){
		    //dhService = new DhService();
    	    //String str = URLDecoder.decode(req.getParameter("userInfo"),"UTF-8");  
		   SessionInfo sessionInfo = null;  
		   Map respMap = new HashMap();
		   String str = req.getParameter("userInfo");
	        //将json格式的字符串转换为json对象，并取得该对象的“userName”属性值  
	       JSONObject jsonObject = JSON.parseObject(str);
	       String userName = jsonObject.getString("userName");
	       String password = jsonObject.getString("password");
	       TUser user = userService.isExistsService(userName,password);
	       if(user!=null){
	    	   sessionInfo = new SessionInfo();
	    	   sessionInfo.setUserId(user.getUserId());
	           sessionInfo.setUserName(jsonObject.getString("userName"));
	           sessionInfo.setAdminFlag(Long.valueOf(user.getAdminFlag()));
	           sessionInfo.setDeptName(user.getDh_Dept().getDeptName());
	           sessionInfo.setDeptId(user.getDh_Dept().getDeptId());
	           req.getSession().setAttribute("sessionInfo", sessionInfo);
	           respMap.put("success",true);
		       respMap.put("failReason", "");
	       }else{
	    	   respMap.put("success",false);
		       respMap.put("failReason", "用户名或密码不正确");  
	       }
	           return respMap;
	}
	
	/**
	 * 系统用户页面初始化
	 * @return
	 */
	@RequestMapping("/initSysUser.html")
    public String initSysUser(){
		
		System.out.println("initSysUser");
		
		return "sys/sysuser/user";
		
	}
	
	/**
	 * 查询系统用户
	 * @return
	 */
	@RequestMapping("/findSysUsers.html")
	@ResponseBody
	public List<TUser> findSysUsers(HttpServletRequest req,P_SysUser user){
		String page = req.getParameter("page");
		String rows = req.getParameter("rows");
		List userList = userService.findSysUserService(page, rows, user); 
		return userList;
	}
	
	/**
	 * 删除系统用户
	 * @param req
	 * @return 
	 */
	@RequestMapping("/deleteUserById.html")
	@ResponseBody
	public Map deleteUserById(HttpServletRequest req){
		Map respMap = null;
		try{
	      String userId = req.getParameter("userId");
	      respMap = userService.deleteUserByIdService(userId);
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
		  return respMap;
	 }
	
	/**
	 * 释放系统用户下的角色信息
	 * @param req
	 * @return
	 */
	@RequestMapping("/releaseRole.html")
	@ResponseBody
	public Map releaseRole(HttpServletRequest req){
		Map respMap = null;
		try{
		  String userId = req.getParameter("userId");	
		  respMap = userService.releaseRoleService(userId);  	
		}catch(Exception e){
			e.printStackTrace();
		}
		return respMap;
	}
	
	/**
	 * 新增系统用户
	 * @param user
	 * @return
	 */
	@RequestMapping("/adduser.html")
	@ResponseBody
	public Map addUser(P_SysUser user){
		Map respMap = new HashMap();
		try{
		  
		 boolean addFlag = userService.addUserService(user);    	
		 if(addFlag){
		    respMap.put("success",true);	 
		    respMap.put("msg","添加成功");
		    respMap.put("reason", "");
		 }else{
		   respMap.put("success",false);	 
		   respMap.put("msg","添加失败");
		   respMap.put("reason", "Error");
 
		 }
		 
		}catch(Exception e){
			e.printStackTrace();
		}
		return respMap;
	}
	
	/**
	 * 新增初始化
	 * @param req
	 * @return
	 */
	@RequestMapping("/addInit.html")
	public String addInit(HttpServletRequest req){
		 String userIp = req.getRemoteAddr();
		 String nowDate = CommonsUtils.getNowDate(new Date());
		 req.setAttribute("nowDate", nowDate);
		 req.setAttribute("userIp", userIp);
		 return "sys/sysuser/adduser";
		
	}
	
	@RequestMapping("/updateInit.html")
	public String updateInit(HttpServletRequest req){
		 P_SysUser user = null;
		try{
		  String userId = req.getParameter("userId");	
		  user = userService.getUserByIdService(Integer.valueOf(userId));
		  req.setAttribute("user",user);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "sys/sysuser/edituser";
	}
	
	/**
	 * 修改用户
	 *  
	 * @return
	 */
	@RequestMapping("/updateuser.html")
	@ResponseBody
	public Map updateUser(P_SysUser user){
		Map respMap = new HashMap();
		try{
		    
			boolean updateFlag = userService.updateUserService(user);
		    if(updateFlag){
		    	respMap.put("msg","系统用户变更成功")	;
		        respMap.put("success",true);
		    }else{
		    	respMap.put("msg","系统用户变更失败")	;
		        respMap.put("success",false);
		    }
		}catch(Exception e){
			e.printStackTrace();
		}
		return respMap;
	}
	
	 
}
