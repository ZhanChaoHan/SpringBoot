<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="../../taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>用户管理-添加</title>
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
<form action="/sysUser/querySysUserReturn.do" class="layui-form" method="post" id="from">
	<blockquote class="layui-elem-quote news_search">
		<div class="layui-row">
		    <div class="layui-col-xs4">
		    	<div class="grid-demo grid-demo-bg1">
		    		<label class="layui-form-label">
		    			用户代码：
		    		</label>
					<div class="layui-input-inline">
			    		<input type="text" id="userCode" lay-verify="userCode"  name="userCode" class="layui-input search_input">
			     	</div>
				</div>
		    </div>
		    <div class="layui-col-xs4">
			   <label class="layui-form-label">机构代码：</label>
			    <div class="layui-input-inline">
			      <select name="comCode" id="comCode" lay-verify="comCode">
			         <option value="">请选择机构代码</option>
				     <c:forEach items="${sysCompanyList}" var="one">
                     	 <option value="${one.comCode}">${one.comCname}</option>
                  	  </c:forEach>
			      </select>
			   </div>
			</div>
		    <div class="layui-col-xs4">
		      <div class="grid-demo"> 
			      	<label class="layui-form-label">
			    			用户姓名：
			    	</label>
		      		<div class="layui-input-inline">
			    		<input type="text"  id="userName" lay-verify="userName" name="userName" class="layui-input search_input">
			    	</div>
			   </div>
		    </div>
		    <div class="layui-col-xs4">
		      <div class="grid-demo">
		      		<label class="layui-form-label">
			    			用户密码：
			    	</label>
	 				<div class="layui-input-inline">
			    		<input type="password" id="passWord" lay-verify="passWord" value="" name="passWord"  class="layui-input search_input">
			    	</div>
			   </div>
		    </div>
		      <div class="layui-col-xs4">
		      <div class="grid-demo">
		      		<label class="layui-form-label">
			    			密码确认：
			    	</label>
	 				<div class="layui-input-inline">
			    		<input type="password" id="passWord1"  lay-verify="passWord1" value="" name="passWord1" class="layui-input search_input">
			    	</div>
			   </div>
		    </div>
		     <div class="layui-col-xs4">
		      <div class="grid-demo"> 
			      	<label class="layui-form-label">
			    			联系电话：
			    	</label>
		      		<div class="layui-input-inline">
			    		<input type="text"  id="telePhone"  lay-verify="phone" value="" name="telePhone"  class="layui-input search_input">
			    	</div>
			    	
			   </div>
		    </div>
		</div>
		<div class="layui-row">
		   
		    <div class="layui-col-xs4">
		      <div class="grid-demo">
		      		<label class="layui-form-label">
			    			用户邮件：
			    	</label>
	 				<div class="layui-input-inline">
			    		<input type="text" id="email"  lay-verify="email" value="" name="email"  class="layui-input search_input">
			    	</div>
			   </div>
		    </div>
		</div>
		<button class="layui-btn" lay-submit="" id="add" lay-filter="demo1">立即提交</button>
		<button type="reset" class="layui-btn layui-btn-primary"  id="return" onclick="addReturn()">返回</button>
	</blockquote>
	</form>
	<div class="layui-form news_list">
	  	<table id="demo" lay-filter="test"></table>
	</div>
	<div id="page"></div>
	<script type="text/javascript" src="../common/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="../common/layui/layui.all.js"></script>
	<script  type="text/javascript">
	layui.use(['form', 'layedit', 'laydate'], function(){
		  var form = layui.form
		  ,layer = layui.layer
		  ,layedit = layui.layedit
		  ,laydate = layui.laydate;
		  
		  //日期
		  laydate.render({
		    elem: '#date'
		  });
		  laydate.render({
		    elem: '#date1'
		  });
		  
		  //创建一个编辑器
		  var editIndex = layedit.build('LAY_demo_editor');
		 
		  //自定义验证规则
		  form.verify({
			  userCode: function(value){
		      if(value.length ==0){
		        return '用户代码不能为空';
		      }
		      if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)){
	                return '不能有特殊字符';
	            }
		    }
		    ,comCode: function(value){
		      if(value.length ==0){
			        return '机构代码不能为空';
			      }
			    if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)){
	                return '不能有特殊字符';
	            }
		  	}
		    ,userName: function(value){
		      if(value.length ==0){
			        return '用户姓名不能为空';
			      }
			    if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)){
	                return '不能有特殊字符';
	            }
		    }
		    ,passWord: function(value){
			      if(value.length ==0){
				        return '密码不能为空';
				      }
				    }
		    ,passWord: [/(.+){6,12}$/, '密码必须6到12位']
		    ,passWord1: function(value){
		    	  var passWord=document.getElementById("passWord").value;
			      if(value != passWord){
				        return '输入密码不一致密码';
				      }
				    }
		    ,content: function(value){
		        layedit.sync(editIndex);
		    }
		  });
		  
		  //监听提交
		  form.on('submit(demo1)', function(data){
				var url="/sysUser/checkUserCode.do";
				 $.post(url,{userCode:data.field.userCode},function(json){
					 if(json.msg==1){
						 //保存用户
						 var url="/sysUser/addSysUser.do";
							  $.post(url,{userCode:data.field.userCode, userName:data.field.userName, comCode:data.field.comCode, passWord:data.field.passWord, telePhone:data.field.telePhone, email:data.field.email},function(json){ 
								  alert("保存成功");
								  //关闭并清空弹框的值
								  $('#return').trigger("click");
						      },"json");
					 }else{
						 alert("用户代码重复");
					 }
				 },"json");
				 
			    return false;
		  });
		});
		//点击返回跳转到查询页面
		function addReturn(){
			window.location.href="/sysUser/querySysUser.do";
		}
	</script>
</body>
</html>