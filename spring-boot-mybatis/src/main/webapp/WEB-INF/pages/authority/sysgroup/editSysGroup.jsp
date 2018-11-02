<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<link rel="stylesheet" href="../common/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="../common/sysuser/css/font_eolqem241z66flxr.css" media="all" />
	<link rel="stylesheet" href="../common/sysuser/css/user.css" media="all" />
</head>
<body class="childrenBody">
	<form class="layui-form" action="${ctx}/sysGroup/saveSysGroup.do" method="post" id="form">
	<blockquote class="layui-elem-quote news_search">
		<div class="layui-row">
		    <div class="layui-col-xs4">
		    	<div class="grid-demo grid-demo-bg1">
		    		<label class="layui-form-label">
		    			岗位代码：
		    		</label>
					<div class="layui-input-inline">
			    		<input type="text" value="" name="groupCode" id="groupCode" lay-verify="groupCode" placeholder="请输入岗位代码" class="layui-input search_input">
			     	</div>
				</div>
		    </div>
		    <div class="layui-col-xs4">
		      <div class="grid-demo"> 
		      		<label class="layui-form-label">
		      			岗位名称：
		      		</label>
		      		<div class="layui-input-inline">
			    		<input type="text" value="" name="GroupName" id="GroupName" lay-verify="GroupName" placeholder="请输入岗位名称" class="layui-input search_input">
			    	</div>
			   </div>
		    </div>
			<div class="layui-col-xs4">
			   <label class="layui-form-label">机构级别：</label>
			    <div class="layui-input-inline">
			      <select name="comLevel" id="comLevel" lay-verify="comLevel">
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
		      <select name="comType" id="comType" lay-verify="comType">
		         <option value="">请选择机构类型</option>
			     <c:forEach items="${comTypeList}" var="one">
                   	 <option value="${one.codeCode}">${one.codeCname}</option>
                 </c:forEach>
		      </select>
		    </div>
		  <button class="layui-btn" lay-submit="" id="add" lay-filter="demo1">保存</button>
		  </div>
  	</div>
	</blockquote>
	<div class="layui-form news_list">
	  	<table id="demo" lay-filter="test"></table>
	</div>
	<div id="page"></div>
	</form>
	<div class="bg" style="position:relative;background:rgba(224,224,224,0.5);width:100%;height:100%;top:-130px;display:none;">
		<div class="alert" style="position: absolute;top:50%;left:50%;margin-left:-163px;">
			
			<button class="layui-btn" id="cSave">继续添加岗位</button>
			<button class="layui-btn" id="addSysRole">为新岗位分配功能</button>
		</div>
	</div>
	<script type="text/javascript" src="../common/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="../common/layui/layui.all.js"></script>
	<script type="text/javascript">
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
		  groupCode: function(value){
	      if(value.length == 0){
		        return '岗位代码不能为空';
		      }
		      if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)){
	              return '不能有特殊字符';
	          }
		      if(!new RegExp("/^[\u2E80-\u9FFF]+$/").test(value)){
		    	  return '不能输入中文';
		      }
		    	  
		  } 
	 	 ,GroupName: function(value){
		      if(value.length == 0){
		        return '岗位名称不能为空';
		      }
		      if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)){
	              return '不能有特殊字符';
	          }
		 }
		  ,comLevel: function(value){
		      if(value.length == 0){
		        return '机构级别不能为空';
		      }
		      if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)){
	              return '不能有特殊字符';
	          }
		 }
		 ,comType: function(value){
		      if(value.length == 0){
		        return '机构类型不能为空';
		      }
		      if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)){
	              return '不能有特殊字符';
	          }
		 }
		 ,content: function(value){
		        layedit.sync(editIndex);
		    }
		 
	  });
	  
	  //监听提交:保存方法
	  form.on('submit(demo1)', function(data){
		  var url="${ctx}/sysGroup/checkGroupCode.do";
		  $.post(url,{groupCode:data.field.groupCode},function(json){
			  if(json.msg == '0'){
					alert('已存在报告名称相同的数据！');
				}else if(json.msg == '1'){
					  var url="${ctx}/sysGroup/saveSysGroup.do";
					  $.post(url,{groupCode:data.field.groupCode, groupName:data.field.GroupName, comLevel:data.field.comLevel, comType:data.field.comType},function(json){
						  if(json.msg == '1'){
							  var saveGroupCode = json.sysgroup.groupCode;
					  		$(".bg").css("display","block");
					  		$("#cSave").click(function() {
					  			window.location.href="${ctx}/sysGroup/editSysGroup.do";
					  		});
					  		$("#addSysRole").click(function() {
					  			window.location.href="${ctx}/sysGroup/querySysRole.do?saveGroupCode="+saveGroupCode;
					  		});
					  		//$("#btn")
					  	    //var url="${ctx}/sysGroup/editsysGroup.do";
						  }
					  },"json");
					  return false;
				}
			  
		  },"json");
		  return false;
	  });
	  
	});

	$(function(){
		 //console.log("1111111");
		//console.log($("#comType"));
 	});
</script>
</body>
</html>