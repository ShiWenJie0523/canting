<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>



<div class="pageContent">
	<table class="list" width="100%" layoutH="26">
		<thead>
			<tr>
				<th width="140">日期</th>

				<th width="140">总收入</th>
				<th width="140">菜品数量</th>
		
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${mingxi}" var="mingxi">
			<tr>
				<td>${mingxi[0]}</td>

				<td>${mingxi[2]}</td>
				<td>${mingxi[3]}</td>
			
				
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
