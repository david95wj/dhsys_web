<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="edge" />
 

<!-- 引入jQuery库 -->
<script src="${ctx}/jslib/jquery-1.8.3.js" type="text/javascript" charset="utf-8"></script>

<!-- 引入EasyUI -->
<%-- <link id="easyuiTheme" rel="stylesheet" href="${ctx}/jslib/easyui/themes/<c:out value="${cookie.easyuiThemeName.value}" default="default"/>/easyui.css" type="text/css">
 --%> 
<link id="easyuiTheme" rel="stylesheet" href="${ctx}/jslib/easyui/themes/default/easyui.css" type="text/css">
<link rel="stylesheet" type="text/css" href="${ctx}/jslib/easyui/themes/icon.css">
<script type="text/javascript" src="${ctx}/jslib/easyui/jquery.easyui.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/jslib/easyui/locale/easyui-lang-zh_CN.js" charset="utf-8"></script>

<!-- 扩展EasyUI -->
<script type="text/javascript" src="${ctx}/jslib/easyui/extEasyui.js" charset="utf-8"></script>

<!-- 扩展Jquery -->
<script type="text/javascript" src="${ctx}/jslib/easyui/extJquery.js" charset="utf-8"></script>

<!-- 自定义工具类 -->
<script type="text/javascript" src="${ctx}/jslib/dhsys.js" charset="utf-8"></script>

<!-- 扩展EasyUI图标 -->
 <link rel="stylesheet" href="${ctx}/style/dhsys.css" type="text/css">

<script type="text/javascript">
$(window).load(function(){
	$("#loading").fadeOut();
});
 
</script>
