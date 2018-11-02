<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../taglibs.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>权限管理系统</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="../common/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="../common/css/login.css" media="all" />
</head>

<body style="height: 100%;">
	<div class="login">
		<h1>通用权限管理登录</h1>
		<form class="layui-form" action="${ctx}/login/login.do" method="post"
			id="frm">
			<div class="layui-form-item">
				<input class="layui-input" name="usercode" placeholder="用户名"
					lay-verify="required" type="text" autocomplete="off">
			</div>
			<div class="layui-form-item">
				<input class="layui-input" name="password" placeholder="密码"
					lay-verify="required" type="password" autocomplete="off">
			</div>
			<div class="layui-form-item form_code">
				<input class="layui-input" name="randomCode" placeholder="验证码"
					lay-verify="required" type="text" autocomplete="off">
				<div class="code">
					<img onclick="changeValidateCode()" id="randomImg" width="116"
						height="38" style="margin-top: -1px;">
				</div>
			</div>
			<button class="layui-btn login_btn" lay-submit="" lay-filter="login">登录</button>
		</form>
		<input type="hidden" value="${msg}" id="msg" />
	</div>
	<canvas id=c></canvas>
	<script type="text/javascript" src="../common/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="../common/layui/layui.all.js"></script>
	<script src='../common/js/dat.gui.min.js'></script>
	<script src="../common/js/blackhole-index.js"></script>
	<!-- <script type="text/javascript" src="../common/js/login-login.js"></script> -->
	<script type="text/javascript">
		$(function(){
			 if (window != top)   
				    top.location.href = location.href; 
				//更换验证码
				changeValidateCode();
			});
		layui.config({
			base : "js/"
		}).use(['form','layer'],function(){
			  var layer = layui.layer
			  ,form = layui.form;
				layer = parent.layer === undefined ? layui.layer : parent.layer,
				$ = layui.jquery;
			
			var msg = $("#msg").val();
			
			if(msg!=null&&msg!=""){
				var index = layer.msg(msg, {
					  icon: 5,
					  time: 2000 //2秒关闭（如果不配置，默认是3秒）
					}, function(){
					}); 
			}
			
			//登录按钮事件
			form.on("submit(login)",function(data){
				
			})
		})
		function changeValidateCode() {		
			var img = document.getElementById('randomImg');
			//获取当前的时间作为参数，无具体意义    
			var timenow = new Date().getTime();
			//每次请求需要一个不同的参数，否则可能会返回同样的验证码    
			//这和浏览器的缓存机制有关系，也可以把页面设置为不缓存，这样就不用这个参数了。   
			img.src = "/login/getVerificationCode.do?d=" + timenow;
		}
	</script>
</body>
</html>