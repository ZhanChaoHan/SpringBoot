var dates=Date.parse(new Date());//取当前时间为用户ID
var ws = new WebSocket("ws://localhost:8080/websocket?"+dates);
var Status;//状态码
var init=false;//是否初始化
var playUser;//是否游戏玩家
var opponent;//对手
var play1;//玩家一
var play2;//玩家二

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
  console.info(dates);
  switch (received_msg.status) {
	case Status[0]://移动
		var point=received_msg.mess.split("-");
		clickPoint(point[0],point[1],point[2],received_msg.userName);
		break;
	case Status[1]://发送消息
		$("#messBox").append(received_msg.mess);
		break;
	case Status[2]://checkUser
		checkUser(received_msg.mess);
		break;
	default:
		break;
  }
};
//断开 web socket 连接成功触发事件
ws.onclose = function () {
  alert("连接已关闭...");
};

