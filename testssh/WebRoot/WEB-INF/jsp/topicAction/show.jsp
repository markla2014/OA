<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
<title>查看主题：${topic.title}</title>
<%@ include file="/WEB-INF/jsp/public/common.jspf"%>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/style/blue/forum.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/script/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
	$(function() {
		if (typeof CKEDITOR == 'undefined') {
			document.write('加载CKEditor失败');
		} else {
			var editor = CKEDITOR.replace('content', {
				toolbar : 'bbs',
				basePath : '${pageContext.request.contextPath}/ckeditor/',
				width : '95%',
				height : '95%',
				skin : 'kama',
				customConfig : 'myconfig.js'
			});
		}
	});
</script>
</head>
<body>

	<!-- 标题显示 -->
	<div id="Title_bar">
		<div id="Title_bar_Head">
			<div id="Title_Head"></div>
			<div id="Title">
				<!--页面标题-->
				<img border="0" width="13" height="13"
					src="${pageContext.request.contextPath}/style/images/title_arrow.gif" />
				查看主题
			</div>
			<div id="Title_End"></div>
		</div>
	</div>

	<!--内容显示-->
	<div id="MainArea">
		<div id="PageHead"></div>
		<center>
			<div class="ItemBlock_Title1" style="width: 98%">
				<font class="MenuPoint"> &gt; </font>
				<s:a action="forumAction_list">论坛</s:a>
				<font class="MenuPoint"> &gt; </font>
				<s:a action="forumAction_show?id=%{#topic.forum.id}">${topic.forum.name}</s:a>
				<font class="MenuPoint"> &gt;&gt; </font> 帖子阅读 <span
					style="margin-left:30px;"> <s:a
						action="topicAction_addUI?forumId=%{#topic.forum.id}">
						<img align="absmiddle"
							src="${pageContext.request.contextPath}/style/blue/images/button/publishNewTopic.png" />
					</s:a>
				</span>
			</div>

			<div class="ForumPageTableBorder dataContainer" datakey="replyList">

				<!--显示主题标题等-->
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr valign="bottom">
						<td width="3" class="ForumPageTableTitleLeft">&nbsp;</td>
						<td class="ForumPageTableTitle"><b>本帖主题：${topic.title}</b></td>
						<td class="ForumPageTableTitle" align="right"
							style="padding-right:12px;"><s:a cssClass="detail"
								action="replyAction_addUI?topicId=%{#topic.id}">
								<img border="0"
									src="${pageContext.request.contextPath}/style/images/reply.gif" />
							回复
						</s:a> <a href="moveUI.html"><img border="0"
								src="${pageContext.request.contextPath}/style/images/edit.gif" />移动到其他版块</a>
							<a href="#" onClick="return confirm('要把本主题设为精华吗？')"><img
								border="0"
								src="${pageContext.request.contextPath}/style/images/topicType_1.gif" />精华</a>
							<a href="#" onClick="return confirm('要把本主题设为置顶吗？')"><img
								border="0"
								src="${pageContext.request.contextPath}/style/images/topicType_2.gif" />置顶</a>
							<a href="#" onClick="return confirm('要把本主题设为普通吗？')"><img
								border="0"
								src="${pageContext.request.contextPath}/style/images/topicType_0.gif" />普通</a>
						</td>
						<td width="3" class="ForumPageTableTitleRight">&nbsp;</td>
					</tr>
					<tr height="1" class="ForumPageTableTitleLine">
						<td colspan="4"></td>
					</tr>
				</table>

				<!-- ~~~~~~~~~~~~~~~ 显示主帖（主帖只在第1页显示） ~~~~~~~~~~~~~~~ -->
				<s:if test="currentPage==1">
					<div class="ListArea">
						<table border="0" cellpadding="0" cellspacing="1" width="100%" height="100%">
							<tr>
								<td rowspan="3" width="130" class="PhotoArea" align="center"
									valign="top">
									<!--作者头像-->
									<div class="AuthorPhoto">
										<img border="0" width="110" height="110"
											src="${pageContext.request.contextPath}/style/images/defaultAvatar.gif"
											onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/style/images/defaultAvatar.gif';" />
									</div> <!--作者名称-->
									<div class="AuthorName">${topic.author.name}</div>
								</td>
								<td style="text-align:center;height:40px">
									<ul class="TopicFunc">
										<!--操作列表-->
										<li class="TopicFuncLi"><a class="detail"
											href="${pageContext.request.contextPath}/BBS_Topic/saveUI.html"><img
												border="0"
												src="${pageContext.request.contextPath}/style/images/edit.gif" />编辑</a>
											<a class="detail" href="#"
											onClick="return confirm('确定要删除本帖吗？')"><img border="0"
												src="${pageContext.request.contextPath}/style/images/delete.gif" />删除</a>
										</li>
										<!-- 文章表情与标题 -->
										<li class="TopicSubject"><s:if
												test="#topic.faceIcon!=null">
												<img width="19" height="19"
													src="${pageContext.request.contextPath}/style/images/face/${topic.faceIcon}.gif" />
											</s:if> ${topic.title}</li>
									</ul>
								</td>
							</tr>
							<tr>
								<!-- 文章内容 -->
								<td valign="top" align="center" class="">
									<div class="Content">${topic.content}</div>
								</td>
							</tr>
							<tr >
								<!--显示楼层等信息-->
								<td class="Footer" height="28" align="center" valign="bottom">
									<ul style="margin: 0px; width: 98%;">
										<li style="float: left; line-height:18px;"><font
											color=#C30000>[楼主]</font> <s:date name="%{#topic.postTime}"
												format="yyyy-MM-dd HH:mm:ss" /></li>
										<li style="float: right;"><a
											href="javascript:scroll(0,0)"> <img border="0"
												src="${pageContext.request.contextPath}/style/images/top.gif" /></a>
										</li>
									</ul>
								</td>
							</tr>
						</table>
					</div>
				</s:if>
				<!-- ~~~~~~~~~~~~~~~ 显示主帖结束 ~~~~~~~~~~~~~~~ -->


				<!-- ~~~~~~~~~~~~~~~ 显示回复列表 ~~~~~~~~~~~~~~~ -->
				<s:iterator value="recordList" status="status">
					<div class="ListArea template">
						<table border="0" cellpadding="0" cellspacing="1" width="100%" height="100%">
							<tr>
								<td rowspan="3" width="130" class="PhotoArea" align="center"
									valign="top">
									<!--作者头像-->
									<div class="AuthorPhoto">
										<img border="0" width="110" height="110"
											src="${pageContext.request.contextPath}/style/images/defaultAvatar.gif"
											onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/style/images/defaultAvatar.gif';" />
									</div> <!--作者名称-->
									<div class="AuthorName">${author.name}</div>
								</td>
								<td align="center" height="20px">
									<ul class="TopicFunc">
										<!--操作列表-->
										<li class="TopicFuncLi"><a class="detail"
											href="${pageContext.request.contextPath}/BBS_Topic/saveUI.html"><img
												border="0"
												src="${pageContext.request.contextPath}/style/images/edit.gif" />编辑</a>
											<a class="detail" href="#"
											onClick="return confirm('确定要删除本帖吗？')"><img border="0"
												src="${pageContext.request.contextPath}/style/images/delete.gif" />删除</a>
										</li>
										<!-- 文章表情与标题 -->
										<li class="TopicSubject"><s:if test="faceIcon!=null">
												<img width="19" height="19"
													src="${pageContext.request.contextPath}/style/images/face/${faceIcon}.gif" />
											</s:if> ${title}</li>
									</ul>
								</td>
							</tr>
							<tr>
								<!-- 文章内容 -->
								<td valign="top" align="center" height="40%">
									<div class="Content">${content}</div>
								</td>
							</tr>
							<tr>
								<!--显示楼层等信息-->
								<td class="Footer" height="28" align="center" valign="bottom">
									<ul style="margin: 0px; width: 98%;">
										<li style="float: left; line-height:18px;"><font
											color=#C30000>[${(currentPage-1)*pageSize+status.count}楼]</font>
											<s:date name="%{postTime}" format="yyyy-MM-dd HH:mm:ss" /></li>
										<li style="float: right;"><a
											href="javascript:scroll(0,0)"> <img border="0"
												src="${pageContext.request.contextPath}/style/images/top.gif" /></a>
										</li>
									</ul>
								</td>
							</tr>
						</table>
					</div>
				</s:iterator>
				<!-- ~~~~~~~~~~~~~~~ 显示回复列表结束 ~~~~~~~~~~~~~~~ -->
			</div>

			<!--分页信息-->
			<s:form action="topicAction_show.action?id=%{id}"></s:form>
			<%@ include file="/WEB-INF/jsp/public/pageView.jspf" %>
			<script type="text/javascript">
				function gotoPage(pageNum) {
					//var test = "topicAction_show.action?id=${id}&pageNum="+ num;
					//alert(test);
					//window.location = test;
var test="<input type='hidden' name='pageNum' value='"+pageNum+"'/>";
			  $(document.forms[0]).append(test);
              document.forms[0].submit();
				}
			</script>
			<div class="ForumPageTableBorder" style="margin-top: 25px;">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr valign="bottom">
						<td width="3" class="ForumPageTableTitleLeft">&nbsp;</td>
						<td class="ForumPageTableTitle"><b>快速回复</b></td>
						<td width="3" class="ForumPageTableTitleRight">&nbsp;</td>
					</tr>
					<tr height="1" class="ForumPageTableTitleLine">
						<td colspan="3"></td>
					</tr>
				</table>
			</div>
		</center>

		<!--快速回复-->
		<div class="QuictReply">
			<s:form action="replyAction_add" cssStyle="margin: 0; padding: 0;">
				<input type="hidden" name="topicId" value="${topic.id}" />

				<div id="PageHead"></div>
				<center>
					<div class="ItemBlock_Title1">
						<div width=85% style="float:left">
							<font class="MenuPoint"> &gt; </font>
							<s:a action="forumAction_list">论坛</s:a>
							<font class="MenuPoint"> &gt; </font>
							<s:a action="forumAction_show?id=%{#topic.forum.id}">${topic.forum.name}</s:a>
							<font class="MenuPoint"> &gt;&gt; </font> 帖子回复
						</div>
					</div>
					<div class="ItemBlockBorder">
						<table border="0" cellspacing="1" cellpadding="1" width="100%"
							id="InputArea">
							<tr>
								<td class="InputAreaBg" height="30"><div class="InputTitle">帖子主题</div></td>
								<td class="InputAreaBg"><div class="InputContent">${topic.title}</div></td>
							</tr>
							<tr>
								<td class="InputAreaBg" height="30"><div class="InputTitle">标题</div></td>
								<td class="InputAreaBg"><div class="InputContent">
										<s:textfield name="title" cssClass="InputStyle"
											cssStyle="width:100%" value="回复：%{#topic.title}" />
									</div></td>
							</tr>
							<tr>
								<td class="InputAreaBg" height="30"><div class="InputTitle">表情</div></td>
								<td class="InputAreaBg"><div class="InputContent">
										<!-- 显示表情符号 -->
										<!--现在在设计单选框(radio)和复选框(checkbox)时都会给选择文字加上label增大选择范围，以提高用户体验。
								但在给单独的图片加label时无法成功。
								解决方法是：给图片img标签加上disabled即可。-->
										<table cellpadding="0" border="0" cellspacing="0">
											<tr>
												<s:iterator begin="1" end="14" var="num">
													<td width="50" style="border-bottom:0 solid #ffffff">
														<input type="radio" name="faceIcon" value="${num}"
														id="r_${num}" /> <label for="r_${num}"><img
															width="19" height="19"
															src="${pageContext.request.contextPath}/style/images/face/${num}.gif"
															disabled="true" align="absmiddle" /></label>
													</td>
												</s:iterator>
											</tr>
										</table>
									</div></td>
							</tr>
							<tr height="240">
								<td class="InputAreaBg"><div class="InputTitle">内容</div></td>
								<td class="InputAreaBg">
									<div class="InputContent">
										<s:textarea name="content"
											cssStyle="width:650px;height:200px;"></s:textarea>
									</div>
								</td>
							</tr>
							<tr height="30">
								<td class="InputAreaBg" colspan="2" align="center"><input
									type="image"
									src="${pageContext.request.contextPath}/style/blue/images/button/submit.PNG"
									style="margin-right:15px;" /> <a
									href="javascript:history.go(-1);"><img
										src="${pageContext.request.contextPath}/style/blue/images/button/goBack.png" /></a>
								</td>
							</tr>
						</table>
					</div>
				</center>
			</s:form>
		</div>
	</div>

	<div class="Description">
		说明：<br /> 1，主帖只在第一页显示。<br />
		2，只有是管理员才可以进行“移动”、“编辑”、“删除”、“精华”、“置顶”的操作。<br /> 3，删除主帖，就会删除所有的跟帖（回复）。<br />
	</div>

</body>
</html>
