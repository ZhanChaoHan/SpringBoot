<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<title>岗位管理——查询</title>
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
	<form class="layui-form" action="${ctx}/sysGroup/modifySysGroup.do" method="post" id="fm">
	 <input type="hidden" name="updateGroupCode" id="updateGroupCode"/>
	<blockquote class="layui-elem-quote news_search">
		<div class="layui-row">
		    <div class="layui-col-xs4">
		    	<div class="grid-demo grid-demo-bg1">
		    		<label class="layui-form-label">
		    			岗位代码：
		    		</label>
					<div class="layui-input-inline">
			    		<input type="text" value="" name="groupCode" id="groupCode" placeholder="请输入关键字 (模糊)" class="layui-input search_input">
			     	</div>
				</div>
		    </div>
		    <div class="layui-col-xs4">
		      <div class="grid-demo"> 
		      		<label class="layui-form-label">
		      			岗位名称：
		      		</label>
		      		<div class="layui-input-inline">
			    		<input type="text" value="" name="GroupName" id="GroupName" placeholder="请输入关键字 (模糊)" class="layui-input search_input">
			    	</div>
			   </div>
		    </div>
			<div class="layui-col-xs4">
			   <label class="layui-form-label">机构级别：</label>
			    <div class="layui-input-inline">
			      <select name="comLevel" id="comLevel">
			         <option value="">请选择机构级别</option>
				     <c:forEach items="${comLevelList}" var="one">
                     	 <option value="${one.codeCode}">${one.codeCname}</option>
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
                   	 <option value="${one.codeCode}">${one.codeCname}</option>
                 </c:forEach>
		      </select>
		    </div>
		  <a class="layui-btn search_btn" id="btn">查询</a>
		  <a class="layui-btn search_btn" id="btn" onclick="addOne()">添加</a>
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
	$(function(){
		 //console.log("1111111");
		//console.log($("#comType"));
 	});
	
	 //添加岗位
	 function addOne(){
		window.location.href="${ctx}/sysGroup/editSysGroup.do";
	 }
	 
	 //修改岗位
	function modifyOne(obj){
	  console.log(obj.parentNode.parentNode.parentNode.firstChild.firstChild.innerHTML);
	  //获取当前选中行主键
	  var groupCode = obj.parentNode.parentNode.parentNode.firstChild.firstChild.innerHTML;
	  var fmq = document.getElementById("fm");
	  var url = "${ctx}/sysGroup/modifySysGroup.do?updateGroupCode="+groupCode;
	  fmq.action = url;
	  fmq.submit();
	}
	 
	 function groupOne(obj){
		 console.log(obj.parentNode.parentNode.parentNode.firstChild.firstChild.innerHTML);
		  //获取当前选中行主键
		  var groupCode = obj.parentNode.parentNode.parentNode.firstChild.firstChild.innerHTML;
		  var fmq = document.getElementById("fm");
		  var url = "${ctx}/sysGroup/modifySysGroupRole.do?updateGroupCode="+groupCode;
		  fmq.action = url;
		  fmq.submit();
	 }
	
	  //后面就跟你平时使用jQuery一样
	  $("#btn").click(function () {
		  //var groupCode = document.getElementById("groupCode").value;
		  //var url="${ctx}/sysGroup/querySysGroupResult.do?groupCode="+groupCode;
		  var url="${ctx}/sysGroup/querySysGroupResult.do";
		  var param = getParam();
		  $.post(url,param,function(json){
			  //console.log("1111111");
			  layui.use('table', function(){
				  var table = layui.table;
				  console.log(json.groupList);
				  //展示已知数据
				  table.render({
				    elem: '#demo',
				    data: json.groupList,
				    height: 472,
				    cols: [[ //标题栏
				      {field: 'groupCode', title: '岗位代码', width: 210},
				      {field: 'groupName', title: '岗位名称', width: 210},
				      {field: 'comLevelCh', title: '机构级别', width: 210},
				      {field: 'comTypeCh', title: '机构类型', width: 210},
				      {fixed: 'right', width:150, align:'center',width: 400, toolbar: '#barDemo',sort: true} 
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
		     var groupCodeValue = data.groupCode;
		     $("#updateGroupCode").val(groupCodeValue);
		     if(layEvent === 'edit'){
			       console.log(data);
			       var groupCodeValue = data.groupCode;
			       $("#updateGroupCode").val(groupCodeValue);
			       console.info(groupCodeValue);
			       //modifyOne();
		      } else if(layEvent === 'del'){
			     layer.confirm('"\n\r删除该岗位会删除以下信息\n  1、该岗位的系统岗位功能信息\n  2、该该岗位的岗位信息\n\n删除后不可删除，确定执行删除操作？"', function(index){
			    	 var url="../sysGroup/deleteSysGroup.do";
				     $.post(url,{groupCode:data.groupCode},function(json){ 
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
		    } else if(layEvent === 'detail'){
		    	//alert(data.groupCode);
		    	$("#groupCode").val(data.groupCode);
		    	       
		      $(".yincang").css("display","block");
		    }
			});
		});
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
	<script type="text/html" id="barDemo">
 	 <a class="layui-btn layui-btn-primary layui-btn-mini" lay-event="edit" onclick="modifyOne(this)">修改</a>
 	 <a class="layui-btn layui-btn-mini" lay-event="detail" onclick="groupOne(this)">分配功能</a>
 	 <a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="del">删除</a>
	</script>
</body>
</html>