<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../../taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<title>岗位管理——修改</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="../common/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="../common/sysuser/css/font_eolqem241z66flxr.css" media="all" />
	<link rel="stylesheet" href="../common/sysuser/css/user.css" media="all" />
</head>
<body class="childrenBody">
	<form class="layui-form" action="/sysGroup/saveUpdateSysGroup.do" method="post" id="form">
	<blockquote class="layui-elem-quote news_search">
		<div class="layui-row">
		    <div class="layui-col-xs4">
		    	<div class="grid-demo grid-demo-bg1">
		    		<label class="layui-form-label">
		    			岗位代码：
		    		</label>
					<div class="layui-input-inline">
			    		<input type="text"  name="groupCode" id="groupCode" value="${queryUpdate.groupCode}"  placeholder="请输入岗位代码" class="layui-input search_input" disabled="disabled"/>
			     	</div>
				</div>
		    </div>
		    <div class="layui-col-xs4">
		      <div class="grid-demo"> 
		      		<label class="layui-form-label">
		      			岗位名称：
		      		</label>
		      		<div class="layui-input-inline">
			    		<input type="text" name="GroupName" id="GroupName" value="${queryUpdate.groupName}" placeholder="请输入岗位名称" class="layui-input search_input">
			    	</div>
			   </div>
		    </div>
			<div class="layui-col-xs4">
			   <label class="layui-form-label">机构级别：</label>
			    <div class="layui-input-inline">
			      <select name="comLevel" id="comLevel">
			         <option value="">请选择机构级别</option>
				     <c:forEach items="${comLevelList}" var="one">
				     	<c:if test="${one.codeCode==queryUpdate.comLevel}">
                     	 <option value="${one.codeCode}" selected="selected">${one.codeCname}</option>
				     	</c:if>
				     	<c:if test="${one.codeCode!=queryUpdate.comLevel}">
                     	 <option value="${one.codeCode}">${one.codeCname}</option>
				     	</c:if>
                  	  </c:forEach>
			      </select>
			   </div>
			</div>
		    <div class="layui-form-item">
		    <label class="layui-form-label">机构类型：</label>
		    <div class="layui-input-inline">
		      <select name="comType" id="comType">
		         <option value="">请选择机构类型</option>
			     <c:forEach items="${comTypeList}" var="one">
			     	<c:if test="${one.codeCode==queryUpdate.comType}">
                   	 <option value="${one.codeCode}" selected="selected">${one.codeCname}</option>
			     	</c:if>
			     	<c:if test="${one.codeCode!=queryUpdate.comType}">
                   	 <option value="${one.codeCode}" >${one.codeCname}</option>
			     	</c:if>
                 </c:forEach>
		      </select>
		    </div>
		  <a class="layui-btn search_btn" id="btn">保存</a>
		  </div>
  	</div>
	</blockquote>
	<div class="layui-form news_list">
	  	<table id="demo" lay-filter="test"></table>
	</div>
	<div id="page"></div>
	</form>
	<script type="text/javascript" src="../common/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="../common/layui/layui.all.js"></script>
	<script type="text/javascript">
	//保存方法
    $("#btn").click(function () {
		  var url="/sysGroup/saveUpdateSysGroup.do";
		  var param = getParam();
		  $.post(url,param,function(json){
			  if(json.msg=="1"){
		  		alert("修改成功");  
		  		window.location.href="/sysGroup/querySysGroup.do";
			  }
		  },"json");
		  
	})
		//获取表单的数据
	  function getParam(){
	  	var groupCodeValue=document.getElementById("groupCode").value;
	  	var groupNameValue=document.getElementById("GroupName").value;
	  	var comLevelValue=document.getElementById("comLevel").value;
	  	var comTypeValue=document.getElementById("comType").value;

	  	var data={
	  			"groupCode" : groupCodeValue,
	  			"groupName" : groupNameValue,
	  			"comLevel"  : comLevelValue,
	  			"comType"   : comTypeValue
	  	};
	  	return data;
	  }
	</script>
</body>
</html>