<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
                var url="../sysCode/deleteSysCodes.do";
                var param={
                		"CodeType":data.codeType,
                		"codeCode":data.codeCode,
                }
                $.post(url,param,function(data){
                	var json = eval("("+data+")");//转换json格式字符串
                	if(json.msg1 == 0){
                	  layer.msg("这条数据已经被删除", {icon: 2});
                	}
                	else if(json.msg1 == 1){
                    //删除这一行
                     obj.del();
                    //关闭弹框
                     layer.close(index);
                     layer.msg("删除成功", {icon: 1});
                     selectsyscode()
                 }else{
                     layer.msg("删除失败", {icon: 2});
                 } 
                });
            });
        } else if(obj.event === 'edit'){
        	//弹窗
        	layer.open({
        		  title: '修改数据'
        		  ,btn:false
        	      ,anim: 1
        	      ,skin:'layui-layer-lan'
        	      ,id: 'LAY_syscode' //设定一个id，防止重复弹出
        	      ,moveType: 1 //拖拽模式，0或者1
        		  ,content:'<div class="layui-form-item">'+
	        			   ' <label class="layui-form-label">代码类型</label>'+
	        			    '<div class="layui-input-inline">'+
	        			     ' <input  placeholder="'+data.codeType+'"  class="layui-input" readonly>'+
	        			    '</div>'+
        			  	  '</div>'+
        			  	  
        			  	'<div class="layui-form-item">'+
		       			   ' <label class="layui-form-label">代码名称</label>'+
		       			    '<div class="layui-input-inline">'+
		       			     ' <input   placeholder="'+data.codeCode+'"  class="layui-input" readonly>'+
		       			    '</div>'+
       			  	   '</div>'+
       			  			
       					'<div class="layui-form-item">'+
			 			   ' <label class="layui-form-label">代码含义</label>'+
			 			    '<div class="layui-input-inline">'+
			 			     ' <input type="text"  id="demo3"  placeholder="'+data.codeCname+'" class="layui-input" maxlength="300">'+
			 			    '</div>'+
 			  			'</div>'+
 			  		  '<div align="center" id="Eidtbtn" ><button style="width:90px; height:40px" class="layui-btn"  lay-submit="" lay-filter="demo1">确定</button></div>'
        		}); 
        	
        	function getparam(){
        		  var code=$("#demo3").val()
        		  var param={
      	        		"CodeType":data.codeType,
      	         		"codeCode":data.codeCode,
      	         		"codeCname":code,
      	         }
        		  if(code==""){
        			  alert("修改的信息不能为空")
        			  return false;
        		  }
        		  return param;
        	}
        	
        	//执行修改信息
        	$("#Eidtbtn").click(function () {
        	         var url="../sysCode/updateByPrimaryKeySelective.do";
        	         var param=getparam();
        	         console.log(param)
        	         $.post(url,param,function(data){
        	        	if(data == 1){
        	              layer.msg("修改成功", {icon: 1});
        	              selectsyscode()
        	           }else{
        	              layer.msg("修改失败", {icon: 2});
        	           }  
        	         })
        	})
        }
    });
})



//查询
function selectsyscode(){
		var url="../sysCode/selectByPrimaryKeyList.do";
		var param= getData();
		console.log(param)
		$.post(url,param,function(result){
			var obj = JSON.parse(result);
			//使用数据表格框架
			 layui.use('table', function(){
				  var table = layui.table;
				  //展示已知数据		
				  table.render({
				    elem: '#layui-table',
				     data: obj.list
				    ,height:'auto'
				    ,width:'auto',
				    cols: [[ //标题栏
				      {field: 'codeType', title: '代码类型', width:290}
				      ,{field: 'codeCode', title: '代码名称', width:290}
				      ,{field: 'codeCname', title: '代码含义', width:290}
				      ,{field:'right', title: '操作', width:270,toolbar:"#barDemo"}
				    ]] 
				     ,skin: 'row' //表格风格
				     ,even: true
				     ,page: true //是否显示分页
				     ,limits: [5, 7, 10]
				     ,limit: 10 //每页默认显示的数量//表格风格
				  });     
	});
		})
}
//查找表格的数据
function getData(){
	var codeCode=$("#scodeCode").val()
	var codeType=$("#scodeType").val()
	var codeCname=$("#scodeCname").val()
	var data={
		"codeCode" : codeCode,
		"CodeType" : codeType,
		"codeCname": codeCname,
	}
	return data;
}



//添加框页面
function showSysCode(){
	 //示范一个公告层
    layer.open({
       type: 1
      ,closeBtn: false
      ,title:'添加信息'
      ,anim: 1
      ,skin:'layui-layer-lan'
      ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
      ,btn: ['确定', '取消']
      ,btnAlign: 'c'
      ,moveType: 1 //拖拽模式，0或者1
      ,content:  '<div class="layui-form-item">'+
				     '<label class="layui-form-label" >代码类型</label>'+
				     '<div class="layui-input-inline">'+
				      '<input type="text"  id="CodeType"  autocomplete="off" placeholder="请输入代码类型" class="layui-input" maxlength="50">'+
				    '</div>'+  
      			 '</div>'+
      
				  '<div class="layui-form-item">'+
				    '<label class="layui-form-label">代码名称</label>'+
				    '<div class="layui-input-inline">'+
				      '<input type="text"  id="CodeCode"  placeholder="请输入代码名称"  class="layui-input" maxlength="50">'+
				    '</div>'+
				  '</div>'+
			  
				 '<div class="layui-form-item">'+
				      '<label class="layui-form-label" >代码含义</label>'+
				    '<div class="layui-input-inline">'+
				      '<input type="text"  id="CodeCname"   placeholder="请输入代码含义"  class="layui-input" maxlength="300">'+
				    '</div>'+
				  '</div>',
	  /* 添加信息 */
	 //点击提交按钮添加信息
	    yes:function(index, layero){
	    	  var param=getParam();
	    		var url="../sysCode/insertSelective.do";
	    		$.post(url,param,function(result){
	    			console.log(result)
	    			  if(result.num == 1){
	    				layer.msg("添加失败", {icon: 2});
	    				layer.alert("该条数据已经存在",{icon:6});
	    			 }else
	    			 if(result.msg == 1){
	    			   layer.msg("添加成功", {icon: 1});
	    				 $("#layui-table").empty()
	    				 selectsyscode()
	    			 }else
	    			 if(result.msg == 0){
	    			     layer.msg("添加失败", {icon:2 });
	    			 }
	    		 },"json");
	        layer.close(index); //如果设定了yes回调，需进行手工关闭 
	      }
    });
}



//获取添加表单的数据
function getParam(){
	var CodeType=document.getElementById("CodeType").value;
	var codeCode=document.getElementById("CodeCode").value;
	var codeCname=document.getElementById("CodeCname").value;
	if(CodeType == "" ){
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
		    	<input type="text" value="" placeholder="代码名称(模糊)" class="layui-input search_input" id="scodeCode">
		    </div>
		    <div class="layui-input-inline">
		    	<input type="text" value="" placeholder="代码含义(模糊)" class="layui-input search_input" id="scodeCname">
		    </div>
		    <a class="layui-btn search_btn">查询</a>
		</div>
		
		<div class="layui-inline">
			<a class="layui-btn layui-btn-normal newsAdd_btn" >添加</a>
		</div>
	</blockquote>
	<div class="layui-form news_list">
	   <table id="layui-table" lay-filter="test">
       </table>
       <script type="text/html" id="barDemo">
         <div class="layui-btn-group">
             <button class="layui-btn layui-btn-small" lay-event="edit">
               <i class="layui-icon">&#xe642;</i>
             </button>
            <button class="layui-btn layui-btn-small"  lay-event="del">
              <i class="layui-icon">&#xe640;</i>
            </button>
        </div>
       </script>
	</div>
	<div id="page"></div>
</body>
</html>