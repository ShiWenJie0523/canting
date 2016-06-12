<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="pageContent">
	
	<form method="post" action="act_Bill_pay.action?id=${bean.id}" class="pageForm" onsubmit="return validateCallback(this,dialogAjaxDone);">
		<div class="pageFormContent" layoutH="58">
			<div class="unit">
				<label>流水号：</label>
				${bean.searilNum}
			</div>
			<div class="unit">
				<label>桌位号：</label>
				${bean.tableNum}
			</div>		
			<div class="unit">
				<label>消费时间：</label>
				<fmt:formatDate value="${bean.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</div>	
			<div class="unit">
				<label>预定支付：</label>
				<fmt:formatNumber value="${bean.yudingfei}" pattern="0.0"></fmt:formatNumber>
			</div>						
			<div class="unit">
				<label>消费总金额：</label>
				<fmt:formatNumber value="${bean.total_price}" pattern="0.0"></fmt:formatNumber>
			</div>	
			<div class="unit">
				<label>折后金额：</label>
				<fmt:formatNumber value="${bean.zhekou_price}" pattern="0.0"></fmt:formatNumber>
			</div>
			<div class="unit">
				<label>实付金额：(负数表示找零)</label>
				<fmt:formatNumber value="${bean.zhekou_price-bean.yudingfei}" pattern="0.0"></fmt:formatNumber>
				<input name="real_price" class="required number" type="hidden" value="${bean.zhekou_price-bean.yudingfei}"/>
			</div>			
			
			<div class="unit">
				<label>客人手机号：</label>
				${bean.mobile}
			</div>
			<div class="divider"></div>			
											
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">确认</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
	
</div>