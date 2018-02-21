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
<script type="text/javascript">
	function formatHiredate(val,row) {
		var data=val.split(" ")[0];
		return data;
	}
	function formatSal(val,row) { //如果薪资小余1000，那么已红色字体来显示
		if(val<1000){
			return "<span style='color:red;'>"+val+"</span>";
		}else{
			return val;
		}
	}
	function formatComm(val,row) { //如果佣金为null，则显示0
		if(val=="" || val==null){
			return "-";
		}else{
			return val;
		}
	}
</script>
</head>
<body>
<!-- datagrid -->
<table id="dg" class="easyui-datagrid" fitColumns="true"
 pagination="true" rownumbers="true" url="empList" toolbar="#tb">
	<thead>
		<tr>
			<th field="cb" checkbox="true"></th>
			<th field="empno" width="100" align="center">雇员编号</th>
			<th field="ename" width="100" align="center">雇员姓名</th>
			<th field="job" width="100" align="center">职位</th>
			<th field="mgr" width="100" align="center">直属上级</th>
			<th field="hiredate" width="240" align="center" formatter="formatHiredate">雇佣日期</th>
			<th field="sal" width="80" align="center" formatter="formatSal">薪资</th>
			<th field="comm" width="80" align="center" formatter="formatComm">佣金</th>
			<th field="deptno" width="100" align="center" hidden="true">部门编号</th>
			<th field="dname" width="200" align="center">部门名称</th>
		</tr>
	</thead>
</table>
<!-- toolbar -->
<div id="tb">
	<div>
		<a href="javascript:openEmpAddDialog()" class="easyui-linkbutton" iconCls="icon-group-group-add" plain="true">添加</a>
		<a href="javascript:openEmpModifyDialog()" class="easyui-linkbutton" iconCls="icon-group-group-edit" plain="true">修改</a>
		<a href="javascript:deleteEmp()" class="easyui-linkbutton" iconCls="icon-group-group-delete" plain="true">删除</a>
	</div>
	<div>
		<label style="margin-left: 5px;">员工编号：</label><input class="easyui-textbox" type="text" name="s_empno" id="s_empno" size="10"/>
		<label style="margin-left: 0px;">员工姓名：</label><input class="easyui-textbox" type="text" name="s_ename" id="s_ename" size="10"/>
		<label style="margin-left: 0px;">所属部门：</label>
		<input class="easyui-combobox" id="s_deptno" name="s_deptno" size="15"
			data-options="
				url:'deptComboList',
				method:'get',
				editable:false,
				valueField:'deptno',
				textField:'dname',
				panelHeight:'auto'
			"/>
	<a href="javascript:searchEmp()" class="easyui-linkbutton" iconCls="icon-zoom-zoom-in" plain="true">搜索</a></div>
</div>

<!-- dialog -->
<div id="dlg" class="easyui-dialog" style="width: 570px;height: 350px;padding: 10px 20px"
	closed="true" buttons="#dlg-buttons">
	<form id="fm" method="post">
		<table cellspacing="5px;">
			<tr>
				<td>雇员编号：</td>
				<td><input class="easyui-textbox" id="empno" readonly="readonly" value="由系统自动生成"/></td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td>雇员姓名：</td>
				<td><input class="easyui-textbox easyui-validatebox" name="ename" id="ename" required="true"/></td>
			</tr>
			<tr>
				<td>职位：</td>
				<td><input class="easyui-textbox easyui-validatebox" name="job" id="job" class="easyui-validatebox" required="true"/></td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td>直属上级：</td>
				<td>
					<input class="easyui-combobox" id="mgr" name="mgr"
						data-options="
							url:'empMgrList',
							method:'get',
							editable:false,
							valueField:'empno',
							textField:'ename',
							panelHeight:'200px'
					"/>
				</td>
			</tr>
			<tr>
				<td>雇佣日期：</td>
				<td><input class="easyui-datebox" name="hiredate" id="hiredate" required="true" editable="false"/></td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td>所属部门：</td>
				<td>
					<input class="easyui-combobox" id="deptno" name="deptno"
						data-options="
							url:'deptComboList',
							method:'get',
							editable:false,
							valueField:'deptno',
							textField:'dname',
							panelHeight:'200px;'
					"/>
				</td>
			</tr>
			<tr>
				<td>薪资：</td>
				<td><input class="easyui-textbox easyui-validatebox" name="sal" id="sal" required="true"/></td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td>佣金：</td>
				<td><input class="easyui-textbox" name="comm" id="comm"/></td>
			</tr>

		</table>
	</form>
</div>
<!-- buttons -->
<div id="dlg-buttons">
	<a href="javascript:saveEmp()" class="easyui-linkbutton" iconCls="icon-printer-printer-add">保存</a>
	<a href="javascript:closeEmpDialog()" class="easyui-linkbutton" iconCls="icon-folder-folder-wrench">关闭</a>
</div>

<script type="text/javascript">
	function searchEmp() {
		//alert($("#s_deptno").combobox('getValue'));
		$("#dg").datagrid('reload',{
			s_empno:$("#s_empno").val(),
			s_ename:$("#s_ename").val(),
			s_deptno:$("#s_deptno").combobox('getValue')
		});
	}
	
	var url;//定义全局变量
	
	function resetValue(){
		//$("#empno").textbox("setValue",""); //雇员编号由系统自动生成-不能重置
		$("#ename").textbox("setValue","");
		$("#job").textbox("setValue","");
		$("#mgr").combobox("setValue","");
		$("#hiredate").datebox("setValue","");
		$("#deptno").combobox("setValue","");
		$("#sal").textbox("setValue","");
		$("#comm").textbox("setValue","");
	}
	
	function openEmpAddDialog() {
		//resetValue(); //点击右上角关闭，保留已填写内容
		$("#dlg").dialog("open").dialog("setTitle","添加雇员信息");
		url="empSave";
	}
	function closeEmpDialog(){
		$("#dlg").dialog("close");
		resetValue(); //点击关闭按钮，清除已填写内容
	}
	
	function saveEmp(){
		$("#fm").form("submit",{
			url:url,
			onSubmit:function(){
				if($('#mgr').combobox("getValue")==""){
					$.messager.alert("系统提示","请选择所属领导");
					return false;
				}
				if($('#deptno').combobox("getValue")==""){
					$.messager.alert("系统提示","请选择所属部门");
					return false;
				}
				return $(this).form("validate");
			},
			success:function(result){
				var data=eval("("+result+")");
				if(!data.flag){
					$.messager.alert("系统提示",data.msg);
					return;
				}else{
					$.messager.alert("系统提示",data.msg);
					resetValue();
					$("#dlg").dialog("close");
					$("#dg").datagrid("reload");
				}
			}
		});
	}
	
	function openEmpModifyDialog() {
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一条要编辑的数据！");
			return;
		}
		var row=selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle","编辑雇员信息");
		$("#fm").form("load",row);
		url="empSave?empno="+row.empno; //准备好提交前要请求的Servlet
	}
	
	function deleteEmp() {
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length==0){
			$.messager.alert("系统提示","请选择要删除的数据！");
			return;
		}
		var strIds=[];
		for(var i=0;i<selectedRows.length;i++){
			strIds.push(selectedRows[i].empno);
		}
		var ids=strIds.join(",");
		$.messager.confirm("系统提示","您确认要删掉这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
			if(r){
				$.post("empDelete",{delIds:ids},function(result){
					if(result.flag){
						$.messager.alert("系统提示","您已成功删除<font color=red>"+result.delNums+"</font>条数据！");
						$("#dg").datagrid("reload");
					}else{
						$.messager.alert('系统提示','<font color=red>'+selectedRows[result.errorIndex].dname+'</font>'+result.msg);
					}
				},"json");
			}
		});
	}
</script>
</body>
</html>