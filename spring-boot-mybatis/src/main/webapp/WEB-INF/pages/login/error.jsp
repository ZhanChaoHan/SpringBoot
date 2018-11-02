<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@include file="/common/pages/taglibs.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title></title> 
		<link rel="stylesheet" href="${ctx }/common/css/error.css"  type="text/css"/>

	</head>
	<body >
		<div class="error_content">
			<div class="error_left">
				 <span class="sp_con">赶紧修，大家等着呢。</span> 
			</div>
			<div class="error_right">
				<div class="error_detail">
					<p class="error_p_title" style="white-space: nowrap;">${error }</p>
					<p class="error_p_con">●别急，工程师正在紧急处理，马上就好。</p>
					<p class="error_p_con">●您可致电010-*****,通知中科软的开发人员!</p>
					<p class="error_p_con">●我们的进步需要您的反馈,感谢您的使用,请您耐心等待!</p>
				</div>
				<div class="btn_error">
					<a class="btn_back1" href="${ctx }//login/index.do">返回首页</a>
					<a class="btn_back2"href="javascript:window.history.back(-1);">返回上一页</a>
				</div>
				 
			</div>
		</div>
	</body>
</html>
