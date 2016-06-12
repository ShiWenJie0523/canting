<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<form id="pagerForm" method="post" action="act_Bill_historyBill.action">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
</form>



<div class="pageContent">
	<table class="list" width="100%" layoutH="26">
		<thead>
			<tr>
				<th width="140">流水号</th>
				<th width="100">桌位号</th>
				<th width="140">消费时间</th>
				<th width="100">客人手机</th>
				<th width="100">预定支付</th>
				<th width="100">支付方式</th>
				<th width="100">总消费金额</th>
				<th width="100">折后金额</th>
				<th width="100">实付金额（负数表示找零）</th>
				<th width="100">找零</th>
				<th width="100">是否结账</th>
			
				<th width="100">状态</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="list">
			<tr target="sid_studentList" rel="${list.id}">
				<td>${list.searilNum}</td>
				<td>${list.tableNum}</td>
				<td><fmt:formatDate value="${list.createTime}" pattern="yyyy-MM-dd"/> </td>
				<td>${list.mobile}</td>
				<td>${list.yudingfei}</td>
				<td>
				<c:if test="${list.zhifu==null}">现金支付</c:if>
				<c:if test="${list.zhifu!=null}">${list.zhifu}</c:if>
				</td>
				<td><fmt:formatNumber value="${list.total_price}" pattern="0.0"></fmt:formatNumber></td>
				<td><fmt:formatNumber value="${list.zhekou_price}" pattern="0.0"></fmt:formatNumber></td>
				<td>${list.real_price}</td>
				<td><fmt:formatNumber value="${list.zhaoli_price}" pattern="0.0"></fmt:formatNumber></td>
				<td>
					<c:if test="${list.status==0}">未付钱</c:if>
					<c:if test="${list.status==1}">已付钱</c:if>
				</td>
				
				<td>
					<c:if test="${list.effect==0}"><span style="color:red;">未生效</span></c:if>
					<c:if test="${list.effect==1}"><span style="color:green;">生效</span></c:if>
				</td>
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
