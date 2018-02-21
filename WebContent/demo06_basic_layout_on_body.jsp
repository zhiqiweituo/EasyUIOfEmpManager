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
<body class="easyui-layout">
<div data-options="region:'north'" style="height:50px">
	<h2 style="margin-top:10px; margin-left: 100px;">员工部门信息管理</h2>
</div>
<div data-options="region:'south'" style="height:50px;">
	<div style="font-size: 14px; text-align: center;">
		<p style="font-size: 14px; text-align: center;">Copyright © 2018 - 2019 甲骨文淄博软件中心, 鲁ICP备14024044号-2, All Rights Reserved.</p>
	</div>
</div>
<div data-options="region:'west',split:true" title="导航菜单" style="width:200px;">
	<!-- border="flase"去边框 -->
	<div class="easyui-panel" fit="true" border="flase" style="padding:5px">
		<ul class="easyui-tree">
			<li>
				<span>系统菜单</span>
				<ul>
					<li><a href="#" onclick="addTab('部门管理','dept_manager.jsp')">部门管理</a></li>
					<li><a href="#" onclick="addTab('雇员管理','emp_manager.jsp')">雇员管理</a></li>
					<li>修改密码</li>
					<li>退出系统</li>
				</ul>
			</li>
		</ul>
	</div>
</div>
<div data-options="region:'center'">

	<div id="tt" class="easyui-tabs" border="flase" style="width:100%;height:450px;">
		<div title="首页" style="padding:10px">
			<p>系统描述：</p>
			<p style="font-size:14px">
				easyUI练习项目，数据表使用Scott用户表，对emp和dept表进行操作。
			</p>
			<ul>
				<li>emp雇员信息表的操作</li>
				<li>dept部门信息表的操作</li>
				<li>实际项目删除操作为逻辑删除，这里直接删除</li>
				<li>主键非自增长，由程序生成,并发访问时应加线程锁</li>
			</ul>
		</div>
	</div>

</div>

<script type="text/javascript">
	function addTab(title, url){
		if ($('#tt').tabs('exists', title)){
			$('#tt').tabs('select', title);
		} else {
			var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
			$('#tt').tabs('add',{
				title:title,
				content:content,
				closable:true
			});
		}
	}
</script>
</body>
</html>