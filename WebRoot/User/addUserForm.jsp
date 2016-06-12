<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="pageContent">
	
	<form method="post" action="act_User_addUser.action" class="pageForm" onsubmit="return validateCallback(this,dialogAjaxDone);">
		<div class="pageFormContent" layoutH="58">
			<div class="unit">
				<label>用户名：</label>
				<input type="text" name="username" size="20" class="required"/>
			</div>
			<div class="unit">
				<label>密码：</label>
				<input type="password" name="userpwd" size="20" class="required" id="newspwd" minlength="3" maxlength="20"/>
			</div>
			<div class="unit">
				<label>确认密码：</label>
				<input type="password" size="20" class="required" equalTo="#newspwd" minlength="3" maxlength="20"/>
			</div>
			
				
			<div class="unit">
				<label>姓名：</label>
				<input type="text" name="realName" size="20" class="required"/>
			</div>				
			
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
	
</div>