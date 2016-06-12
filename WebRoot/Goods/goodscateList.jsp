<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<form id="pagerForm" method="post" action="act_Goods_goodscateList.action">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
</form>



<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="to_Goods_addGoodsCateForm.action" target="dialog" mask="true" title="添加"><span>添加</span></a></li>
			<li><a class="edit" href="act_Goods_findGoodsCate.action?id={sid_studentList}" target="dialog" mask="true" title="信息"><span>信息修改</span></a></li>
			<li><a class="delete" href="act_Goods_deleGoodsCate.action?id={sid_studentList}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
			<li><a class="icon" href="act_Goods_goodsList.action?cateId={sid_studentList}" target="navTab" rel="goodsList"><span>菜品列表</span></a></li>
			
		</ul>
	</div>
	<table class="table" width="100%" layoutH="75">
		<thead>
			<tr>
				<th width="140">分类名称</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="list">
			<tr target="sid_studentList" rel="${list.id}">
				<td>${list.cateName}</td>
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
