<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="inc.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>大华售后服务平台</title>
<script type="text/javascript">
	var index_layout;
	var index_tabs;
	var index_tabsMenu;
	var layout_west_tree;
	var layout_west_tree_url = '';
	
	 var sessionInfo_userId = '${sessionInfo.userId}';
	//alert(sessionInfo_userId);
	 //如果没有登录,直接跳转到登录页面
	if (sessionInfo_userId) {
		 
		layout_west_tree_url = '${ctx}/dhsys/main.html';
	}else{
		alert("session为空流程");
		window.location.href='${ctx}/dhsys/sysInit.html';
	} 
	$(function() {
		index_layout = $('#index_layout').layout({
			fit : true
		});
		
		index_tabs = $('#index_tabs').tabs({
			fit : true,
			border : false,
			tools : [{
				iconCls : 'icon_home',
				handler : function() {
					index_tabs.tabs('select', 0);
				}
			}, {
				iconCls : 'icon_refresh',
				handler : function() {
					var index = index_tabs.tabs('getTabIndex', index_tabs.tabs('getSelected'));
					index_tabs.tabs('getTab', index).panel('refresh');
				}
			}, {
				iconCls : 'icon_close',
				handler : function() {
					var index = index_tabs.tabs('getTabIndex', index_tabs.tabs('getSelected'));
					var tab = index_tabs.tabs('getTab', index);
					if (tab.panel('options').closable) {
						index_tabs.tabs('close', index);
					}
				}
			} ]
		});
		
		layout_west_tree = $('#layout_west_tree').tree({
			url : layout_west_tree_url,
			parentField : 'pid',
			lines : true,
			onClick : function(node) {
				alert(node.url);
				if (node.attributes && node.attributes.url) {
					var url = '${ctx}' + node.attributes.url;
					addTab({
						url : url,
						title : node.text,
						iconCls : node.iconCls
					});
				}
			}
		});
	});
	
	function addTab(params) {
		var iframe = '<iframe src="' + params.url + '" frameborder="0" style="border:0;width:100%;height:98%;"></iframe>';
		var t = $('#index_tabs');
		var opts = {
			title : params.title,
			closable : true,
			iconCls : params.iconCls,
			content : iframe,
			border : false,
			fit : true
		};
		if (t.tabs('exists', opts.title)) {
			t.tabs('select', opts.title);
		} else {
			t.tabs('add', opts);
		}
	}


	 
</script>
</head>
<body bgcolor="#53868B">
	<div id="loading" style="position: fixed;top: -50%;left: -50%;width: 200%;height: 200%;background: #fff;z-index: 100;overflow: hidden;">
    <img src="${ctx}/images/ext/ajax-loader.gif" style="position: absolute;top: 0;left: 0;right: 0;bottom: 0;margin: auto;"/>
	</div>
	<div id="index_layout">
		<div data-options="region:'north',split:true" style=" overflow: hidden;" id="header">
		    <img  style="float: left; padding-left: 30px;padding-top: 5px;" src="${ctx}/images/ext/logo.png" ></img>
			<span style="float: right; padding-right: 20px;">当前用户 <b>${sessionInfo.userName}</b>&nbsp;&nbsp; <a href="javascript:void(0)" onclick="editUserPwd()" style="color: #fff">修改密码</a>&nbsp;&nbsp;<a href="javascript:void(0)" onclick="logout()" style="color: #fff">安全退出</a>
        	&nbsp;&nbsp;&nbsp;&nbsp;
    		</span>
    		<span class="header"></span>
		</div>
		<div data-options="region:'west',split:true" title="功能导航" style="width: 200px; overflow: hidden;overflow-y:auto;" iconCls="icon_nav">
			<div class="well well-small" style="padding: 10px 5px 5px 5px;">
				<ul id="layout_west_tree"></ul>
			</div>
		</div>
		<div data-options="region:'center'" style="overflow: hidden;">
			<div id="index_tabs" style="overflow: hidden;">
				<div title="系统首页" data-options="border:false" style="overflow: hidden;">
					<div style="padding:10px 0 10px 10px">
						<h2>系统介绍</h2>
						<div class="light-info">
							<div class="light-tip icon-tip"></div>
							<div>大华售后服务平台</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div data-options="region:'south',border:false" style="height: 30px; overflow: hidden;text-align: center;background-color: #daeef5" ></div>
	</div>
	
</body>
</html>