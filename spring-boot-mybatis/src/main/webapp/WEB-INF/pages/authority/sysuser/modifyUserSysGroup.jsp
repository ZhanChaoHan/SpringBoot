<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="../../taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>用户管理-分配权限</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="../common/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="../common/sysuser/css/font_eolqem241z66flxr.css" media="all" />
	<link rel="stylesheet" href="../common/sysuser/css/user.css" media="all" />
	<link rel="stylesheet" href="../common/sysuser/css/Standard.css" media="all" />
</head>
<body class="childrenBody">
<form action="../sysUser/querySysUserReturn.do" class="layui-form" method="post" id="from">
	<input type="hidden" id="userCode"name="userCode" value="${sysUser.userCode }" />
	<blockquote class="layui-elem-quote news_search">
			<table class="common" cellspacing="1" cellpadding="5">
			<tr>
				<td  class="title0" colspan="4"><B>为保监局用户：${sysUser.userCode }(${sysUser.userName }) 修改岗位信息</B></td>
			</tr>
			<tr class="listtitle">
				<td  style="width:20%" align="center">选择</td>
				<td  style="width:30%" align="center">岗位名称</td>
				<td  style="width:20%" align="center">选择</td>
				<td  style="width:30%" align="center">岗位名称</td>
			</tr>
			<c:set var="index" value="0"></c:set>
			<c:forEach items="${sysGroupList}" var="one">
				<c:set var="check" value="false"></c:set>
				<c:forEach items="${userGroupsList}" var="two">
						<c:if test="${one.groupCode == two.groupCode}">
							<c:set var="check" value="true"></c:set>
						</c:if>
				</c:forEach>
				<c:if test="${index%2==0}">
					<tr>
				</c:if>
				<c:choose>
					<c:when  test="${check == true}" >
						<td class="input" style="width:20%" align="center"><input id="isCheck" type="checkbox" name="selected" value="${one.groupCode}" checked="checked" ></td>
					</c:when>	
					<c:otherwise>
						<td class="input" style="width:20%" align="center"><input id="isCheck" type="checkbox" name="selected" value="${one.groupCode}" ></td>
   					</c:otherwise>
				</c:choose>
					<td class="input" style="width:30%" >${one.groupName}</td>
				<c:set var="index" value="${index+1}"></c:set>	
				<c:if test="${index % 2 == 0}">
				</c:if>
			</c:forEach>
			<c:if test="${index % 2 == 1}">
				<td class="input" colspan="1" style="width:20%"></td>
				<td class="input" colspan="1" style="width:30%"></td>
			</c:if>
				</table>
				<br>
				<table align="center" width="100%">
				<tr>
					<td align="center" ><input class="button" type="button" id="but1" value="保 存" onclick="submitForm()"></td>
					<td align="center" ><input class="button" type="button" id="but2" value="返 回" onclick="addReturn()"></td>
				</tr>
			</table>
	</blockquote>
	</form>
	<div class="layui-form news_list">
	  	<table id="demo" lay-filter="test"></table>
	</div>
	<div id="page"></div>
	<script type="text/javascript" src="../common/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="../common/layui/layui.all.js"></script>
	<script  type="text/javascript">
	function submitForm(){
		var url = "/sysUser/updateSysUserGroup.do";
		from.action = url;
		from.submit();
	}
	//点击返回跳转到查询页面
	function addReturn(){
		window.location.href="/sysUser/querySysUser.do";
	}
	</script>
</body>
</html>