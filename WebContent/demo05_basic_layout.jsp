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

	<div class="easyui-layout" style="width:1000px;height:560px;">
		<div data-options="region:'north'" style="height:50px">
			<h2 style="margin-top: 10px; margin-left: 100px;">雇员信息管理</h2>
		</div>
		<div data-options="region:'south',split:true" style="height:60px;">
			<p style="font-size: 14px; text-align: center;">Copyright © 2018 - 2019 稚序员网, All Rights Reserved.</p>
		</div>
		<div data-options="region:'west',split:true" title="菜单管理" style="width:220px;">
			<!-- border="flase"去边框 -->
			<div class="easyui-panel" border="flase" style="padding:5px">
				<ul id="tree" class="easyui-tree" data-options="animate:'true',lines:'true'">
				</ul>
			</div>
		</div>
		
		<div data-options="region:'center'">
			<!-- border="flase"去边框 -->
			<div id="tt" class="easyui-tabs" border="flase" style="width:100%;height:420px;">
				<div title="首页" style="padding: 10px;">
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
	</div>

<script type="text/javascript">
	$('#tree').tree({
		data: [{
			text: '信息管理',
			iconCls:'icon-computer-go',
			children: [{
				text: '部门管理',
				iconCls:'icon-table-table-edit',
			},{
				text: '雇员管理',
				iconCls:'icon-group-group',
			},{
				text: '薪资管理',
				iconCls:'icon-table-table-save',
			}]
		},{
			text: '系统设置',
			iconCls:'icon-cog',
			children: [{
				text: '个人中心',
				iconCls:'icon-vcard-vcard-edit',
			},{
				text: '修改密码',
				iconCls:'icon-lock-lock-go',
			},{
				text: '退出系统',
				iconCls:'icon-folder-folder-user',
			}]
		}],
		onClick: function(node){
			//alert(node.text); //在用户点击的时候提示
			if(node.text=="部门管理"){
				addTab('部门管理','dept_manager.jsp');
			}else if(node.text=="雇员管理"){
				addTab('雇员管理','emp_manager.jsp')
			}
		}
	});
	
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