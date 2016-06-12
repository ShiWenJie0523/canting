<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="pageContent">
	
	<form method="post" action="act_Bill_yuding.action?id=${param.id}" class="pageForm" onsubmit="return validateCallback(this,dialogAjaxDone);">
		<div class="pageFormContent" layoutH="58">
			<div class="unit">
				<label>手机号：</label>
				<input type="text" name="mobile" size="20" class="required"/>
			</div>
			<div class="unit">
				<label>姓名：</label>
				<input type="text" name="name" size="20" class="required"/>
			</div>		
			<div class="unit">
				<label>预定时间：</label>
				<input type="text" name="bookTime" class="date required" format="yyyy-MM-dd HH:mm" readonly="true"/>
				<a class="inputDateButton" href="javascript:;">选择</a>
			</div>	
			<div class="unit">
				<label>预定支付：</label>
				<input type="text" name="yudingfei" size="20" class="required number"/>
			</div>
			<div class="unit">
				<label>支付方式：</label>
				<select name="zhifu" class="combox required">
					
					<option value="现金支付">现金支付</option>
					<option value="网上支付">网上支付</option>
				</select>			
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