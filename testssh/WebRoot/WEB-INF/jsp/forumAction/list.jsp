<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
	<title>论坛</title>
	<%@ include file="/WEB-INF/jsp/public/common.jspf" %>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/forum.css" />
</head>
<body>
<div id="Title_bar">
	<div id="Title_bar_Head">
		<div id="Title_Head"></div>
		<div id="Title">
			<!--页面标题-->
			<img style="border:0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 论坛 </div>
		<div id="Title_End"></div>
	</div>
</div>
<div id="MainArea">
		<div class="ForumPageTableBorder" style="margin-top: 25px; text-align:center;">
			<table style=" width:100%; border:0; border-collapse:collapse;">
			
				<!--表头--> 
				<tr style="text-align:center; text-valign:middle;">
					<td colspan="3" class="ForumPageTableTitleLeft">版块</td>
					<td style="width:80;" class="ForumPageTableTitle">主题数</td>
					<td style="width:80;" class="ForumPageTableTitle">文章数</td>
					<td style="width:270;" class="ForumPageTableTitle">最后发表的主题</td>
				</tr>
				<tr  class="ForumPageTableTitleLine"><td colspan="9"></td></tr>
				<tr style="height:3;"><td colspan="9"></td></tr>
			
				<!--版面列表-->
				<tbody class="dataContainer">
				
				<s:iterator value="#forumList">
					<tr style="text-align:center;height:78;" class="template">
						<td style="width:3;"></td>
						<td style="width:75;" class="ForumPageTableDataLine">
							<img src="${pageContext.request.contextPath}/style/images/forumpage3.gif" />
						</td>
						<td class="ForumPageTableDataLine">
							<ul class="ForumPageTopicUl">
								<li class="ForumPageTopic">
									<s:a action="forumAction_show?id=%{id}" cssClass="ForumPageTopic">${name}</s:a>
								</li>
								<li class="ForumPageTopicMemo">${description}</li>
							</ul>
						</td>
						<td class="ForumPageTableDataLine"><b>${topicCount}</b></td>
						<td class="ForumPageTableDataLine"><b>${articleCount}</b></td>
						<td class="ForumPageTableDataLine">
							<ul class="ForumPageTopicUl">
								<li><span style="color:#444444;">┌ 主题：</span> 
									<s:a cssClass="ForumTitle" action="topicAction_show?id=%{lastTopic.id}">${lastTopic.title}</s:a>
								</li>
								<li><span style="color:#444444;">├ 作者：</span> ${lastTopic.author.name}</li>
								<li><span style="color:#444444;">└ 时间：</span> <s:date name="%{lastTopic.postTime}" format="yyyy-MM-dd HH:mm:ss"/></li>
							</ul>
						</td>
						<td style="width:3;"></td>
					</tr>
				</s:iterator>	
					
				</tbody>
				<!-- 版面列表结束 -->
				
				<tr height="3"><td colspan="9"></td></tr>
			</table>
		</div>
</div>
</body>
</html>
