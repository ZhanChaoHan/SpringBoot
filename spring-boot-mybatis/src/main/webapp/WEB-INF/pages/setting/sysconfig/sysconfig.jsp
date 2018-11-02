<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>系统代码管理页面</title>

<link rel="stylesheet" href="../common/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="../common/css/font_eolqem241z66flxr.css" media="all" />
<script src="../common/layui/layui.js"></script>
<script src="../common/layui/layui.all.js"></script>
<script src="../common/js/jquery-1.9.1.min.js"></script> 
<script>
layui.use('table', function(){
    var table = layui.table;
    
  //监听工具条
    table.on('tool(test)', function(obj){
        var data = obj.data;
       if(obj.event === 'edit'){
        	//弹窗
        	layer.open({
        		  title: '修改数据'
        		  ,btn:false
        	      ,anim: 1
        	      ,skin:'layui-layer-lan'
        	      ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
        	      ,moveType: 1 //拖拽模式，0或者1
        		  ,content:	'<div class="layui-form-item">'+
			      			   ' <label class="layui-form-label">配置名</label>'+
			      			    '<div class="layui-input-inline">'+
			      			     ' <input type="text"   placeholder="'+data.configName+'"  class="layui-input" readonly>'+
			      			    '</div>'+
			  	            '</div>'+
        			  
        			     '<div class="layui-form-item">'+
	        			   ' <label class="layui-form-label">配置值</label>'+
	        			    '<div class="layui-input-inline">'+
	        			     ' <input type="text"   id="configValue1"  placeholder="'+data.configValue+'"  class="layui-input" maxlength="500">'+
	        			    '</div>'+
        			  	  '</div>'+
        			  	  
        			  	'<div class="layui-form-item">'+
		       			   ' <label class="layui-form-label">配置说明</label>'+
		       			    '<div class="layui-input-inline">'+
		       			     ' <input type="text"  id="configExp1"  placeholder="'+data.configExp+'"  class="layui-input" maxlength="500" >'+
		       			    '</div>'+
       			  	   '</div>'+
       			  			
       					'<div class="layui-form-item">'+
			 			   ' <label class="layui-form-label">配置描述</label>'+
			 			    '<div class="layui-input-inline">'+
			 			     ' <input type="text"  id="configDes1"  placeholder="'+data.configDes+'" class="layui-input" maxlength="1000">'+
			 			    '</div>'+
 			  			'</div>'+
 			  		  '<div align="center" id="EidtUv"><button style="width:90px; height:40px" class="layui-btn"  lay-submit="" lay-filter="demo1">确定</button></div>'
        		}); 
        	
        	  //添加修改数据属性
            function getparam(){
            	var configValue1=$("#configValue1").val();
            	var configExp1=$("#configExp1").val();
            	var configDes1=$("#configDes1").val();
            	   var param={
                   		"configName":data.configName,
                   		"configValue":configValue1,
                    	"configExp":configExp1,
                    	"configDes":configDes1,
                    }
            	   return param;
            }
            
        	//点击修改按钮触发
            $("#EidtUv").click(function() { 
            	 var url="../sysConfig/updateMarySelect.do";
            	 var param=getparam();
                 console.log(param)
                 $.post(url,param,function(data){
                	if(data == 1){
                      layer.msg("修改成功", {icon: 1});
                  	  search_btn();
                   }else{
                      layer.msg("修改失败", {icon: 2});
                   }  
                 });
            });
            
        }  
        //工具条末尾
    });
})

//查找语句
      function search_btn(){
    	  var url="../sysConfig/selectMaryList.do";
    	  var param= getDate();
    	  console.log(param);
    	  $.post(url,param,function(result){
    		  console.log(result)
				 layui.use('table', function(){
					  var table = layui.table;
					  //展示已知数据		
					  table.render({
					    elem: '#layui-table',
					     data: result.list
					    ,height:'auto'
					    ,width:'auto'
					    ,id: 'LAY_sysconfig'
					    ,cols: [[ //标题栏
					       {field: 'configName', title: '配置名', width:230}
					      ,{field: 'configValue', title: '配置值', width:230}
					      ,{field: 'configExp', title: '配置说明', width:230}
					      ,{field: 'configDes', title: '配置描述', width:230}
					      ,{field:'right', title: '操作', width:177,toolbar:"#barDemo"}
					    ]] 
					     ,skin: 'row' //表格风格
					     ,even: true
					     ,page: true //是否显示分页
					     ,limits: [5, 7, 10]
					     ,limit: 10 //每页默认显示的数量//表格风格
					  });     
		});
    	  },"json");
      }
      
      function getDate(){
    	  var configName=$("#configName").val();
    	  var configValue=$("#configValue").val();
    	  var configExp=$("#configExp").val();
    	  var configDes=$("#configDes").val();
    	  
    	  var data={
    			  "configName":configName,
    			  "configValue":configValue,
    			  "configExp":configExp,
    			  "configDes":configDes
    	  }
    	  return data
      }
</script>
</head>
<body class="childrenBody">
	<blockquote class="layui-elem-quote news_search">
   <div class="layui-form-inline">
    <div class="layui-inline">
      <label class="layui-form-label">配置名</label>
      <div class="layui-input-inline">
        <input type="tel"  lay-verify="phone" placeholder="请输入配置名（模糊）"  class="layui-input" id="configName">
      </div>
    </div>
    <div class="layui-inline">
      <label class="layui-form-label">配置值</label>
      <div class="layui-input-inline">
        <input type="text"  lay-verify="email" placeholder="请输入值（模糊）" class="layui-input" id="configValue">
      </div>
    </div>
  </div>
  <div class="layui-form-inline">
    <div class="layui-inline">
      <label class="layui-form-label">配置值说明</label>
      <div class="layui-input-inline">
        <input type="text"  lay-verify="required|number" placeholder="请输入配置值说明（模糊）" class="layui-input" id="configExp">
      </div>
    </div>
    <div class="layui-inline">
      <label class="layui-form-label">配置描述</label>
      <div class="layui-input-inline">
        <input type="text" lay-verify="date" placeholder="请输入配置描述（模糊）"  class="layui-input" id="configDes">
      </div>
          <a class="layui-btn search_btn" onclick="search_btn()">查询</a>
    </div>
    
  </div>
  </blockquote>
	<div class="layui-form news_list">
	   <table id="layui-table" lay-filter="test"></table><!-- body部分选取 -->
       <!-- 动态添加工具框 -->
       <script type="text/html" id="barDemo">
          <div class="layui-btn-group">
             <button class="layui-btn layui-btn-small" lay-event="edit">
               <i class="layui-icon">&#xe642;</i>
             </button>
        </div>
       </script>
	</div>
	<div id="page"></div>
</body>
</html>