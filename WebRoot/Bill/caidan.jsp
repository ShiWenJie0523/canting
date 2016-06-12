<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
		</ul>
	</div>
	
	<form method="post" action="act_Bill_addCaiDan.action?billId=${bill.id}" class="pageForm" onsubmit="return validateCallback(this,navTabAjaxDone);">
	<div class="pageFormContent" layoutH="58">
		<div class="unit">
				<label>提示：</label>
				<span style="color: red;">
				<c:if test="${bill.guazhang==0}">您必须先付款</c:if>
				<c:if test="${bill.guazhang==1}">您可以享受挂账功能</c:if>
				</span>
		</div>	
	
	<div align="center"><h2 class="contentTitle">单点菜品</h2></div>
	<c:forEach items="${candanList}" var="candanList">
	<div style="padding-top:15px;padding-bottom: 5px;padding-left: 10px;font-weight: bold;">${candanList.cateName}</div>
	<table class="list" width="100%">
		<thead>
			<tr>
				<th width="22"></th>
				<th width="30">数量</th>
				<th width="140">菜品编号</th>
				<th width="140">菜品名称</th>
				<th width="100">价格</th>
				<th width="100">是否推荐</th>
				<th width="100">适用季节</th>
				<th width="100">口味</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${candanList.goodsList}" var="list">
			<tr target="sid_studentList" rel="${list.id}">
				<td><input name="goods_ids" value="${list.id}" type="checkbox"></td>
				<td><input name="goods_billNum${list.id}" size="1" value="1"/></td>
				<td>${list.goodsNum}</td>
				<td>${list.name}</td>
				<td>${list.price}</td>
				<td>	
					<c:if test="${list.tuijian==1}">推荐</c:if>
				</td>
				<td>${list.season}</td>
				<td>${list.kouwei}</td>
			</tr>			
			</c:forEach>
			
		</tbody>
	</table>
	</c:forEach>
	<!-- 
	
	<div align="center"><h2 class="contentTitle">套餐</h2></div>
	<table class="list" width="100%">
		<thead>
			<tr>
				<th width="22"></th>
				<th width="30">数量</th>
				<th width="140">套餐编号</th>
				<th width="140">套餐名称</th>
				<th width="100">价格</th>
				<th width="100">是否推荐</th>
				<th width="100">适用季节</th>
				<th width="100">套餐信息</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${taocanList}" var="list">
			<tr>
				<td><input name="taocan_ids" value="${list.id}" type="checkbox"></td>
				<td><input name="taocan_billNum${list.id}" size="1" value="1"/></td>
				<td>${list.taocanNum}</td>
				<td>${list.name}</td>
				<td>${list.price}</td>
				<td>	
					<c:if test="${list.tuijian==1}">推荐</c:if>
				</td>
				<td>${list.season}</td>
				<td>${list.mark}</td>
			</tr>			
			</c:forEach>
			
		</tbody>
	</table>
	 -->
	<div class="formBar">
		<ul>
			<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
			<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
		</ul>
	</div>
	
	</div>
	</form>
</div>
