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
<title>大华售后服务平台--系统用户管理</title>
<script type="text/javascript">
  var dataGrid;
  $(function(){
	  $.initScreen("#dataGrid");
	  dataGrid = $('#dataGrid').datagrid({    
    	    url:'${ctx}/dhsys/dept/findDepts.html',
    	    method:'post',
    	    idField:'deptId',
    	    autoRowHeight:false,
    	    loadMsg:'部门信息查询中....',
    	    singleSelect:true,
    	    toolbar:[
    	             {
    	    	       text:'新增部门',
    	    	       iconCls:'icon_add',
    	    	       handler:doAdd
    	             }
    	            ],
    	    fitColumns:true,
    	    rownumbers:true,
    	    pagination:true,
    	    pageSize:10,
    	    pageList:[5,10,15,20],
    	    columns:[[ 
                {
                 field:'deptId',
                 hidden:true
                },{
                  field:'deptNo',
                  title:'部门编号',
                  width:'120'
                },{
                  field:'deptName',
                  title:'部门名称',
                  width:'120'	
                },{
                  field:'createDate',
                  title:'建立时间',
                  width:'120'
                    
                },
    	        {
    				field : 'action',
    				title : '操      作',
    				width : '120',
    				formatter : function(value, row, index) {
    					var str = '';
    					    if ($.canUpdate) {
    							str += $.formatString('<img style="cursor:pointer" alt="变更用户" src="${ctx}/images/ext/edit.gif" onclick="doUpdate(\'{0}\')" ></img>', row.deptId);
    						}
    						str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
    						if ($.canRemove) {
    							str += $.formatString('<img style="cursor:pointer" alt="移除用户" src="${ctx}/images/ext/delete.gif" onclick="doRemove(\'{0}\')" ></img>', row.deptId);
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
      
     
      
     
      
      //新增系统用户
       function doAdd() {
    	 parent.$.modalDialog({
  			title : '新增部门',
  			width : 275,
  			height : 210,
  			href : '${ctx}/dhsys/dept/addInit.html',
  			buttons : [{
  					text : '确定新增',
  					iconCls:'icon-ok',
  	  				handler : function() {
  	  					parent.$.modalDialog.openner_dataGrid = dataGrid;
  	  					var f = parent.$.modalDialog.handler.find('#addDeptForm');
  	  					f.submit();	
  				}
  			},{
  				    text:'取消新增',
  				    iconCls:'icon-cancel',
  				    handler:function(){
  				    	parent.$.modalDialog.handler.dialog('close');  	
  				    }
  				}]
  		});
  	}
     
       $("#searchBtn").click(function(){
    	  var deptNo = $("#deptNo").val();
    	  var deptName = $("#deptName").val();
    	  var createDate = $("#createDate").datebox('getValue');
    	  
    	  var queryParams = $('#dataGrid').datagrid('options').queryParams;
          queryParams["deptNo"] = deptNo;
          queryParams["deptName"] = deptName;
          queryParams["createDate"] = createDate;
          
           $('#dataGrid').datagrid('reload');  
      });
     
     //定义条件重置按钮事件
     $("#resetBtn").click(function(){
    	 $("#searchForm").form('clear');
      });
  });
  
  //移除部门信息
   function doRemove(deptId){
	 
	  if(deptId==undefined){
		  var rows = $('#dataGrid').datagrid('getSelections'); 
		  deptId = rows[0].deptId;
	  }else if(deptId=='${sessionInfo.deptId}'){
		     parent.$.messager.show({
		     title:'系统提示',
		     msg:'不能移除当前系统用户所在的部门'
	        });
    	    return false;
	  }else{ 
		 dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
	  }
	     parent.$.messager.confirm('系统提示', '确定移除所选择的部门?', function(r) {  
		   if (r) {  
			     $.post('${ctx}/dhsys/dept/deleteDept.html', {"deptId":deptId},
			       function(result) { 
			    	 if(result.success){
			    	  parent.$.messager.alert("系统提示",result.msg,"info");
			          $('#dataGrid').datagrid('reload'); 
			    	}else{
			    	  parent.$.messager.alert("系统提示",result.msg+"<br/>原因:" + result.reason,"error");
			    	  $('#dataGrid').datagrid('reload');  
			    	}
			      },"json");
			   }  
			});  
        }
 
  
  //变更系统用户
  function doUpdate(deptId){
	   if (deptId == undefined) {
			var rows = dataGrid.datagrid('getSelections');
			deptId = rows[0].deptId;
		} else{
			dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		   parent.$.modalDialog({
					title : '变更用户信息',
					width : 275,
					height : 210,
					href : '${ctx}/dhsys/dept/updateInit.html?deptId='+deptId,
					buttons : [{
						    	  text : '确定变更',
						    	  iconCls:'icon-ok',
						          handler : function() {
							      parent.$.modalDialog.openner_dataGrid = dataGrid; 
							      var f = parent.$.modalDialog.handler.find('#updateDeptForm');
							      f.submit();   
						         }
						        },{
						         text : '取消变更',
						         iconCls:'icon-cancel',
				                 handler : function() {
				                 parent.$.modalDialog.handler.dialog('close');
						        }
						      }] 
				   }); 
		    }
    
  
  
</script>
</head>
<body class="easyui-layout">
	<form id="searchForm" method="post">
		<table id="tb" title="系统用户"
			data-options="toolbar:'#inputParam'"></table>
		<div id="inputParam" style="background: #F4F4F4;">
			<table>
				<tr>
					<th>部门编号</th>
					<td><input id="deptNo"  class="easyui-textbox"
						data-options="prompt:'部门编号'"></td>
                    
					<th>部门名称</th>
					<td><input id="deptName" ></td>
					<th>建立时间</th>
					<td><input id="createDate" class="easyui-datebox" data-options="editable:false,prompt:'请选择'"></td>
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