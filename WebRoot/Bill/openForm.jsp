<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="pageContent">
	
	<form method="post" action="act_Bill_useDiningTable.action?id=${bean.id}" class="pageForm" onsubmit="return validateCallback(this,dialogAjaxDone);">
		<div class="pageFormContent" layoutH="58">
			<div class="unit">
				<label>手机号：</label>
				<input type="text" name="mobile" value="${bean.mobile}" size="20" class="required"/>
			</div>
			<div class="unit">
				<label>姓名：</label>
				<input type="text" name="name" value="${bean.name}" size="20" class="required"/>
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