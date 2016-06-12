<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="pageContent">
	
	<form method="post" action="act_Custom_updateCustom.action?id=${bean.id}" class="pageForm" onsubmit="return validateCallback(this,dialogAjaxDone);">
		<div class="pageFormContent" layoutH="58">
			<div class="unit">
				<label>姓名：</label>
				<input type="text" name="name" value="${bean.name}" size="20" class="required"/>
			</div>
			<div class="unit">
				<label>性别：</label>
				<select name="sex" class="combox required">
					<option value="">请选择</option>
					<option value="MAN" <c:if test="${bean.sex eq 'MAN'}">selected="selected"</c:if>>男</option>
					<option value="WOMAN" <c:if test="${bean.sex eq 'WOMAN'}">selected="selected"</c:if>>女</option>
				</select>			
			</div>
			
			<div class="unit">
				<label>年龄：</label>
				<input type="text" name="age" value="${bean.age}" size="20" class="required digits"/>
			</div>
			<div class="unit">
				<label>电话：</label>
				<input type="text" name="mobile" value="${bean.mobile}" size="20" class="required"/>
			</div>			
			<div class="unit">
				<label>住址：</label>
				<input type="text" name="address" value="${bean.address}" size="20"/>
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