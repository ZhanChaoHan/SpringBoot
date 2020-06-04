<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../../taglibs.jsp" %>
<!DOCTYPE html >
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="../common/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="../common/css/font_eolqem241z66flxr.css" media="all" />
	<title>日志管理</title>
	<style>
		table.layui-table th {
			text-align:center;
		}
		table.layui-table td {
			text-align:center;
		}
	</style>
</head>
<body class="childrenBody">
	<form class="layui-form" action="/sysArea/querySysAreaListByFilter.do" method="post" id="frm">
	<blockquote class="layui-elem-quote news_search">
		<div class="layui-inline">
			<label class="layui-form-label">地区代码： </label>
		    <div class="layui-input-inline">
		    	<input type="text" lay-verify="areaCode" name="areaCode" value="${areaCode }" placeholder="请输入关键字" class="layui-input search_input">
		    </div>
		</div>
		<div class="layui-inline">
		<label class="layui-form-label">地区名称： </label>
		    <div class="layui-input-inline">
		    	<input type="text" lay-verify="areaCname" name="areaCname" value="${areaCname }" placeholder="请输入关键字" class="layui-input search_input">
		    </div>
		</div>
		<div class="layui-inline">
		<label class="layui-form-label">上级地区：  </label>
		    <div class="layui-input-inline">
		    	<input type="text" lay-verify="supAreaCName" name="supAreaCName" value="${supAreaCName }" placeholder="请输入关键字" class="layui-input search_input">
		    </div>
		</div>
		<div class="layui-inline">
			<%if(isRoleContained(AppConst.ROLE_CODE_2_3_2, set)){ %>
		    <button  class="layui-btn" lay-submit lay-filter="search" >查询</button >
		    <% } %>
		    <%if(isRoleContained(AppConst.ROLE_CODE_2_3_1, set)){ %>
		    <button class="layui-btn layui-btn-normal usersAdd_btn">添加地区</button>
		    <% } %>
		    <%if(isRoleContained(AppConst.ROLE_CODE_2_3_4, set)){ %>
		    <button  class="layui-btn layui-btn-danger batchDel"  >批量删除</button >
		    <% } %>
		</div>
	</blockquote>
	<div class="layui-form news_list">
	  	<table class="layui-table">
		    <colgroup>
		    	<col width="50">
				<col width="10%">
				<col width="15%">
				<col width="15%">
				<col width="15%">
				<col width="10%">
				<col width="31%">
		    </colgroup>
		    <thead>
				<tr>
					<th><input type="checkbox" name="" lay-skin="primary" lay-filter="allChoose" id="allChoose"></th>
					<th>序号</th>
					<th>地区代码</th>
					<th>地区名称</th>
					<th>上级地区</th>
					<th>是否有下级</th>
					<th>操作</th>
				</tr> 
		    </thead>
		    <tbody class="news_content">
		    	<c:if test="${page!=null }">
		    		<c:forEach items="${page.list }" var="sysarea" varStatus="varCount">
						<tr>
							<td>
								<input type="checkbox" name="checked" lay-skin="primary" lay-filter="choose" id="choose">
							</td>
							<td>${(page.pageNum-1)*10+varCount.index+1}</td>					
							<td>${sysarea.areaCode }</td>
							<td>${sysarea.areaCname }</td>		
							<td>${sysarea.superName }</td>
							<td>${sysarea.hasSubArea }</td>
							<td>
								 <%if(isRoleContained(AppConst.ROLE_CODE_2_3_3, set)){ %>
								 <a class="layui-btn layui-btn-mini news_edit"><i class="iconfont icon-edit"></i>修改</a>
								 <%} %>
								 <%if(isRoleContained(AppConst.ROLE_CODE_2_3_4, set)){ %>
								 <a class="layui-btn layui-btn-danger layui-btn-mini news_del" data-id="14"><i class="layui-icon"></i> 删除</a>
								 <%} %>
							</td>
						</tr>		    		
		    		</c:forEach>
		    	</c:if>
		    </tbody>
		</table>
	</div>
	<input name="pageNum" value="${page.pageNum }" id="pageNum" type="hidden" />
	<input id="total" type="hidden" value="${page.total }"/>
	<c:if test="${page!=null }">
		<div id="page" style="text-align: right;"></div>
	</c:if>
	</form>
	<script type="text/javascript" src="../common/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="../common/layui/layui.all.js"></script>
	<script type="text/javascript">
	;!function(){
			var layer = parent.layer === undefined ? layui.layer : parent.layer;
		  var laypage = layui.laypage;
		  var form = layui.form;
		  //执行一个laypage实例
		form.verify({
			findContent: function(value, item){ //value：表单的值、item：表单的DOM对象
			    if(value!=''&&!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)){
			      return '关键字不能有特殊字符';
			    }
			  }
			});      
		   form.on("submit(search)",function(data){
			   $("#pageNum").val(1);
			});
		  
		  if($("#total").val()!=null && $("#total").val().trim()!=''){
			  laypage.render({
				  elem: 'page'
				  ,curr: $("#pageNum").val()
				  ,count: $("#total").val()//数据总数，从服务端得到
				  ,limit: $("#pageSize").val()//每页的大小
				  ,layout: ['count', 'prev', 'page', 'next', 'limit', 'skip']
				  ,jump: function(obj, first){
				    //obj包含了当前分页的所有参数，比如：
				    console.log(location.hash.replace('#!fenye=', '')); //得到当前页，以便向服务端请求对应页的数据。
				    console.log(obj.limit); //得到每页显示的条数
				    //首次不执行
				    if(!first){
				    	$("#pageNum").val(obj.curr);
				    	 $("#frm").submit();
				    }
				  }
				});
		  }
	}();
	</script>
</body>
</html>