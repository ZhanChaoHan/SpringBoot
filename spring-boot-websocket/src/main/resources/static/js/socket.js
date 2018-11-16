var ws = new WebSocket("ws://localhost:8080/websocket");

/*
//建立 web socket 连接成功触发事件
ws.onopen = function () {
  // 使用 send() 方法发送数据
  ws.send("发送数据");
  alert("数据发送中...");
};
*/

// 接收服务端数据时触发事件
ws.onmessage = function (evt) {
  var received_msg =$.parseJSON(evt.data);
  switch (received_msg.status) {
	case "CONNTION":
		console.info(received_msg.mess);
		break;
	
	default:
		break;
  }
};

// 断开 web socket 连接成功触发事件
ws.onclose = function () {
  alert("连接已关闭...");
};