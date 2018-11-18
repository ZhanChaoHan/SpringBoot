//开始游戏
function startPlay(){
	if(playUser){//不是游戏用户不能游戏
		play.isPlay=true ;	
		play.init();
		$("#startPlay").attr("disabled",true);
	}
}
//发送消息
function sendMess(){
	var mess=$("#Messg").val();
	sendMssg('{"userName":"'+dates+'","status":"'+Status[1]+'","mess":"'+dates+"  Say："+mess+'</br>"}');//发送请求
	//clickPoint('z0',0,4,dates);
	$("#Messg").val("");
}

//点击着点
play.clickPoint = function (x,y){
	var key=play.nowManKey;
	var man=com.mans[key];
	if (playUser&&play.nowManKey){
		if (play.indexOfPs(com.mans[key].ps,[x,y])){
			sendMssg('{"userName":"'+dates+'","status":"'+Status[0]+'","mess":"'+key+'-'+x+'-'+y+'"}');//发送请求
			play.isPlay=false;
		}else{
			//alert("不能这么走哦！")	
		}
	}
}

//获取游戏玩家
function getPlays(received_msg){
	console.info(received_msg);
	var p1;
	var p2;
	for(info in received_msg){
		if(received_msg[info].playUser){//游戏玩家
			if(p1==null){
				p1=received_msg[info].userName;
			}else{
				p2=received_msg[info].userName;
			}
		}
	}
	if(play1!=dates&&play2!=dates){//上一把吃瓜了
		if(p1==dates||p2==dates){//这把上手
			
		}
	}
	play1=p1;
	play2=p2;
}
//checkUser
function checkUser(received_msg){
	received_msg=$.parseJSON(received_msg);
	getPlays(received_msg);
	if(dates!=play1&&dates!=play2){//当前用户为吃瓜群众
		playUser=false;
	}else{
		playUser=true;
		$("#startPlay").attr("disabled",false);
		if(opponent!=play1&&opponent!=play2){//对手跑了
			console.info("對手跑了");
		}
		if(dates==play1){//对手
			opponent=play2;
		}else{
			opponent=play1;
		}
	}
	if(playUser){
		$("#onLine").text("当前用户ID："+dates+" 当前在线人数:"+received_msg.length+" 游戏玩家");
	}else{
		$("#onLine").text("当前用户ID："+dates+" 当前在线人数:"+received_msg.length+" 吃瓜群众");
	}
	
	if(!init){//初始化
		init=true;
	}else{
		
	}
	$("#opponent").text("对手: "+opponent);
	$("#play1").text("玩家1: "+play1);
	$("#play2").text("玩家2: "+play2);
}
//移动棋子
function clickPoint(key,x,y,userName){
	if(userName==dates){//当前用户发送的移动命令
		play.isPlay=false;
	}
	if(userName==opponent){//对手移动
		key=key.toUpperCase();
		y=parseInt(y);
		if(y==9)y=0;
		if(y==8)y=1;
		if(y==7)y=2;
		if(y==6)y=3;
		if(y==5)y=4;
		play.isPlay=true;
	}
	if(userName!=dates&&userName!=opponent){//吃瓜群众
		
	}
	var man=com.mans[key];
	var pace=man.x+""+man.y;
	delete play.map[man.y][man.x];
	play.map[y][x] = key;
	com.showPane(man.x ,man.y,x,y)
	man.x = x;
	man.y = y;
	play.pace.push(pace+x+y);
	com.dot.dots = [];
	com.show();
	$("#news").append("用户:"+userName+"移动:"+key+"到:"+x+"-"+y+"</br>");
}
//吃子
function eatChess(key,x,y,opponent){
	if(playUser&&opponent==opponent){
		alert(key);
		var pace=com.mans[key].x+""+com.mans[key].y
		delete play.map[com.mans[key].y][com.mans[key].x];
		com.showPane(com.mans[key].x ,com.mans[key].y,x,y)
		
		play.pace.push(pace+x+y);
		com.dot.dots = [];
		com.show()
		if (key == "j0") play.showWin (-1);
		if (key == "J0") play.showWin (1);
	}
	$("#news").append("用户:"+opponent+"移动:"+key+"到:"+x+"-"+y+"</br>");
}