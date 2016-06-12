<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String username = (String)session.getAttribute(com.util.ActionUtil.SESSION_USERNAME);
if(username==null){
	response.sendRedirect("login.jsp");
	return ;
}

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>餐厅管理系统</title>

<link href="ui/themes/default/style.css" rel="stylesheet" type="text/css" />
<link href="ui/themes/css/core.css" rel="stylesheet" type="text/css" />
<link href="ui/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" />
<!--[if IE]>
<link href="ui/themes/css/ieHack.css" rel="stylesheet" type="text/css" />
<![endif]-->

<script src="ui/js/speedup.js" type="text/javascript"></script>

<script src="ui/js/jquery-1.4.4.min.js" type="text/javascript"></script>
<script src="ui/js/jquery.cookie.js" type="text/javascript"></script>
<script src="ui/js/jquery.validate.js" type="text/javascript"></script>
<script src="ui/js/jquery.bgiframe.js" type="text/javascript"></script>
<script src="ui/xheditor/xheditor-1.1.9-zh-cn.min.js" type="text/javascript"></script>
<script src="ui/uploadify/scripts/swfobject.js" type="text/javascript"></script>

<script src="ui/uploadify/scripts/jquery.uploadify.v2.1.4.js" type="text/javascript"></script>

<script src="ui/js/dwz.min.js" type="text/javascript"></script>
<script src="ui/js/dwz.regional.zh.js" type="text/javascript"></script>

<script type="text/javascript">
$(function(){
	DWZ.init("ui/dwz.frag.xml", {
		loginUrl:"login_dialog.html", loginTitle:"登录",	// 弹出登录对话框
		statusCode:{ok:200, error:300, timeout:301}, //【可选】
		pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"}, //【可选】
		debug:false,	// 调试模式 【true|false】
		callback:function(){
			initEnv();
			$("#themeList").theme({themeBase:"ui/themes"}); // themeBase 相对于index页面的主题base路径
		}
	});
});
</script>
</head>

<body scroll="no">
	<div id="layout">
		<div id="header">
			<div class="headerNav">
				<!-- <a class="logo" href="http://">标志</a> -->
				<div style="font-size: 25px;font-weight: bold;color: #fff;padding-left:10px;height: 40px;;padding-top: 10px;">餐厅管理系统</div>
				<ul class="nav">
					<li><a href="javascript:void(0);">您好,${session_username}</a></li>
					<li><a href="to_User_pwd.action" rel="pwd" target="dialog" width="600">修改密码</a></li>
					<li><a href="logout.action">退出</a></li>
				</ul>
				
			</div>

			<!-- navMenu -->
			
		</div>

		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse"><div></div></div>
				</div>
			</div>
			<div id="sidebar">
				<div class="toggleCollapse"><h2>主菜单</h2><div>收缩</div></div>

				<div class="accordion" fillSpace="sidebar">
					
					<div class="accordionHeader">
					<h2><span>Folder</span>操作栏目</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							${menu}
						</ul>
					</div>
					
					
										
				</div>
			</div>
		</div>
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon">我的主页</span></span></a></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:;">公司通告</a></li>
				</ul>
				<div class="navTab-panel tabsPageContent layoutBox">
					<div class="page unitBox">
						<div class="accountInfo">
							<p>
							<span style="font-size: 22px;text-align: center;">您好，${session_username}。欢迎使用餐厅管理系统！</span></p>
						</div>
						<div class="pageFormContent" layoutH="80">
						<c:forEach items="${newsList}" var="newsList">
						<h2>${newsList.title}:</h2>
						<div class="unit"><fmt:formatDate value="${newsList.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
						<div class="unit">${newsList.content}</div>
						<div class="divider"></div>
						</c:forEach>
						</div>
					</div>
					
				</div>
			</div>
		</div>

	</div>
	<!-- <div id="footer">Copyright &copy; 2011 </div> -->

</body>
</html>