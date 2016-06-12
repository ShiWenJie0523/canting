<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<form id="pagerForm" method="post" action="act_Goods_goodsList.action">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
	<input type="hidden" name="cateId" value="${cateId}" />
</form>



<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="act_Goods_addGoodsForm.action?cateId=${cateId}" target="navTab" mask="true" title="添加"><span>添加</span></a></li>
			<li><a class="edit" href="act_Goods_findGoods.action?id={sid_studentList}" target="navTab" mask="true" title="信息"><span>信息修改</span></a></li>
			<li><a class="delete" href="act_Goods_deleGoods.action?id={sid_studentList}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
		</ul>
	</div>
	<table class="list" width="100%" layoutH="52">
		<thead>
			<tr>
				<th width="140">分类名称</th>
				<th width="100">菜品编号</th>
				<th width="100">是否推荐</th>
				<th width="100">状态</th>
				<th width="100">适用季节</th>
				<th width="100">菜品名称</th>
				<th width="100">价格</th>
	
				<th width="100">口味</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="list">
			<tr target="sid_studentList" rel="${list.id}">
				<td>${list.goodsCate.cateName}</td>
				<td>${list.goodsNum}</td>
				<td>
					<c:if test="${list.tuijian==0}">否</c:if>
					<c:if test="${list.tuijian==1}">是</c:if>
				</td>
				<td>
					<c:if test="${list.ground==0}"><span style="color: red">下架</span></c:if>
					<c:if test="${list.ground==1}"><span style="color: green;">上架</span></c:if>
				</td>
				<td>${list.season}</td>
				<td>${list.name}</td>
				<td>${list.price}</td>

				<td>${list.kouwei}</td>
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
