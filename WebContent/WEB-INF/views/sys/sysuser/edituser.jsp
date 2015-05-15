<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript">
	$(function() {
     
		$('#updateUserForm').form({
            url : '${ctx}/dhsys/sysuser/updateuser.html',
			onSubmit : function() {
				//progressLoad();
				var isValid = $(this).form('validate');
                if (!isValid) {
					  $.messager.show({
						  title:'提示信息',
					      msg:'用户信息没有填写完成'
					  });
				}
				return isValid;
			},
			success : function(result) {
				progressClose();
				result = $.parseJSON(result);

				if (result.success) {
					parent.$.messager.alert('系统提示', result.msg, 'info');
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');
					parent.$.modalDialog.handler.dialog('close');
				} else {
					parent.$.messager.alert('系统提示', result.msg, 'error');
				}
			}
		});
		 
		//加载管理员标志
        $("#adminFlag").combobox({
            url:'${ctx}/json/ADMIN_FLAG.json',    
 	        valueField:'adminFlagId',    
 	        textField:'adminFlagName',
 	        method:'post',
 	        editable:false,
 	        panelHeight:'auto',
 	        onLoadSuccess:function(data){
 	        	var o_adminFlagId = $("#adminFlag").val();
 	            for(var i=0;i<data.length;i++){
 	        		if(data[i].adminFlagId==o_adminFlagId){
 	        			$('#adminFlag').combobox('setValue',data[i].adminFlagId);
 	        		}
 	        	}
 	        }
 	    });
       
        //获取角色树
        $('#roles').combotree({
		    url: '${ctx}/dhsys/sysrole/roleList.html',
		    multiple: true,
		    required: false,
		    panelHeight : 'auto',
		    cascadeCheck : false,
		    value : $.stringToList('${user.roleIds}')
		});
        
        //加载部门信息
        $('#deptId').combobox({    
	        url:'${ctx}/dhsys/dept/deptList.html',
			valueField : 'deptId',
		    textField : 'deptName',
			method : 'post',
			editable : false,
			panelHeight : 'auto',
			onLoadSuccess : function(data) {
			 var o_deptId = $("#deptId").val();
				 for(var i=0;i<data.length;i++){
					 if (data[i].deptId==o_deptId) {
					    $('#deptId').combobox('setValue',data[i].deptId);
			          } 
			     }
		     }
      });
  });
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title=""
		style="overflow: hidden; padding: 3px;">
		<form id="updateUserForm" method="post">
			<table id="addUser" cellspacing="13" cellpadding="4" align="center" class="grid">
				 <tr>
			       <input type="hidden" name="userId" value="${user.userId }"/>
			       <td>用户账号:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="userAccount" name="userAccount" class="easyui-validatebox" value="${user.userAccount }"
						data-options="required:true,missingMessage:'必填项'" /></td>
				   <td>建立时间:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="createDate" class="easyui-datebox" name="createDate"  value="${user.createDate}"
						data-options="prompt:'建立时间',editable:false" /></td>
				 </tr>
				 <tr>
				   <td>用户IP:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="userIp" type="userIp" name="userIp" value="${user.userIp }" 
						data-options="prompt:'用户IP',editable:false" /></td>		
				   <td>用户部门:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="deptId" name="deptId" value="${user.deptId }"/></td>
				 </tr> 
				 <tr>
				  <td>是否管理员:&nbsp;&nbsp;&nbsp;<input id="adminFlag" name="adminFlag" value="${user.adminFlag }"/></td>
				  <td>角色列表:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="roles" name="roleIds"/></td>
				</tr>
				<tr>
				  <td colspan="2">用户备注:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="userBak" name="userBak" value="${user.userBak }" data-options="multiline:true,prompt:'在此输入对设备的检测描述'" style="width: 220px; height: 50px" /></td>
				</tr>  
			</table>
		</form>
	</div>
</div>