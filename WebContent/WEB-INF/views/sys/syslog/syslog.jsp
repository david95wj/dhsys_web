<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../../inc.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="edge" />
<c:if test="${fn:contains(sessionInfo.adminFlag,1)}">
<script type="text/javascript">
        $.canUpdate = true;
		$.canRemove = true;
</script>
</c:if>
<title>大华售后服务平台--系统日志管理</title>
<script type="text/javascript">
  var dataGrid;
  $(function(){
	  $.initScreen("#dataGrid");
	  dataGrid = $('#dataGrid').datagrid({    
    	    url:'${ctx}/dhsys/syslog/findSyslogs.html',
    	    method:'post',
    	    idField:'logId',
    	    autoRowHeight:false,
    	    loadMsg:'系统日志信息查询中....',
    	    singleSelect:true,
    	    toolbar:[
    	             
    	            ],
    	    fitColumns:true,
    	    rownumbers:true,
    	    pagination:true,
    	    pageSize:10,
    	    pageList:[5,10,15,20],
    	    columns:[[ 
                {
                 field:'logId',
                 hidden:true
                },{
                  field:'userAccount',
                  title:'用户',
                  width:'120'
                },{
                  field:'moduleName',
                  title:'模块名称',
                  width:'120'	
                },{
                    field:'type',
                    title:'日志类型',
                    width:'120'	
                },{
                  field:'activeDate',
                  title:'操作时间',
                  width:'120' 
                },{
                    field:'logBak',
                    title:'备注',
                    width:'120'
                },
    	        {
    				field : 'action',
    				title : '操      作',
    				width : '120',
    				formatter : function(value, row, index) {
    					var str = '';
    					    if ($.canUpdate) {
    							//str += $.formatString('<img style="cursor:pointer" alt="变更用户" src="${ctx}/images/ext/edit.gif" onclick="doUpdate(\'{0}\')" ></img>', row.syslogId);
    						} 
    					  return str;
    				}
    			}
    	    ]],
    	 });
      
      var p = $('#dataGrid').datagrid('getPager');   
      $(p).pagination({   
          pageSize: 10,//每页显示的记录条数，默认为10   
          pageList: [5,10,15],//可以设置每页记录条数的列表   
          beforePageText: '第',//页数文本框前显示的汉字   
          afterPageText: '页    共 {pages} 页',   
          displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',   
          //刷新数据按钮时间定义
          /*onBeforeRefresh:function(){  
              $(this).pagination('loading');  
              alert('before refresh');  
              $(this).pagination('loaded');  
          }*/  
      }); 
	
      $("#searchBtn").click(function(){
    	  var userAccount = $("#userAccount").val();
    	  var moduleName = $("#moduleName").val();
    	  var activeDate = $("#activeDate").datebox('getValue');
    	  
    	  var queryParams = $('#dataGrid').datagrid('options').queryParams;
          queryParams["userAccount"] = userAccount;
          queryParams["moduleName"] = moduleName;
          queryParams["activeDate"] = activeDate;
          
           $('#dataGrid').datagrid('reload');  
      });
     //定义条件重置按钮事件
     $("#resetBtn").click(function(){
    	 $("#searchForm").form('clear');
      });
  });
  
  
 
 
</script>
</head>
<body class="easyui-layout">
	<form id="searchForm" method="post">
		<table id="tb" title="系统日志"
			data-options="toolbar:'#inputParam'"></table>
		<div id="inputParam" style="background: #F4F4F4;">
			<table>
				<tr>
					<th>用户</th>
					<td><input id="userAccount"  class="easyui-textbox"
						data-options="prompt:'用名账号'"></td>
					<th>模块名称</th>
					<td><input id="moduleName"  class="easyui-textbox"
						data-options="prompt:'模块名称'"></td>
					<th>操作时间</th>
					<td><input id="activeDate" class="easyui-datebox" data-options="editable:false,prompt:'请选择'"></td>
				    <td><a id="searchBtn" class="easyui-linkbutton"
						data-options="iconCls:'icon_search'">查 询</a></td>
					<td><a id="resetBtn" class="easyui-linkbutton"
						data-options="iconCls:'icon_reload'">重 置</a></td>
			</table>
		</div>
	</form>
	<table id="dataGrid"></table>
</body>
</html>