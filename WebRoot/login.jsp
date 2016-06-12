<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.util.Utils"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%
//Utils.init(request);

%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>餐厅管理系统</title>
<link href="css/styles.css" rel="stylesheet" type="text/css" />
<style type="text/css">
img, div { behavior: url(iepngfix.htc) }
</style>
<script type="text/javascript">
function aa(){

window.close();
}
</script>
</head>

<body>
<body id="login">
		<div id="wrappertop"></div>
			<div id="wrapper">
					<div id="content">
						<div id="header">
							<span style="font-size: 20px;font-weight: bold;">餐厅管理系统</span>
						</div>
						
						<div id="darkbannerwrap">
						</div>
						<form action="login.action" method="post">
						<fieldset class="form">
	
                        	<p>
								<label for="user_name">账号:</label>
								<input name="username"  type="text">
							</p>
							<p>
								<label for="user_password">密码:</label>
								<input name="password" id="user_password" type="password">
							</p>
							<p>
								<label for="user_name">用户角色：</label>
								<select name="role" >
								
								<option value="ROLE_CASH"><span style="font-size: 10px;">收银员</span></option>
								<option value="ROLE_ROOT"><span style="font-size: 10px;">系统管理员</span></option>
								</select>
							</p>
							
							<button type="submit" class="positive" name="Submit">
								登录</button>
								
								<button type="button" class="positive"  onclick="aa()" >
								退出</button>
                            						</fieldset>
						
						
					</form></div>
				</div>   

<div id="wrapperbottom_branding"><div id="wrapperbottom_branding_text"></div></div>

</body>
</html>