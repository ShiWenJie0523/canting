<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<form id="pagerForm" method="post" action="act_Custom_customList.action">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
</form>


<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="act_Custom_customList.action" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					会员编号：<input type="text" name="customNum" />
				</td>
				<td>
					会员姓名：<input type="text" name="name" />
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
			<li><a class="add" href="to_Custom_addCustomForm.action" target="dialog" mask="true" title="添加"><span>添加</span></a></li>
			
			<li><a class="edit" href="act_Custom_findCustom.action?id={sid_studentList}" target="dialog" mask="true" title="修改"><span>修改</span></a></li>
			
			<li><a class="delete" href="act_Custom_deleCustom.action?id={sid_studentList}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
			<span>说明：会员的手机号为会员消费时的唯一凭证。</span>
				</ul>
	</div>
	<div class="panelBar">
		<ul class="toolBar">
			
			<span>积分规则：消费一元积一分，500积分以下：普通会员（不打折），500到1500积分：白金会员（打9折），1500到3000积分：黄金会员（打8折），3000积分以上:钻石会员（打7折）</span>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="113">
		<thead>
			<tr>
				<th width="140">登记时间</th>
				<th width="140">会员编号</th>
				<th width="100">姓名</th>
				<th width="100">性别</th>
				<th width="100">年龄</th>
				<th width="100">电话</th>
				<th width="200">住址</th>
				<th width="100">积分</th>
				<th width="100">会员等级</th>
				<th width="140">最新消费时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="list">
			<tr target="sid_studentList" rel="${list.id}">
				<td><fmt:formatDate value="${list.createTime}" pattern="yyyy-MM-dd"/></td>
				<td>${list.customNum}</td>
				<td>${list.name}</td>
				<td>${list.sex.label}</td>
				<td>${list.age}</td>
				<td>${list.mobile}</td>
				<td>${list.address}</td>
				<td>${list.jifen}</td>
				<td>
				<c:if test="${list.clevel==0}">普通会员</c:if>
				<c:if test="${list.clevel==1}">白金会员</c:if>
				<c:if test="${list.clevel==2}">黄金会员</c:if>
				<c:if test="${list.clevel==3}">钻石会员</c:if>
				</td>
				<td><fmt:formatDate value="${list.freshTime}" pattern="yyyy-MM-dd"/></td>
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
