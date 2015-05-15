<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript">
	$(function() {
     
		$('#addRoleForm').form({
            url : '${ctx}/dhsys/sysrole/addrole.html',
			onSubmit : function() {
				//progressLoad();
				var isValid = $(this).form('validate');
                if (!isValid) {
					  $.messager.show({
						  title:'提示信息',
					      msg:'角色信息没有填写完成'
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
		 
		 //获取应用模块列表
        $('#modules').combotree({
		    url: '${ctx}/dhsys/sysrole/moduleList.html',
		    parentField : 'pid',
			lines : true,
			panelHeight : 'auto',
			multiple : true,
			required: false,
			cascadeCheck : true,
			width: 150,
			value : $.stringToList('${modules}')
		});
    });
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title=""
		style="overflow: hidden; padding: 3px;">
		<form id="addRoleForm" method="post">
			<table id="addRole" cellspacing="13" cellpadding="4" align="center" class="grid">
				 <tr>
			       <td>角色名称:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="roleName" name="roleName" class="easyui-validatebox"
						data-options="required:true,missingMessage:'必填项'" /></td>
				   <td>建立时间:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="createDate" class="easyui-datebox" name="createDate"
						data-options="prompt:'建立时间',editable:false" value="${nowDate }"/></td>
				 </tr>
				 <tr>
				   <td>应用模块:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="modules" name="modules" data-options="prompt:'应用模块'" /></td>
				   <td>排列序号:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="seq" name="seq" value="${userIp }" 
						data-options="prompt:'排列序号'" /></td>		
				</tr> 
			 </table>
		</form>
	</div>
</div>