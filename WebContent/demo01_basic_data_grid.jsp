<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.5.1/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.5.1/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.5.1/demo/demo.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.5.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.5.1/jquery.easyui.min.js"></script>
</head>
<body>

	<h2>基础数据表格</h2>
	<p>The DataGrid is created from markup, no JavaScript code needed.</p>
	<div style="margin:20px 0;"></div>
	
	<table class="easyui-datagrid" title="部门管理" style="width:700px;height:250px"
			data-options="singleSelect:true,collapsible:true,url:'deptList',method:'get'">
		<thead>
			<tr>
				<th data-options="field:'deptno',width:100,align:'left'">部门编号</th>
				<th data-options="field:'dname',width:200,align:'center'">部门名称</th>
				<th data-options="field:'loc',width:80,align:'right'">部门地址</th>
			</tr>
		</thead>
	</table>
	
</body>
</html>