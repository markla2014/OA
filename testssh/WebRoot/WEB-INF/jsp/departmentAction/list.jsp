<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
    <title>部门列表</title>
	<%@ include file="/WEB-INF/jsp/public/common.jspf" %>
</head>
<body>
 
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img style="border:0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 部门管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
    <table class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr style="text-align:center;text-align:middle" id=TableTitle>
            	<td style="width:150px">部门名称</td>
				<td style="width:150px">上级部门名称</td>
				<td style="width:200px">职能说明</td>
				<td>相关操作</td>
            </tr>
        </thead>

		<!--显示数据列表-->
        <tbody id="TableData" class="dataContainer">
        
        <s:iterator value="#departmentList">
			<tr class="TableDetail1 template">
				<td><s:a action="departmentAction_list?parentId=%{id}">${name}&nbsp;</s:a></td>
				<td>${parent.name}&nbsp;</td>
				<td>${description}&nbsp;</td>
				<td>
				<!--初始的方法 
				<s:if test="#session.user.hasPrivilegeByName('部门删除')">
					<s:a action="departmentAction_delete?id=%{id}" onclick="return window.confirm('这将删除所有的下级部门，您确定要删除吗？')">删除</s:a>
				</s:if>
				<s:if test="#session.user.hasPrivilegeByName('部门修改')">
					<s:a action="departmentAction_editUI?id=%{id}">修改</s:a>
				</s:if>
				-->
					<s:a action="departmentAction_delete?id=%{id}" onclick="return window.confirm('这将删除所有的下级部门，您确定要删除吗？')">删除</s:a>
					<s:a action="departmentAction_editUI?id=%{id}">修改</s:a>
				
				</td>
			</tr>
		</s:iterator>	
			
        </tbody>
    </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail">
        <div id="TableTail_inside">
            <s:a action="departmentAction_addUI"><img src="${pageContext.request.contextPath}/style/images/createNew.png" /></s:a>
        </div>
    </div>
</div>

<!--说明-->	
<div id="Description"> 
	说明：<br />
	1，列表页面只显示一层的（同级的）部门数据，默认显示最顶级的部门列表。<br />
	2，点击部门名称，可以查看此部门相应的下级部门列表。<br />
	3，删除部门时，同时删除此部门的所有下级部门。
</div>

</body>
</html>
