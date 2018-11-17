var dates=Date.parse(new Date());//取当前时间为用户ID
var ws = new WebSocket("ws://localhost:8080/websocket?"+dates);
var Status;//状态码
var playUser;//是否游戏玩家
var opponent;//对手

$.ajax({
	type: "POST",
	url: "http://localhost:8080/login/status.do",
	dataType:"jsonp",
	async:false,
	success:function(json){
		Status=json;
		$("#sendMssg").attr("disabled",false);
	},
	error:function(XMLHttpRequest, textStatus, errorThrown) {
	   alert(XMLHttpRequest.status);
	   alert(XMLHttpRequest.readyState);
	   alert(textStatus);
	}
});
function sendMssg(mssg){
	ws.send(mssg);
}
//发送消息
function sendMess(){
	var mess=$("#Messg").val();
	sendMssg('{"userName":"'+dates+'","status":"'+Status[1]+'","mess":"'+dates+"  Say："+mess+'</br>"}');//发送请求
	$("#Messg").val("");
}
// 接收服务端数据时触发事件
ws.onmessage = function (evt) {
  var received_msg =$.parseJSON(evt.data);
  switch (received_msg.status) {
	case Status[0]://移动
		var point=received_msg.mess.split("-");
		clickPoint(point[0],point[1],point[2],received_msg.userName);
		break;
	case Status[1]://发送消息
		$("#messBox").append(received_msg.mess);
		break;
	default:
		break;
  }
  if(received_msg instanceof Array){//登录或刷新页面或登出
	  for(info in received_msg){
		  if(received_msg[info].userName==dates){//当前用户信息
			  if(received_msg[info].playUser){//游戏玩家
				  playUser=true;
				  $("#startPlay").attr("disabled",false);
			  }else{
				  playUser=false;//吃瓜群众
			  }
		  }
		  if(received_msg[info].playUser&&received_msg[info].userName!=dates){//为玩家但不是当前玩家那就是对手玩家
			  opponent=info.userName
		  }
	  }
	  if(playUser){
	 	$("#onLine").text("当前用户ID："+dates+" 当前在线人数:"+received_msg.length+" 游戏玩家");
	  }else{
		$("#onLine").text("当前用户ID："+dates+" 当前在线人数:"+received_msg.length+" 吃瓜群众");
	  }
  }
};
//断开 web socket 连接成功触发事件
ws.onclose = function () {
  alert("连接已关闭...");
};
//开始游戏
function startPlay(){
	if(playUser){//不是游戏用户不能游戏
		play.isPlay=true ;	
		play.init();
		$("#startPlay").attr("disabled",true);
	}
}
//点击着点
play.clickPoint = function (x,y){
	var key=play.nowManKey;
	var man=com.mans[key];
	if (playUser&&play.nowManKey){
		if (play.indexOfPs(com.mans[key].ps,[x,y])){
			sendMssg('{"userName":"'+dates+'","status":"'+Status[0]+'","mess":"'+x+'-'+y+'-'+key+'"}');//发送请求
			play.isPlay=false;
		}else{
			//alert("不能这么走哦！")	
		}
	}
}
function clickPoint(x,y,key,userName){
	if(userName==dates){//当前用户发送的移动命令
		play.nowManKey = false;
	}
	var man=com.mans[key];
	var pace=man.x+""+man.y
	delete play.map[man.y][man.x];
	play.map[y][x] = key;
	com.showPane(man.x ,man.y,x,y)
	man.x = x;
	man.y = y;
	man.alpha = 1;
	play.pace.push(pace+x+y);
	com.dot.dots = [];
	com.show();
}