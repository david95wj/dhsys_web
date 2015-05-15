<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<% 
  String path = request.getContextPath();
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>大华售后业务平台</title>
<link rel="stylesheet" type="text/css" href="<%=path %>/jslib/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/jslib/easyui/themes/icon.css">
<script type="text/javascript" src="<%=path %>/jslib/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=path %>/jslib/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
$(function(){
  
	$("#loginBtn").click(function(){
	  var userName = encodeURI($("#userName").val());
	  var password = encodeURI($("#password").val());
	  var userInfo = {userName:userName,password:password};
	  var params  = encodeURI(JSON.stringify(userInfo));  
	  $.ajax(
		   {
		     url:'<%=path%>/dhsys/sysuser/login.html',
		     type:'POST',
		     dataTyp:'json',
		     //contentType:'application/json',
		     data:"userInfo="+params,
		     error:function(){
		    	 alert('Error loading parameter');
		     },
             success:function(respData){
            	var result = eval('(' + respData + ')');  
            	 if(result.success){
            		 alert("success!");
            		 window.location.href = "<%=path%>/dhsys/sysInit.html";
            	}else{
            		$.messager.show({
            			title:'系统提示',
            			msg:'用户名或密码错误',
            			timeout:3000
            			 
                   })
            	}
             }

	       }
        )
	   
   });
});



</script>
</head>
<body bgcolor="#53868B">
    <div align="center" style="padding:160px 0 0 0">
	<div class="easyui-panel" title="大华售后服务平台" style="width:400px;" >
		<div style="padding:10px 0 10px 100px" >
	    <form id="loginform"  method="post">
	    	<table>
	    		<tr>
	    			<td>用户账号:</td>
	    			<td><input id="userName" class="easyui-validatebox" type="text" name="userName" data-options="required:true" value="190144"></input></td>
	    		</tr>
	    		<tr>
	    			<td>密&nbsp;&nbsp;码:</td>
	    			<td><input id="password" class="easyui-validatebox" type="password" name="password" value="123"></input></td>
	    		</tr>
	    	</table>
	    </form>
	    </div>
	    <div style="text-align:center;padding:5px">
		<a id="loginBtn" class="easyui-linkbutton">登      录</a>
		<a id="resetBtn" href="javascript:void(0)" class="easyui-linkbutton">清      除</a>
		</div>
	</div>
	</div>
</body>
</html>