<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.sinosoft.normal.po.SysRole" %>
<%@include file="../../taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
	<title>岗位管理——添加</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link href="../common/dtree/dtree.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="../common/dtree/dtree.js"></script>
    <script type="text/javascript" src="../common/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" >
	function goback(){
		window.location.href="/sysGroup/editSysGroup.do";
	}	
	</script>
</head>
<body>

	<form id="fm" method="post">
		<input type="hidden" name="groupCode" id="groupCode" value="${groupCode}">
	<div id="divTree" >
	<script>
		var d = new dTree('d');
		d.add('0','-1','功能列表','','','','','','',false,false,false);
	<%
			List list = (List)request.getAttribute("list");
		    SysRole role = null;
		    for(int i=0; i<list.size(); i++){
		    	role = (SysRole)list.get(i);
				out.println("d.add('"+role.getRoleCode()+"','"+role.getUpperRoleCode()+"','"+role.getRoleName()+"','','','','','','',true,false,false);"); 
			}
		%>
		document.write(d);
	</script>
</div>
<br>
<table width="100%">
<tr>
	<td align="center" ><input class="button" type="button" id="save" value="保 存"></td>
	<td align="center" ><input class="button" type="button" id="but2" value="返回 " onclick="goback()"></td>
</tr>
</table>
</form>
<script type="text/javascript" src="../common/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
var treeCheckBox ="";
//保存方法
$("#save").click(function () {
     var groupCodeValue=document.getElementById("groupCode").value;
	 $("input:checkbox[name='treeCheckBox']:checked").each(function() { // 遍历name=test的多选框
	 treeCheckBox += $(this).val() + ",";  // 每一个被选中项的值
	});
	 if(treeCheckBox == null || treeCheckBox ==""){
		 alert("请添加岗位权限");
	 }else{
  		  var url="/sysGroup/saveSysGroupRole.do";
	      $.post(url,{groupCode:groupCodeValue,treeCheckBox:treeCheckBox},function(json){
			  if(json.msg == "1"){
				  alert("添加成功");
				  window.location.href="/sysGroup/editSysGroup.do";
			  }
		  },"json");
		 }
});
</script>
</body>
</html>
