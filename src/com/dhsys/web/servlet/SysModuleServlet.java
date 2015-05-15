package com.dhsys.web.servlet;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dhsys.web.entity.TUser;
import com.dhsys.web.pagemodel.P_SysUser;
import com.dhsys.web.service.SysUserService;
import com.dhsys.web.utils.RespJson;
import com.dhsys.web.utils.SessionInfo;
 
 

@RequestMapping("/dhsys/module")
@Controller
public class SysModuleServlet {
    
	 
	//webservice服务声明
	//private DhService dhService = null;
	
	 
}
