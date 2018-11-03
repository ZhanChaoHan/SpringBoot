<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="../../taglibs.jsp" %>
<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>系统配置管理</title>
<link rel="stylesheet" href="../common/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="../common/css/font_eolqem241z66flxr.css" media="all" />
<script src="../common/js/jquery-1.9.1.min.js"></script> 
<script src="../common/layui/layui.js"></script>
<script src="../common/layui/layui.all.js"></script>
<script>
$(document).ready(function(){
	//添加框页面
    $(".childrenBody").on('click','.newsAdd_btn',showSysCode);
	//查询页面
	$(".childrenBody").on('click','.search_btn',selectsyscode)
});

/*删除信息  */
//删除
layui.use('table', function(){
    var table = layui.table;
  //监听工具条
    table.on('tool(test)', function(obj){
        var data = obj.data;
        if(obj.event === 'del'){
            layer.confirm('真的删除行么', function(index){
                console.log(data);
                var url="/sysCode/deleteSysCodes.do";
                var param={
                		"CodeType":data.codeType,
                		"codeCode":data.codeCode,
                }
                console.log(param)
                $.post(url,param,function(data){
                	 if(data == 1){
                    //删除这一行
                     obj.del();
                    //关闭弹框
                     layer.close(index);
                     layer.msg("删除成功", {icon: 6});
                 }else{
                     layer.msg("删除失败", {icon: 5});
                 } 
                });
            });
        } else if(obj.event === 'edit'){
            layer.prompt({
                 formType: 2
                ,title: '修改 代码含义  的字段 为 ['+ data.codeCname +'] 的数据'
                ,value: data.codeCname
            }, function(value, index){
               //这里一般是发送修改的Ajax请求
                EidtUv(data,value,index,obj);
            });
        }
    });
})

function  EidtUv(data,value,index,obj) {
         var url="/sysCode/UpdateSysCode.do";
         var param={
        		"CodeType":data.codeType,
         		"codeCode":data.codeCode,
         		"codeCname":value,
         }
         $.post(url,param,function(data){
        	if(data == 1){
             //关闭弹框
              layer.close(index);
              //同步更新表格和缓存对应的值
              obj.update({
            	  codeCname: value
              });
              layer.msg("修改成功", {icon: 6});
           }else{
              layer.msg("修改失败", {icon: 5});
           }  
         })
}

/* 精准查询信息 */
//查询
function selectsyscode(){
	var scode= $("#scodeCode").val()
	var stype= $("#scodeType").val();
	//全局查询
	if(scode == "" && stype == ""){
		console.log("全局查询")
		var url="/sysCode/SelectById.do";
		var param= getData();
		$.post(url,param,function(result){
			console.log(result);
			var data=eval("("+result+")")//转换数据
			 $("#layui-table").empty(),
			 layui.use('table', function(){
				  var table = layui.table;
				  //展示已知数据		
				  table.render({
				    elem: '#layui-table',
				     data: data.list
				    ,height:'auto'
				    ,width:763,
				    cols: [[ //标题栏
				    	  {checkbox: true, LAY_CHECKED: true}, //默认全选
				        {field: 'codeType', title: '代码类型', width:120}
				      ,{field: 'codeCode', title: '代码名称', width:120}
				      ,{field: 'codeCname', title: '代码含义', width:120}
				      ,{field:'right', title: '操作', width:177,toolbar:"#barDemo"}
				    ]] 
				     ,skin: 'row' //表格风格
				     ,even: true
				     ,page: true //是否显示分页
				     ,limits: [5, 7, 10]
				     ,limit: 7 //每页默认显示的数量//表格风格
				  });     
	});
		})
	}
	
	else if(scode !=""){
	 var url="/sysCode/selectSysCode.do";
	 var param=getData();
	 $.post(url,param,function(result){
		 var data=eval("("+result+")")//转换数据
		 if(data.msg == 0){
			 alert("没有这条信息")
		 }else{
		 $("#layui-table").empty(),
		  layui.use('table', function(){
		  var table = layui.table;
		  //展示已知数据
		  table.render({
		    elem: '#layui-table'
		    ,data: [{
		    	"codeType":data.list.codeType,
		    	"codeCode":data.list.codeCode,
		    	"codeCname":data.list.codeCname,
		    },]
		    ,height:'auto'
		    ,width:763
		    ,cols: [[ //标题栏
		  	  {checkbox: true, LAY_CHECKED: true}, //默认全选
		      ,{field: 'codeType', title: '代码类型', width:120}
		      ,{field: 'codeCode', title: '代码名称', width:120}
		      ,{field: 'codeCname', title: '代码含义', width:120}
		      ,{field:'right', title: '操作', width:177,toolbar:"#barDemo"}
		    ]] ,
		    skin: 'row', //表格风格
		    even: true,
		  });
		});	
		 }
	});
    }
	//模糊查询
	 else{
		console.log("模糊")
		var url="/sysCode/SelectSysCode.do";
		var param= getData();
		$.post(url,param,function(result){
			var data=eval("("+result+")")//转换数据
     		console.log(data)
			if(data.msg==0){
				alert("没有这条信息")
			}else{
				$("#layui-table").empty(),
				 layui.use('table', function(){
					  var table = layui.table;
					  //展示已知数据		
					  table.render({
					    elem: '#layui-table',
					     data: data.list
					    ,height:'auto'
					    ,width:763,
					    cols: [[ //标题栏
					  	  {checkbox: true, LAY_CHECKED: true}, //默认全选
					        {field: 'codeType', title: '代码类型', width:120}
					      ,{field: 'codeCode', title: '代码名称', width:120}
					      ,{field: 'codeCname', title: '代码含义', width:120}
					      ,{field:'right', title: '操作', width:177,toolbar:"#barDemo"}
					    ]] 
					     ,skin: 'row' //表格风格
					     ,even: true
					     ,page: true //是否显示分页
					     ,limits: [5, 7, 10]
					     ,limit: 5 //每页默认显示的数量//表格风格
					  });     
		});
			}
	})
	}
}
//查找表格的数据
function getData(){
	var codeCode=$("#scodeCode").val()
	var codeType=$("#scodeType").val()
	var data={
		"codeCode" : codeCode,
		"CodeType" : codeType,
	}
	return data;
}

//添加框页面
function showSysCode(){
	 $("#layui-table").empty()
	 $("#layui-table").append(
	   '<div class="layui-form-item">'+
	     '<label class="layui-form-label" >代码类型</label>'+
	     '<div class="layui-input-block">'+
	      '<input type="text" name="title" id="CodeType"  autocomplete="off" placeholder="请输入代码类型" class="layui-input">'+
	    '</div>'+  
       '</div>'+
      
	  '<div class="layui-form-item">'+
	    '<label class="layui-form-label">代码名称</label>'+
	    '<div class="layui-input-block">'+
	      '<input type="text" name="username" id="CodeCode"  placeholder="请输入代码名称" autocomplete="off" class="layui-input">'+
	    '</div>'+
	  '</div>'+
	  
	 ' <div class="layui-form-item">'+
	      '<label class="layui-form-label" >代码含义</label>'+
	      '<div class="layui-input-block">'+
	      '<input type="text" name="username"  id="CodeCname"   placeholder="请输入代码含义" autocomplete="off" class="layui-input">'+
	    '</div>'+
	  '</div>'+
	  
	  '<div class="layui-form-item">'+
	    '<div class="layui-input-block">'+
	      '<button class="layui-btn" lay-submit="" lay-filter="demo1" onclick="addSysCode()">立即提交</button>'+
	      '<button type="reset" class="layui-btn layui-btn-primary"  onclick="returnSysCode()">取消添加</button>'+
	    '</div>'+
	  '</div>'
	)
}

//取消按钮
function returnSysCode(){
	 $("#layui-table").empty()
}

/* 添加信息 */
//点击提交按钮添加信息
function addSysCode(){
  	    var param=getParam();
  		var url="/sysCode/addSysCode.do";
  		$.post(url,param,function(result){
  			console.log(result)
  			console.log(result.msg)
  			  if(result.num == 1){
  				alert("该条数据已经存在");
  			 }else
  			 if(result.msg == 1){
  				 alert("添加成功")
  				 $("#layui-table").empty()
  			 }else
  			 if(result.msg == 0){
  				 alert("添加失败")
  			 }
  		 },"json");
}

//获取添加表单的数据
function getParam(){
	var CodeType=document.getElementById("CodeType").value;
	var codeCode=document.getElementById("CodeCode").value;
	var codeCname=document.getElementById("CodeCname").value;
	if(CodeType == ""){
		alert("代码类型不能为空");
		return false;
	}
	if(codeCode == ""){
		alert("代码名称不能为空");
		return false;
	}
	if(codeCname == ""){
		alert("代码含义不能为空");
		return false;
	}
	var data={
			"CodeType" : CodeType,
			"codeCode" : codeCode,
			"codeCname" : codeCname,
	};
	return data;
}
</script>
</head>
<body class="childrenBody">
	<blockquote class="layui-elem-quote news_search">
		<div class="layui-inline">
		    <div class="layui-input-inline">
		    	<input type="text" value="" placeholder="代码类型(模糊)" class="layui-input search_input" id="scodeType">
		    </div>
		    <div class="layui-input-inline">
		    	<input type="text" value="" placeholder="代码名称" class="layui-input search_input" id="scodeCode">
		    </div>
		    <a class="layui-btn search_btn">查询</a>
		</div>
		
		<div class="layui-inline">
			<a class="layui-btn layui-btn-normal newsAdd_btn" value="2">添加</a>
		</div>
	</blockquote>
	<div class="layui-form news_list">
	   <table id="layui-table" lay-filter="test">
       </table>
       <script type="text/html" id="barDemo">
         <a class="layui-btn layui-btn-mini" lay-event="edit">修改</a>
         <a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="del" >删除</a>
       </script>
	</div>
	<div id="page"></div>
</body>
</html>