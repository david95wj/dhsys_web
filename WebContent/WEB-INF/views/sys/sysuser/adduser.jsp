<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript">
	$(function() {
     
		$('#addUserForm').form({
            url : '${ctx}/dhsys/sysuser/adduser.html',
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
 	        prompt:'请选择'
 	       

		});
       
        //获取角色树
        $('#roles').combotree({
		    url: '${ctx}/dhsys/sysrole/roleList.html',
		    multiple: true,
		    required: false,
		    panelHeight : 'auto'
		});
        
        
        $("#deptId").combobox({    
	        url:'${ctx}/dhsys/dept/deptList.html',    
	        valueField:'deptId',    
	        textField:'deptName',
	        method:'post',
	        editable:false,
	        panelHeight:'auto',
	        prompt:'请选择'
	  });
  });
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title=""
		style="overflow: hidden; padding: 3px;">
		<form id="addUserForm" method="post">
			<table id="addUser" cellspacing="13" cellpadding="4" align="center" class="grid">
				 <tr>
			       <td>用户账号:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="userAccount" name="userAccount" class="easyui-validatebox"
						data-options="required:true,missingMessage:'必填项'" /></td>
				   <td>建立时间:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="createDate" class="easyui-datebox" name="createDate"  value="${nowDate}"
						data-options="prompt:'建立时间',editable:false" /></td>
				 </tr>
				 <tr>
				   <td>用户密码:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="pwd" type="password" class="easyui-validatebox" name="password"
						data-options="prompt:'用户密码'" /></td>
				    <td>用户IP:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="userIp" type="userIp" name="userIp" value="${userIp }" 
						data-options="prompt:'用户IP'" /></td>		
				</tr> 
				 <tr>
				  <td>是否管理员:&nbsp;&nbsp;&nbsp;<input id="adminFlag" name="adminFlag"/></td>
				  <td>角色列表:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="roles" name="roleIds"/></td>
				</tr>
				<tr>
				  <td>用户部门:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="deptId" name="deptId"/></td>
				  <td>用户备注:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="userBak" name="userBak"/></td>
				</tr>  
			</table>
		</form>
	</div>
</div>