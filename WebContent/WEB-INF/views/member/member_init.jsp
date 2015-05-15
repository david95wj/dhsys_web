<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<title>大华售后业务平台</title>
<head>
<% 
    String ctx = request.getContextPath();
%>
<link rel="stylesheet" type="text/css" href="<%=ctx %>/jslib/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=ctx %>/jslib/easyui/themes/icon.css">
<script type="text/javascript" src="<%=ctx %>/jslib/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=ctx %>/jslib/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=ctx %>/jslib/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
$(function(){
	 
      $("#dataGrid").datagrid({
    	url:'<%=ctx%>/member/findMembers.html',
    	autoRowHeight:false,
    	striped:false,
    	idField:'memberId',
    	loadMsg:'数据加载中..........',
    	pagination:true,
    	pageList:[10,20,30],
    	fitColumns:false,
    	toolbar:'#tb',
    	rownumbers:true,
    	singleSelect:true,
    	showFooter:true,
    	sortName:'createDate',
    	sortOrder:'desc',
    	remoteSort:false,
    	columns:[[
    	   {
    		  field:'memberId',
    		  hidden:true
    	   },{
    		  field:'memberAccount',
    		  title:'会员账号',
    		  width:100
    	   },{
    		  field:'memberName',
     		  title:'会员名称',
     		  width:100 
    	   },{
    		  field:'createDate',
    		  title:'创建时间',
    		  width:100
    	   },{
    		  field:'userName',
    		  title:'用户名称',
    		  width:100
    	   },{
    		  field:'agreementCode',
     		  title:'合同编号',
     		  width:100  
    	   },{
    		  field:'memberPhone',
      		  title:'联系电话',
      		  width:100 
    	   },{
    		  field:'memberCompany',
    		  title:'所属企业',
    		  width:100
    	   },{
    		  field:'memberMail',
     		  title:'电子邮件',
     		  width:100 
    	   },{
    		  field:'memberMail',
      		  title:'电子邮件',
      		  width:100  
    	   },{
    		  field:'memberTypeName',
    		  title:'会员类型',
    		  width:100
    	   }
    	]]
     });
     
     //获取会员类型
     $("#memberAttr").combobox({
    	 url:'<%=ctx%>/member/getMemberType.html',
    	 valueField:'memberTypeId',    
    	 textField:'memberTypeName',
    	 panelHeight:'auto'
     });
     
     //查询按钮事件绑定
     $("#findBtn").click(function(){
    	 var memberAccount = $("#memberAccount").val();
	     var memberName = $("#memberName").val();
	     var userName = $("#userName").val();
	     var memberAttr = $("#memberAttr").combobox('getValue');
		 var queryParams = $('#dataGrid').datagrid('options').queryParams;
		 queryParams["p_Member.memberAccount"] = memberAccount;
		 queryParams["p_Member.memberName"] = memberName;
	  	 $('#dataGrid').datagrid('reload');  
     });
 });


</script>
</head>
<body>
   <div id="tb">
			会员账号:<input id="memberAccount" class="easyui-textbox" style="width:120px" name="p_Member.memberAccount">
			会员名称:<input id="memberName" class="easyui-textbox" style="width:120px" name="p_Member.memberName">
			合同编号:<input id="" class="easyui-textbox" style="width:120px">
			用户姓名:<input id="userName" class="easyui-textbox" style="width:120px">
			会员性质:<input id="memberAttr" style="width:120px">
			<a id="findBtn" href="#" class="easyui-linkbutton" iconCls="icon-search">查    询</a>
		</div>
    <table id="dataGrid"></table>
</body>
</html>

