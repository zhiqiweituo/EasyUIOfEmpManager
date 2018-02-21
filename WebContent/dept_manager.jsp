<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.5.1/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.5.1/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.5.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.5.1/jquery.easyui.min.js"></script>
</head>
<body>

<table id="dg" class="easyui-datagrid" fitColumns="true"
 pagination="true" rownumbers="true" url="deptList" toolbar="#tb">
	<thead>
		<tr>
			<th field="cb" checkbox="true"></th>
			<th field="deptno" width="50" align="center">部门编号</th>
			<th field="dname" width="80" align="center">部门名称</th>
			<th field="loc" width="200" align="center">部门地址</th>
		</tr>
	</thead>
</table>

<div id="tb">
	<div>
		<a href="javascript:openDeptAddDialog()" class="easyui-linkbutton" iconCls="icon-table-table-add" plain="true">添加</a>
		<a href="javascript:openDeptModifyDialog()" class="easyui-linkbutton" iconCls="icon-table-table-edit" plain="true">修改</a>
		<a href="javascript:deleteDept()" class="easyui-linkbutton" iconCls="icon-table-table-delete" plain="true">删除</a>
	</div>
	<div>
		<label style="margin-left: 5px;">部门名称：</label><input class="easyui-textbox" id="s_dname" name="s_dname"/>
		<label style="margin-left: 5px;">部门地址：</label><input class="easyui-textbox" id="s_loc" name="s_loc"/>
		<a href="javascript:searchDept()" class="easyui-linkbutton" iconCls="icon-zoom-zoom-in" plain="true">搜索</a>
	</div>
</div>

<div id="dlg" class="easyui-dialog" style="width: 400px;height: 280px;padding: 10px 20px"
	closed="true" buttons="#dlg-buttons">
	<form id="fm" method="post">
		<table>
			<tr>
				<td>部门名称：</td>
				<td><input type="text" name="dname" id="dname" class="easyui-validatebox" required="true"/></td>
			</tr>
			<tr>
				<td valign="top">部门位置：</td>
				<td><textarea rows="5" cols="20" name="loc" id="loc" style="resize:none;"></textarea></td>
			</tr>
		</table>
	</form>
</div>

<div id="dlg-buttons">
	<a href="javascript:saveDept()" class="easyui-linkbutton" iconCls="icon-printer-printer-add">保存</a>
	<a href="javascript:closeDeptDialog()" class="easyui-linkbutton" iconCls="icon-folder-folder-wrench">关闭</a>
</div>

<script type="text/javascript">
	function searchDept(){
		$('#dg').datagrid('load',{
			s_dname:$('#s_dname').val(),
			s_loc:$('#s_loc').val()
		});
	}
	
	var url;//定义全局变量
	function openDeptAddDialog() {
		resetValue();
		$("#dlg").dialog("open").dialog("setTitle","添加部门信息");
		url="deptSave";
	}
	function saveDept() {
		$("#fm").form("submit",{
			url:url,
			onSubmit:function(){
				return $(this).form("validate");
			},
			success:function(result){
				result=eval("("+result+")");
				//alert(result.flag);
				if(!result.flag){
					$.messager.alert("系统提示",result.msg);
					return;
				}else{
					$.messager.alert("系统提示",result.msg);
					resetValue();
					$("#dlg").dialog("close");
					$("#dg").datagrid("reload");
				}
			}
		});
	}
	function closeDeptDialog() {
		$("#dlg").dialog("close");
	}
	function resetValue() {
		$("#dname").val("");
		$("#loc").val("");
	}
	
	function openDeptModifyDialog() {
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一条要编辑的数据！");
			return;
		}
		var row=selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle","编辑部门信息");
		$("#fm").form("load",row);
		url="deptSave?deptno="+row.deptno;
	}
	
	function deleteDept() {
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length==0){
			$.messager.alert("系统提示","请选择要删除的数据！");
			return;
		}
		var strIds=[];
		for(var i=0;i<selectedRows.length;i++){
			strIds.push(selectedRows[i].deptno);
		}
		var ids=strIds.join(",");
		$.messager.confirm("系统提示","您确认要删掉这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
			if(r){
				$.post("deptDelete",{delIds:ids},function(result){
					if(result.flag){
						$.messager.alert("系统提示","您已成功删除<font color=red>"+result.delNums+"</font>条数据！");
						$("#dg").datagrid("reload");
					}else{
						$.messager.alert('系统提示','<font color=red>'+selectedRows[result.errorIndex].dname+'</font>'+result.errorMsg);
					}
				},"json");
			}
		});
	}
</script>
</body>
</html>