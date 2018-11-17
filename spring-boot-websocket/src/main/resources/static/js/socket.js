var dates=Date.parse(new Date());//取当前时间为用户ID
var ws = new WebSocket("ws://localhost:8080/websocket?"+dates);
var Status;

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
	sendMssg('{"userName":"'+dates+'","status":"'+Status[3]+'","mess":"'+dates+"  Say："+mess+'</br>"}');//发送请求
	$("#Messg").val("");
}
// 接收服务端数据时触发事件
ws.onmessage = function (evt) {
  var received_msg =$.parseJSON(evt.data);
  if(received_msg.userName==dates&&received_msg.playUser){//游戏玩家
	  $("#startPlay").attr("disabled",false);
  }else{//吃瓜群众
	  
  }
  switch (received_msg.status) {
	case Status[0]://新连接
		conntion(received_msg.mess);
		break;
	case Status[1]://登出
		console.info(received_msg.mess);
		break;
	case Status[2]://移动
		console.info(received_msg.mess);
		var point=received_msg.mess.split("-");
		clickPoint(point[0],point[1]);
		break;
	case Status[3]://发送消息
		$("#messBox").append(received_msg.mess);
		break;
	default:
		break;
  }
};
// 断开 web socket 连接成功触发事件
ws.onclose = function () {
  alert("连接已关闭...");
};
//开始游戏
function startPlay(){
	play.isPlay=true ;	
	play.init();
}
//新连接
function conntion(number){
	$("#onLine").text("当前用户ID："+dates+"   当前在线人数:"+number);
}
//点击着点
play.clickPoint = function (x,y){
	var key=play.nowManKey;
	var man=com.mans[key];
	if (play.nowManKey){
		if (play.indexOfPs(com.mans[key].ps,[x,y])){
			sendMssg('{"userName":"'+dates+'","status":"'+Status[2]+'","mess":"'+x+'-'+y+'"}');//发送请求
		}else{
			//alert("不能这么走哦！")	
		}
	}
}
function clickPoint(x,y){
	var key=play.nowManKey;
	var man=com.mans[key];
	var pace=man.x+""+man.y
	delete play.map[man.y][man.x];
	play.map[y][x] = key;
	com.showPane(man.x ,man.y,x,y)
	man.x = x;
	man.y = y;
	man.alpha = 1;
	play.pace.push(pace+x+y);
	play.nowManKey = false;
	com.dot.dots = [];
	com.show();
}
//