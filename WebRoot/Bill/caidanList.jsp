<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			
		</ul>
	</div>
	<table class="table" width="100%" layoutH="75">
		<thead>
			<tr>
				<th width="140">名称</th>
				<th width="100">价格</th>
				<th width="100">数量</th>
				<th width="100">类型</th>
				<th width="100">是否已上</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="list">
			<tr target="sid_studentList" rel="${list.id}">
				<td>${list.name}</td>
				<td>${list.price}</td>
				<td>${list.billNum}</td>
				<td>
				<c:if test="${list.type==0}">菜品</c:if> 
				<c:if test="${list.type==1}">套餐</c:if>
				</td>
				<td>
				<c:if test="${list.shang==0}">未上</c:if> 
				<c:if test="${list.shang==1}">已上</c:if>
				</td>
			</tr>			
			</c:forEach>
		</tbody>
	</table>
</div>
