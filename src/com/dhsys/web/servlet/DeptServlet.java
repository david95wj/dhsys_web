package com.dhsys.web.servlet;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dhsys.web.entity.TDept;
import com.dhsys.web.entity.TUser;
import com.dhsys.web.pagemodel.P_Dept;
import com.dhsys.web.pagemodel.P_SysUser;
import com.dhsys.web.service.DeptService;
import com.dhsys.web.service.SysUserService;
import com.dhsys.web.utils.RespJson;
import com.dhsys.web.utils.SessionInfo;
 
 

@RequestMapping("/dhsys/dept")
@Controller
public class DeptServlet {
    
	@Autowired
	private DeptService deptService;
	
	//webservice服务声明
	//private DhService dhService = null;
	
	/**
	 * 获取部门列表
	 * @return
	 */
	@RequestMapping("/deptList.html")
	@ResponseBody
	public List<TDept> deptList(){
		
		List deptList = deptService.deptListService();
		return deptList;
		
	}
	
	/**
	 * 部门页面初始化
	 * @return
	 */
	@RequestMapping("/initDept.html")
    public String initDept(){
		
		 return "sys/dept/dept";
		
	}
	
	/**
	 * 查询部门信息
	 * @return
	 */
	@RequestMapping("/findDepts.html")
	@ResponseBody
	public List<TDept> findDepts(HttpServletRequest req,P_Dept dept){
		String page = req.getParameter("page");
		String rows = req.getParameter("rows");
		List deptList = deptService.findDeptsService(page, rows, dept); 
		return deptList;
	}
	
	/**
	 * 新增初始化
	 * @return
	 */
	@RequestMapping("/addInit.html")
	public String addInit(){
		try{
		  	
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return  "sys/dept/adddept";
	}
	
	@RequestMapping("/adddept.html")
	@ResponseBody
	public Map addDept(P_Dept p_Dept) throws UnsupportedEncodingException{
		 
		Map respMap = new HashMap();
		try{
		  boolean flag = deptService.addDeptService(p_Dept);
		  if(flag){
			  respMap.put("msg","新增部门成功");
			  respMap.put("success",true);
		  }else{
			  respMap.put("msg","新增部门失败");
			  respMap.put("success",false);
		  }
		}catch(Exception e){
			e.printStackTrace();
		}
		return respMap;
	}
	
	@RequestMapping("/deleteDept.html")
	@ResponseBody
	public Map deleteDept(HttpServletRequest req){
		Map respMap = new HashMap();
		try{
		 String deptId = req.getParameter("deptId");
		 boolean flag = deptService.deleteDeptService(deptId);
		 if(flag){
			 respMap.put("msg","部门信息删除成功");
			 respMap.put("success",true);
		 }else{
			 respMap.put("msg","部门信息删除失败");
			 respMap.put("success",false); 
		 }
		}catch(Exception e){
			e.printStackTrace();
		}
		return respMap;
	}
	
	/**
	 * 修改初始化
	 * @return
	 */
	@RequestMapping("/updateInit.html")
	public String updateInit(HttpServletRequest req){
		try{
			String deptId = req.getParameter("deptId");
			P_Dept d = deptService.getDeptByIdService(Integer.valueOf(deptId)); 	
			req.setAttribute("d", d);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "sys/dept/editdept";
		
	}
	
	@RequestMapping("/updatedept.html")
	@ResponseBody
	public Map updateDept(P_Dept dept){
		Map respMap = new HashMap();
		try{
		  boolean flag = deptService.updateDeptService(dept);
		  if(flag){
				 respMap.put("msg","部门信息变更成功");
				 respMap.put("success",true);
			 }else{
				 respMap.put("msg","部门信息变更失败");
				 respMap.put("success",false); 
			 }
		}catch(Exception e){
			e.printStackTrace();
		}
		return respMap;
	}
	
	 
}
