<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<form id="pagerForm" method="post" action="act_Bill_billList.action">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
</form>



<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="act_Bill_caidan.action?id={sid_studentList}" target="navTab" title="点菜" rel="caidan"><span>点菜</span></a></li>
			<li><a class="edit" href="act_Bill_caidanList.action?id={sid_studentList}" target="navTab" title="已点菜单" rel="caidanList"><span>已点菜单</span></a></li>
			<li><a class="icon" href="act_Bill_payForm.action?id={sid_studentList}" target="dialog" mask="true"><span>确认消费</span></a></li>
			<li><a class="icon" href="act_Bill_finish.action?id={sid_studentList}" target="ajaxTodo" title="确定要结束消费吗?"><span>结束消费</span></a></li>
			<span>消费流程：点菜--》确认消费--》厨房传单--》结束消费</span>
			
		</ul>
	</div>
	<table class="list" width="100%" layoutH="53">
		<thead>
			<tr>
				<th width="140">流水号</th>
				<th width="100">桌位号</th>
				<th width="100">客人手机</th>
				<th width="100">预定支付</th>
				<th width="100">支付方式</th>
				<th width="100">总消费金额</th>
				<th width="100">折后金额</th>
				<th width="100">实付金额（负数表示找零)</th>
		
				<th width="100">是否结账</th>
				<th width="100">状态</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="list">
			<tr target="sid_studentList" rel="${list.id}">
				<td>${list.searilNum}</td>
				<td>${list.tableNum}</td>
				<td>${list.mobile}</td>
				<td>${list.yudingfei}</td>
				<td>${list.zhifu}</td>
				
				<td><fmt:formatNumber value="${list.total_price}" pattern="0.0"></fmt:formatNumber></td>
				<td><fmt:formatNumber value="${list.zhekou_price}" pattern="0.0"></fmt:formatNumber></td>
				<td>${list.real_price}</td>
			
				<td>
					<c:if test="${list.status==0}">未付钱</c:if>
					<c:if test="${list.status==1}">已结账</c:if>
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
