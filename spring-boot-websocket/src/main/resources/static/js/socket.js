var dates=Date.parse(new Date());
var ws = new WebSocket("ws://localhost:8080/websocket?"+dates);
var Status;

$.ajax({
	type: "POST",
	url: "http://localhost:8080/login/status.do",
	dataType:"jsonp",
	async:false,
	success:function(json){
		Status=json;
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
// 接收服务端数据时触发事件
ws.onmessage = function (evt) {
  var received_msg =$.parseJSON(evt.data);
  switch (received_msg.status) {
	case Status[0]://新连接
		console.info(received_msg.mess);
		break;
	case Status[1]://登出
		console.info(received_msg.mess);
		break;
	case Status[2]://移动
		console.info(received_msg.mess);
		var point=received_msg.mess.split("-");
		clickPoint(point[0],point[1]);
		break;
	default:
		break;
  }
};
// 断开 web socket 连接成功触发事件
ws.onclose = function () {
  alert("连接已关闭...");
};

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