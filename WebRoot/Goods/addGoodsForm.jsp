<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="pageContent">
	
	<form method="post" action="act_Goods_addGoods.action?cateId=${cateId}" class="pageForm" onsubmit="return validateCallback(this,navTabAjaxDone);">
		<div class="pageFormContent" layoutH="58">
			<div class="unit">
				<label>是否推荐：</label>
				<input type="radio"" name="tuijian" value="0" checked="checked"/>不推荐
				<input type="radio"" name="tuijian" value="1"/>推荐
			</div>
			<div class="unit">
				<label>上架/下架：</label>
				<input type="radio"" name="ground" value="0" checked="checked"/>下架
				<input type="radio"" name="ground" value="1"/>上架
			</div>
			<div class="unit">
				<label>适用季节：</label>
				<input type="checkbox" name="season" value="春"/>春
				<input type="checkbox" name="season" value="夏"/>夏
				<input type="checkbox" name="season" value="秋"/>秋
				<input type="checkbox" name="season" value="冬"/>冬
			</div>
			<div class="unit">
				<label>菜品编号：</label>
				<input type="text" name="goodsNum" size="20" class="required"/>
			</div>
			<div class="unit">
				<label>菜品名称：</label>
				<input type="text" name="name" size="20" class="required"/>
			</div>
			<div class="unit">
				<label>价格：</label>
				<input type="text" name="price" size="20" class="required number"/>
			</div>
					
			<div class="unit">
				<label>口味：</label>
				<input type="text" name="kouwei" size="20"/>
			</div>			
									
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
	
</div>