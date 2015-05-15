<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript">
	$(function() {
     
		$('#updateDeptForm').form({
            url : '${ctx}/dhsys/dept/updatedept.html',
			onSubmit : function() {
				//progressLoad();
				var isValid = $(this).form('validate');
                if (!isValid) {
					  $.messager.show({
						  title:'提示信息',
					      msg:'部门信息没有填写完成'
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
	 });
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title=""
		style="overflow: hidden; padding: 3px;">
		<form id="updateDeptForm" method="post">
			<table id="updateDept" cellspacing="13" cellpadding="4" align="center" class="grid">
				 <tr>
			       <input type="hidden" name="deptId" value="${d.deptId }"/>
			       <td>部门编号:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="deptNo" name="deptNo" class="easyui-validatebox"
						data-options="required:true,missingMessage:'必填项'" value="${d.deptNo }"/></td>
				 </tr>
				 <tr>
			       <td>部门名称:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="deptName" name="deptName" class="easyui-validatebox"
						data-options="required:true,missingMessage:'必填项'" value="${d.deptName }"/></td>
				 </tr>
				 <tr>
			       <td>建立时间:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="createDate" name="createDate" class="easyui-datebox" value="${d.createDate }"/></td>
				 </tr>
			</table>
		</form>
	</div>
</div>