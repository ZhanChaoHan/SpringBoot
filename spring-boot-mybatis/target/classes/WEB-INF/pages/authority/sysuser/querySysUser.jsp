<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@include file="../../taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>用户管理</title>
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
<form class="layui-form"  action="" method="post" id="form">
	<blockquote class="layui-elem-quote news_search">
		<div class="layui-row">
		    <div class="layui-col-xs4">
		    	<div class="grid-demo grid-demo-bg1">
		    		<label class="layui-form-label">
		    			用户代码：
		    		</label>
					<div class="layui-input-inline">
			    		<input type="text" id="userCode" value="" name="userCode" placeholder="请输入关键字 (模糊)" class="layui-input search_input">
			     	</div>
				</div>
		    </div>
		    <div class="layui-col-xs4">
		      <div class="grid-demo"> 
		      		<label class="layui-form-label">
		      			用户姓名：
		      		</label>
		      		<div class="layui-input-inline">
			    		<input type="text" id="userName" value=""  name="userName" placeholder="请输入关键字 (模糊)" class="layui-input search_input">
			    	</div>
			   </div>
		    </div>
		    <div class="layui-col-xs4">
		      <div class="grid-demo">
		      		<label class="layui-form-label">
		      			机构代码：
		      		</label>
	 				<div class="layui-input-inline">
			    		<input type="text" id="comCode" value="" name="comCode" placeholder="请输入关键字 (模糊)" class="layui-input search_input">
			    	</div>
			   </div>
		    </div>
	  	</div>
	  	<div class="layui-row">
		    <div class="layui-col-xs4">
		    	<div class="grid-demo grid-demo-bg1">
		    		<label class="layui-form-label">
		    			机构名称：
		    		</label>
					<div class="layui-input-inline">
			    		<input type="text"  id="comCname" value="" name="comCname" placeholder="请输入关键字 (模糊)" class="layui-input search_input">
			     	</div>
				</div>
		    </div>
		    <div class="layui-col-xs4">
		      <div class="grid-demo"> 
			      	<label class="layui-form-label">
			    			联系电话：
			    	</label>
		      		<div class="layui-input-inline">
			    		<input type="text"  id="telePhone" value="" name="telePhone" placeholder="请输入关键字 (模糊)" class="layui-input search_input">
			    	</div>
			    	
			   </div>
		    </div>
		    <div class="layui-col-xs4">
		      <div class="grid-demo">
		      		<label class="layui-form-label">
			    			用户邮件：
			    	</label>
	 				<div class="layui-input-inline">
			    		<input type="text" id="email" value="" name="email" placeholder="请输入关键字 (模糊)" class="layui-input search_input">
			    	</div>
			   </div>
		    </div>
		    <a class="layui-btn search_btn" id="btn">查询</a>
		    <a class="layui-btn search_btn" onclick="addSysUser()">添加</a>
	  	</div>
	</blockquote>
	</form>
	<div class="layui-form news_list">
	  	<table id="demo" lay-filter="test"></table>
	</div>
	<div id="page"></div>
	<script type="text/javascript" src="../common/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="../common/layui/layui.all.js"></script>
	<script type="text/javascript">
	//进页面是自动查询按钮
	$(document).ready(function(){
		 $('#btn').trigger("click");
	});
	
	//后面就跟你平时使用jQuery一样
	  $("#btn").click(function () {
		  var url="../sysUser/querySysUserReturn.do";
		  var param=getParam();
		  $.post(url,param,function(json){
			  layui.use('table', function(){
				  var table = layui.table;
				  //展示已知数据
				  table.render({
				    elem: '#demo',
				    data:json.sysUserList,
				    height: 472,
				    cols: [[ //标题栏
			    	  {field: 'userCode', title: '用户代码', width: 150, sort: true},
				      {field: 'userName', title: '用户名称', width: 150},
				      {field: 'comCode', title: '机构代码', width: 150},
				      {field: 'comCname', title: '机构名称', width: 150},
				      {field: 'telePhone', title: '电话', width: 150},
				      {field: 'email', title: '邮箱', width: 150},
				      {fixed: 'right', width:150, align:'center',width: 230, toolbar: '#barDemo',sort: true} 
				    ]],
				    skin: 'row', //表格风格
				    even: true,
				    page: true, //是否显示分页
				    limits: [4,5,7,8,10],
				    limit: 10 //每页默认显示的数量
				  });
				});
		  },"json");
	  })
	  //表格的三个按钮
	  layui.use(['table'], function(){
			  var table = layui.table; //表格
			  //监听工具条
			  table.on('tool(test)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
			    var data = obj.data //获得当前行数据
			    ,layEvent = obj.event; //获得 lay-event 对应的值
			    if(layEvent === 'detail'){
				     //分配权限
				     var form = document.getElementById("form");
					 var url = "/sysUser/modifySysUserGroup.do?userCode="+data.userCode;
					 form.action = url;
					 form.submit();
			    } else if(layEvent === 'del'){
				      //删除操作
				      layer.confirm('删除该用户会删除以下信息	1、该用户的岗位关系信息 2、该用户的用户信息删除后不可恢复，确定要执行删除操作？', function(index){
				    	  var url="/sysUser/deleteSysUser.do";
					      $.post(url,{userCode:data.userCode,comCode:data.comCode},function(json){ 
					    	  if(json.number=="1"){
					    		  layer.msg('删除成功');
						    	  obj.del(); //删除对应行（tr）的DOM结构
						    	  $('#btn').trigger("click");
					    	  }else{
					    		  layer.msg('删除失败');
					    	  }
					      },"json");
				        
				        //向服务端发送删除指令
				      });
			    } else if(layEvent === 'edit'){
			    	 //修改操作
			    	 var form = document.getElementById("form");
					 var url = "/sysUser/modifySysUser.do?userCode="+data.userCode;
					 form.action = url;
					 form.submit();
			    }
			  });
			});
	//条件查询获取表单的数据
	  function getParam(){
		var userCode=document.getElementById("userCode").value;
	  	var userName=document.getElementById("userName").value;
	  	var comCode=document.getElementById("comCode").value;
	    var comCname=document.getElementById("comCname").value;
	  	var telePhone=document.getElementById("telePhone").value;
	  	var email=document.getElementById("email").value;
	  	var data={
	  			"userCode" : userCode,
	  			"userName" : userName,
	  			"comCode" : comCode,
	  			"comCname" : comCname,
	  			"telePhone" : telePhone,
	  			"email" : email,
	  	};
	  	return data;
	  }
	
	//点击添加跳转页面
	function addSysUser(){
		window.location.href="/sysUser/editSysUser.do";
	}
	</script>
	<script type="text/html" id="barDemo">
 	 <a class="layui-btn layui-btn-primary layui-btn-mini" lay-event="detail">分配权限</a>
 	 <a class="layui-btn layui-btn-mini" lay-event="edit">编辑</a>
 	 <a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="del">删除</a>
	</script>
</body>
</html>