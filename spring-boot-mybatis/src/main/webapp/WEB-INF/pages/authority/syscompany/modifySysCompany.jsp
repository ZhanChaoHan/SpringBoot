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
	<form class="layui-form" action="${ctx}/sysGroup/querySysGroupResult.do" method="post" id="form">
	<blockquote class="layui-elem-quote news_search">
		<input type="hidden" id="comType1" name="comType1" />
			<div class="layui-row">
		    <div class="layui-col-xs4">
		    	<div class="grid-demo grid-demo-bg1">
		    		<label class="layui-form-label">
		    			机构代码：
		    		</label>
					<div class="layui-input-inline">
			    		<input type="text" value="${comVo.comCode }" readonly="readonly"  name="comCode" id="comCode" placeholder="请输入关键字 (模糊)" class="layui-input search_input">
			     	</div>
				</div>
		    </div>
		    <div class="layui-col-xs4">
		      <div class="grid-demo"> 
		      		<label class="layui-form-label">
		      			机构名称：
		      		</label>
		      		<div class="layui-input-inline">
			    		<input type="text" value="${comVo.comCname }"  lay-verify="comCname" name="comCname" id="comCname"  class="layui-input search_input">
			    	</div>
			   </div>
		    </div>
			<div class="layui-col-xs4">
			   <label class="layui-form-label">地区代码：</label>
			    <div class="layui-input-inline">
		    		<c:if test="${areaCode_isModify == '01'}">
				    		<select name="areaCode" id="areaCode" >
						         <option value="">请选择地区</option>
							     <c:forEach items="${fanyiArea}" var="one">
							     	<c:if test="${one.areaCode==comVo.areaCode}">
			                     	 	<option value="${one.areaCode}" selected="selected">${one.areaCname}</option>
			                     	 </c:if>
			                     	 <c:if test="${one.areaCode!=comVo.areaCode}">
			                     	 	<option value="${one.areaCode}" >${one.areaCname}</option>
			                     	 </c:if>
			                  	 </c:forEach>
				      		</select>
					</c:if>
					<c:if test="${areaCode_isModify == '00'}">
							<select name="areaCode" id="areaCode" >
						         <option value="">请选择地区</option>
							     <c:forEach items="${sysAreaList}" var="one">
							     	<c:if test="${one.areaCode==comVo.areaCode}">
			                     	 	<option value="${one.areaCode}" selected="selected">${one.areaCname}</option>
			                     	 </c:if>
			                     	 <c:if test="${one.areaCode!=comVo.areaCode}">
			                     	 	<option value="${one.areaCode}" >${one.areaCname}</option>
			                     	 </c:if>
			                  	 </c:forEach>
					        </select>
					</c:if>
			   </div>
			</div>
		    <div class="layui-col-xs4">
		      <div class="grid-demo"> 
		      		<label class="layui-form-label">
		      			机构地址：
		      		</label>
		      		<div class="layui-input-inline">
			    		<input type="text" value="${comVo.comAddress }"  lay-verify="comAddress" name="comAddress" id="comAddress" placeholder="请输入关键字 (模糊)" class="layui-input search_input">
			    	</div>
			   </div>
		  </div>
		   <div class="layui-col-xs4">
			   <label class="layui-form-label">机构级别：</label>
			    <div class="layui-input-inline">
			    	<c:if test="${comLevel_isModify == '01'}">
			    		<select name="comLevel" id="comLevel">
					         <option value="">请选择机构级别</option>
						     <c:forEach items="${fanyiComLevel}" var="one">
						     	<c:if test="${one.codeCode==comVo.comLevel}">
		                     	 	<option value="${one.codeCode}" selected="selected">${one.codeCname}</option>
		                     	 </c:if>
		                     	 <c:if test="${one.codeCode!=comVo.comLevel}">
		                     	 	<option value="${one.codeCode}" selected="selected">${one.codeCname}</option>
		                     	 </c:if>
		                  	  </c:forEach>
					      </select>
					</c:if>
					<c:if test="${comLevel_isModify == '00'}">
						 <select name="comLevel" id="comLevel">
					         <option value="">请选择机构级别</option>
						     <c:forEach items="${comLevelList}" var="one">
						     	<c:if test="${one.codeCode==comVo.comLevel}">
		                     	 	<option value="${one.codeCode}" selected="selected">${one.codeCname}</option>
		                     	 </c:if>
		                     	 <c:if test="${one.codeCode!=comVo.comLevel}">
		                     	 	<option value="${one.codeCode}" selected="selected">${one.codeCname}</option>
		                     	 </c:if>
		                  	  </c:forEach>
					      </select>
					</c:if>
			   </div>
			</div>
			 <div class="layui-col-xs4">
		      <div class="grid-demo"> 
		      		<label class="layui-form-label">
		      			机构电话：
		      		</label>
		      		<div class="layui-input-inline">
			    		<input type="text" value="${comVo.comphone }" lay-verify="phone" name="comPhone" id="comPhone" placeholder="请输入关键字 (模糊)" class="layui-input search_input">
			    	</div>
			   </div>
		  </div>
		  <div class="layui-col-xs4">
			   <label class="layui-form-label">上级机构：</label>
			    <div class="layui-input-inline">
				    <c:if test="${supComCode_isModify == '01'}">
				    	<select name="supComCode" id="supComCode">
					         <option value="">请选择上级机构</option>
						     <c:forEach items="${fanyiSup}" var="one">
							     <c:if test="${one.comCode==comVo.supComCode}">
			                     	 <option value="${one.comCode}" selected="selected">${one.comCname}</option>
							     </c:if>
							     <c:if test="${one.comCode!=comVo.supComCode}">
			                     	 <option value="${one.comCode}" selected="selected">${one.comCname}</option>
							     </c:if>
		                  	  </c:forEach>
					      </select>
					</c:if>
					<c:if test="${supComCode_isModify == '00'}">
						<select name="supComCode" id="supComCode">
					         <option value="">请选择上级机构</option>
						     <c:forEach items="${supComCodelist}" var="one">
						     	<c:if test="${one.comCode==comVo.supComCode}">
		                     	 <option value="${one.comCode}" selected="selected">${one.comCname}</option>
						     	</c:if>
						     	<c:if test="${one.comCode!=comVo.supComCode}">
		                     	 <option value="${one.comCode}" selected="selected">${one.comCname}</option>
						     	</c:if>
		                  	  </c:forEach>
					      </select>
					</c:if>
			   </div>
			</div>
  			</div>
  			
  			<button class="layui-btn" lay-submit="" id="update" lay-filter="demo1">立即提交</button>
			<button type="reset" class="layui-btn layui-btn-primary"  id="return" onclick="updateReturn()">返回</button>
	</blockquote>
	</form>

	<div class="layui-form news_list">
	  	<table id="demo" lay-filter="test"></table>
	</div>
	<div id="page"></div>

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
			 comCname: function(value){
			      if(value.length ==0){
				        return '机构名称能为空';
				      }
			      if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)){
		                return '不能有特殊字符';
		            }
				}
			,comAddress: function(value){
			      if(value.length ==0){
				        return '机构地址不能为空';
				      }
			      if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)){
		                return '不能有特殊字符';
		            }
				}
			,content: function(value){
		        layedit.sync(editIndex);
		    }
		  });
		  //监听提交
		  form.on('submit(demo1)', function(data){
			  var url="/sysCompany/updateSysCompany.do";
			  $.post(url,{comCode:data.field.comCode,comCname:data.field.comCname,areaCode:data.field.areaCode,comAddress:data.field.comAddress,comLevel:data.field.comLevel,comPhone:data.field.comPhone,supComCode:data.field.supComCode},function(json){ 
				  alert("修改成功");
				  //关闭并清楚弹层
				  $('#return').trigger("click");
		      },"json");
			    return false;
		  });
		});
		
	//返回机构查询页面
	function updateReturn(){
		window.location.href="${ctx}/sysCompany/querySysCompany.do";
	}
</script>
<script type="text/html" id="barDemo">
 	 <a class="layui-btn layui-btn-primary layui-btn-mini" lay-event="detail">修改</a>
 	 <a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="del">删除</a>
</script>

</body>
</html>