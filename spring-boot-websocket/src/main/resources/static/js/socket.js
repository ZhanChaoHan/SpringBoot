/*
$.ajax({
	type: "POST",
	url: "http://192.168.2.200:8080/login/status.do",
	dataType:"jsonp",
	async:false,
	success:function(json){
		Status=$.parseJSON(json[0]);
		Sender=$.parseJSON(json[1]);
		$("#sendMssg").attr("disabled",false);
	},
	error:function(XMLHttpRequest, textStatus, errorThrown) {
	   alert(XMLHttpRequest.status);
	   alert(XMLHttpRequest.readyState);
	   alert(textStatus);
	}
});
*/
if(dates!=null){
	var ws = new WebSocket("ws://127.0.0.1:8080/websocket?"+dates);
}else{
	alert("No Name No Game!!!");
}

function sendMssg(userName,status,mess){
	Sender.userName=userName;
	Sender.status=status;
	Sender.mess=mess;
	ws.send(JSON.stringify(Sender));
}

//接收服务端数据时触发事件
ws.onmessage = function (evt) {
  var received_msg =$.parseJSON(evt.data);
  switch (received_msg.status) {
  	case Status[0]://登出
  		leave(received_msg.mess);
		break;
	case Status[1]://移动
		var point=received_msg.mess.split("-");
		clickPoint(point[0],point[1],point[2],received_msg.userName);
		break;
	case Status[2]://发送消息
		$("#messBox").append(received_msg.mess);
		break;
	case Status[3]://checkUser
		checkUser(received_msg.mess);
		break;
	case Status[4]://吃
		var point=received_msg.mess.split("-");
		eatChess(point[0],point[1],point[2],received_msg.userName,point[3]);
		break;
	case Status[5]://开始游戏
		startGame(received_msg);
		break;
	default:
		break;
  }
};

//断开 web socket 连接成功触发事件
ws.onclose = function () {
  alert("连接已关闭...");
};