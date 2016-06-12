<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>



<form id="pagerForm" method="post" action="act_Bill_diningtableList.action">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
</form>


<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="act_Bill_diningtableList.action" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					桌位状态：
					<select name="status">
						<option value="">全部</option>
						<option value="0">空</option>
						<option value="1">有人</option>
						<option value="2">已预定</option>
						<option value="3">预定超时</option>
					</select>
				</td>
				<td>
					座位数：
					<select name="seatCount">
						<option value="">全部</option>
						<c:forEach items="${seatCountList}" var="seatCountList">
							<c:if test="${not empty seatCountList}">
							<option value="${seatCountList}">${seatCountList}</option>
							</c:if>
						</c:forEach>
					</select>
				</td>
				<td>
					桌位号：
					<input name="tableNum" size="10"/>
				</td>
				<td>
					客人手机号：
					<input name="mobile" size="10"/>
				</td>				
				<td>
					<div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div>
				</td>
			</tr>
		</table>
	</div>
	</form>
</div>



<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
		<c:if test="${roleNum==0}">
			<li><a class="add" href="to_Bill_addDiningTableForm.action" target="dialog" mask="true" title="添加"><span>添加</span></a></li>
			<li><a class="edit" href="act_Bill_findDiningTable.action?id={sid_studentList}" target="dialog" mask="true" title="信息"><span>信息修改</span></a></li>
			<li><a class="delete" href="act_Bill_deleDiningTable.action?id={sid_studentList}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
			</c:if>
			<c:if test="${roleNum==1}">
			<li><a class="icon" href="to_Bill_yudingForm.action?id={sid_studentList}" target="dialog" mask="true"><span>预定</span></a></li>
			<li><a class="icon" href="act_Bill_resetDiningTable.action?id={sid_studentList}" target="ajaxTodo" title="确定要取消预定吗?"><span>取消预定</span></a></li>
			<li><a class="icon" href="act_Bill_openForm.action?id={sid_studentList}" target="dialog" mask="true"><span>开台</span></a></li>
		</c:if>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="112">
		<thead>
			<tr>
				<th width="100">桌位编号</th>
				<th width="80">座位数</th>
				<th width="100">桌位状态</th>
				<th width="100">客人手机号</th>
				<th width="100">客人姓名</th>
				<th width="140">预定时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="list">
			<tr target="sid_studentList" rel="${list.id}">
				<td>${list.tableNum}</td>
				<td>${list.seatCount}</td>
				<td>
					<c:if test="${list.status==0}">空</c:if>
					<c:if test="${list.status==1}">有人</c:if>
					<c:if test="${list.status==2}">已预定</c:if>
					<c:if test="${list.status==3}">预定超时</c:if>
				</td>
				<td>${list.mobile}</td>
				<td>${list.name}</td>
				<td><fmt:formatDate value="${list.bookTime}" pattern="yyyy-MM-dd HH:mm"/></td>
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
