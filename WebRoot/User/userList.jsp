<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<form id="pagerForm" method="post" action="act_User_userList.action">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
</form>



<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			
			<li><a class="add" href="to_User_addUserForm.action" target="dialog" mask="true" title="添加"><span>添加用户</span></a></li>
			<li><a class="edit" href="act_User_findUser.action?id={sid_studentList}" target="dialog" mask="true" title="个人信息"><span>信息修改</span></a></li>
			<li><a class="delete" href="act_User_deleUser.action?id={sid_studentList}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="75">
		<thead>
			<tr>
				<th width="100">权限</th>
				<th width="140">用户名</th>
				<th width="100">姓名</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="list">
			<tr target="sid_studentList" rel="${list.id}">
				<td>
					<c:if test="${list.role eq 'ROLE_ROOT'}">管理员</c:if>
					<c:if test="${list.role eq 'ROLE_CASH'}">收银员</c:if>
					<c:if test="${list.role eq 'ROLE_MANAGER'}">经理</c:if>
				</td>
				<td>${list.username}</td>
				<td>${list.realName}</td>
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
