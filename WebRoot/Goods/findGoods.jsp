<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="pageContent">
	
	<form method="post" action="act_Goods_updateGoods.action?id=${bean.id}" class="pageForm" onsubmit="return validateCallback(this,navTabAjaxDone);">
		<div class="pageFormContent" layoutH="58">
			<div class="unit">
				<label>是否推荐：</label>
				<input type="radio"" name="tuijian" value="0" <c:if test="${bean.tuijian==0}">checked="checked"</c:if>/>不推荐
				<input type="radio"" name="tuijian" value="1" <c:if test="${bean.tuijian==1}">checked="checked"</c:if>/>推荐
			</div>
			<div class="unit">
				<label>上架/下架：</label>
				<input type="radio"" name="ground" value="0" <c:if test="${bean.ground==0}">checked="checked"</c:if>/>下架
				<input type="radio"" name="ground" value="1" <c:if test="${bean.ground==1}">checked="checked"</c:if>/>上架
			</div>
			<div class="unit">
				<label>适用季节：</label>
				${season}
			</div>
			<div class="unit">
				<label>菜品编号：</label>
				<input type="text" name="goodsNum" value="${bean.goodsNum}" size="20" class="required" readonly="true"/>
			</div>
			<div class="unit">
				<label>菜品名称：</label>
				<input type="text" name="name" value="${bean.name}" size="20" class="required"/>
			</div>
			<div class="unit">
				<label>价格：</label>
				<input type="text" name="price" value="${bean.price}" size="20" class="required number"/>
			</div>
				
			<div class="unit">
				<label>口味：</label>
				<input type="text" name="kouwei" value="${bean.kouwei}" size="20"/>
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