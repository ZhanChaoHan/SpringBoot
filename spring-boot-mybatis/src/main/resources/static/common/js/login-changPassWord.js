var form;
var $;
var ctx;


;!function(){
	form = layui.form;
	var layer = parent.layer === undefined ? layui.layer : parent.layer;
		$ = layui.jquery;
		ctx = $("#ctx").val();

    //添加验证规则
    form.verify({
        newPwd : function(value, item){
            if(value.length < 6){
                return "密码长度不能小于6位";
            }
        },
        confirmPwd : function(value, item){
            if(!new RegExp($("#oldPwd").val()).test(value)){
                return "两次输入密码不一致，请重新输入！";
            }
        }
    })

     //修改密码
    
    /* form.on("submit(changePwd)",function(data){
    	 
    	 
    	 
    	 
    	 
     });*/
    
    
    //下面的方法有个bug就是session超时后点击无法校验
        form.on("submit(changePwd)",function(data){
    	var index;
    	//判断session超时
    		$.post(ctx+"/login/validatePassWord.do", {"password":$("#pwd").val()}, function(json) {
    			console.log(json)
       		 if(json=='error'){
       			index = layer.msg("服务器开小差了,请稍后重试!", {
        			  icon: 5,
        			  time: 2000 //2秒关闭（如果不配置，默认是3秒）
        			}, function(){
        			  //do something
        			}); 
       		 }
          		 if(json!='success'){
          		
          			index = layer.msg("原密码输入不正确,请重新输入", {
          			  icon: 5,
          			  time: 2000 //2秒关闭（如果不配置，默认是3秒）
          			}, function(){
          			  //do something
          			}); 
          			 
          		 }
          		layer.style(index, {
        			width: 'auto',
        			top:'300px',
        			});
          		if(json=='success'){
          			index = layer.msg('提交中，请稍候',{icon: 16,time:false,shade:0.8});
          			layer.style(index, {
               			width: 'auto',
               			top:'300px',
               			});
          			$.post(ctx+"/login/updateUserPassWord.do", {"password":$("#newPwd").val()}, function(msg) {
          				if(msg=="success"){
          					setTimeout(function(){
                  	            layer.close(index);
                  	            index = layer.msg("密码修改成功！");
	                  	          layer.style(index, {
	                         			width: 'auto',
	                         			top:'300px',
	                         			});
                  	            $(".pwd").val('');
                  	        },2000);
          				}else{
          					setTimeout(function(){
                  	            layer.close(index);
                  	            index = layer.msg("密码修改失败！");
                  	          layer.style(index, {
                       			width: 'auto',
                       			top:'300px',
                       			});
                  	            $(".pwd").val('');
                  	        },2000);
          				}
          			});
          			
          		}
       	 
       	
    		});
    	return false;
	
	});
    
    	/*var index = layer.msg('提交中，请稍候',{icon: 16,time:false,shade:0.8});
        setTimeout(function(){
            layer.close(index);
            layer.msg("密码修改成功！");
            $(".pwd").val('');
        },2000);*/
    	 //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    }();

   

	

