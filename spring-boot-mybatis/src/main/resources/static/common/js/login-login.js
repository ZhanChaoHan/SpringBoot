$(function(){
	 if (window != top)   
		    top.location.href = location.href; 
		//更换验证码
		changeValidateCode();
	});
layui.config({
	base : "js/"
}).use(['form','layer'],function(){
	  var layer = layui.layer
	  ,form = layui.form;
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		$ = layui.jquery;
	
	var msg = $("#msg").val();
	
	if(msg!=null&&msg!=""){
		var index = layer.msg(msg, {
			  icon: 5,
			  time: 2000 //2秒关闭（如果不配置，默认是3秒）
			}, function(){
			}); 
	}
	
	//登录按钮事件
	form.on("submit(login)",function(data){
		
	})
})


function changeValidateCode() {		
	var img = document.getElementById('randomImg');
	//获取当前的时间作为参数，无具体意义    
	var timenow = new Date().getTime();
	//每次请求需要一个不同的参数，否则可能会返回同样的验证码    
	//这和浏览器的缓存机制有关系，也可以把页面设置为不缓存，这样就不用这个参数了。   
	img.src = "/pages/login/RandomNumUtil.jsp?d=" + timenow;
}
