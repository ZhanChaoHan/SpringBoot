<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<title>机构管理——查询</title>
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
	<form class="layui-form" action="sysGroup/querySysGroupResult.do" method="post" id="form">
	<blockquote class="layui-elem-quote news_search">
		<div class="layui-row">
		    <div class="layui-col-xs4">
		    	<div class="grid-demo grid-demo-bg1">
		    		<label class="layui-form-label">
		    			机构代码：
		    		</label>
					<div class="layui-input-inline">
			    		<input type="text" value="" name="comCode" id="comCode" placeholder="请输入关键字 (模糊)" class="layui-input search_input">
			     	</div>
				</div>
		    </div>
		    <div class="layui-col-xs4">
		      <div class="grid-demo"> 
		      		<label class="layui-form-label">
		      			机构名称：
		      		</label>
		      		<div class="layui-input-inline">
			    		<input type="text" value="" name="comCname" id="comCname" placeholder="请输入关键字 (模糊)" class="layui-input search_input">
			    	</div>
			   </div>
		    </div>
			<div class="layui-col-xs4">
			   <label class="layui-form-label">地区代码：</label>
			    <div class="layui-input-inline">
			      <select name="areaCode" id="areaCode">
			         <option value="">请选择地区</option>
				     <c:forEach items="${sysAreaList}" var="one">
                     	 <option value="${one.areaCode}">${one.areaCname}</option>
                  	  </c:forEach>
			      </select>
			   </div>
			</div>
		    <div class="layui-col-xs4">
		      <div class="grid-demo"> 
		      		<label class="layui-form-label">
		      			机构地址：
		      		</label>
		      		<div class="layui-input-inline">
			    		<input type="text" value="" name="comAddress" id="comAddress" placeholder="请输入关键字 (模糊)" class="layui-input search_input">
			    	</div>
			   </div>
		  <a class="layui-btn search_btn" id="btn">查询</a>
		  <a class="layui-btn search_btn" onclick="addSysCompany()">添加</a>
		  </div>
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
	  var url="/sysCompany/querySysCompanyResult.do";
	  var param = getParam();
	  $.post(url,param,function(json){
		  layui.use('table', function(){
			  var table = layui.table;
			  //展示已知数据
			  table.render({
			    elem: '#demo',
			    data: json.sysCompanyVoList,
			    height: 472,
			    cols: [[ //标题栏
			      {field: 'comCode', title: '机构代码', width: 180},
			      {field: 'comCname', title: '机构名称', width: 180},
			      {field: 'areaCname', title: '地区名称', width: 180},
			      {field: 'comLevel', title: '机构级别', width: 180},
			      {field: 'supComCode', title: '上级机构名称', width: 180},
			      {fixed: 'right', width:150, align:'center',width: 239, toolbar: '#barDemo',sort: true} 
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
	    	 //修改操作
	    	 var form = document.getElementById("form");
			 var url = "/sysCompany/modifySysCompany.do?comCode="+data.comCode;
			 form.action = url;
			 form.submit();
	    } else if(layEvent === 'del'){
		   	var url="/sysCompany/deleteCheckSysCompany.do";
		    $.post(url,{comCode:data.comCode},function(json){ 
		    	if(json.reFlag == "01"){
					alert('该机构含有下属机构，不能删除！');
				}else {
					layer.confirm('\r\n删除该保监局机构会删除以下信息\r\n 1、该保监局机构用户的岗位信息\r\n  2、该保监局机构的用户信息\r\n  3、该保监局机构的信息\r\n\r\n删除后不可恢复，确定执行删除操作？', function(index){
				    	  var url="/sysCompany/deleteSysCompany.do";
					      $.post(url,{comCode:data.comCode},function(json){ 
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
				}
		   },"json");
	    }
		});
	});
		//获取表单的数据
	  function getParam(){
	  	var comCode=document.getElementById("comCode").value;
	  	var comCname=document.getElementById("comCname").value;
	  	var areaCode=document.getElementById("areaCode").value;
	  	var comAddress=document.getElementById("comAddress").value;

	  	var data={
	  			"comCode" : comCode,
	  			"comCname" : comCname,
	  			"areaCode"  : areaCode,
	  			"comAddress"   : comAddress
	  	};
	  	return data;
	  }

	</script>
	<script type="text/html" id="barDemo">
 	 <a class="layui-btn layui-btn-primary layui-btn-mini" lay-event="detail">修改</a>
 	 <a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="del">删除</a>
	</script>
	
	<script type="text/javascript">
	function addSysCompany(){
		window.location.href="/sysCompany/editSysCompany.do";
	}
	</script>
</body>
</html>