<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<form id="pagerForm" method="post" action="act_Bill_chufangList.action">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
</form>



<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="delete" href="act_Bill_shangcai.action?id={sid_studentList}" target="ajaxTodo"><span>完成上菜</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="75">
		<thead>
			<tr>
				<th width="140">桌位号</th>
				<th width="100">名称</th>
				<th width="100">数量</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="list">
			<tr target="sid_studentList" rel="${list.id}">
				<td>${list.bill.tableNum}</td>
				<td>${list.name}</td>
				<td>${list.billNum}</td>
			</tr>			
			</c:forEach>
			
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			
			<span>共${totalCount}条</span>
		</div>
		
		<div class="pagination" targetType="navTab" totalCount="${totalCount}" numPerPage="${numPerPage}" pageNumShown="10" currentPage="${pageNum}"></div>

	</div>
</div>
