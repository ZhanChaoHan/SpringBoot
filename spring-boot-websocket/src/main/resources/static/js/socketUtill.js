//开始游戏,点击
function startPlay(){
	if(playUser){//不是游戏用户不能游戏
		$("#startPlay").attr("disabled",true);
		ready=true;
		sendMssg(dates,Status[5],"READY");
	}
}
//开始游戏
function startGame(received_msg){
	if(received_msg.userName==dates){//当前点击
		play.isPlay=true;
		play.init();
	}
	if(received_msg.userName==opponent){//对手点击
		if(ready){
			
		}
	}
}
//发送消息
function sendMess(){
	var mess=$("#Messg").val();
	sendMssg(dates,Status[2],dates+"  Say："+mess+"</br>");
	//clickPoint('z0',0,4,dates);
	$("#Messg").val("");
}
//离开
function leave(mess){
	//alert(mess);
}

//获取游戏玩家
function getPlays(received_msg){
	for(info in received_msg){
		if(received_msg[info].playUser){//游戏玩家
			if(play1==null){
				play1=received_msg[info].userName;
			}else{
				play2=received_msg[info].userName;
			}
		}
	}
}
//checkUser
function checkUser(received_msg){
	received_msg=$.parseJSON(received_msg);
	console.info(received_msg);
	getPlays(received_msg);
	if(dates!=play1&&dates!=play2){//当前用户为吃瓜群众
		playUser=false;
	}else{
		playUser=true;
		$("#startPlay").attr("disabled",false);
		
		if(dates==play1){//对手
			opponent=play2;
		}else{
			opponent=play1;
		}
	}
	if(playUser){
		$("#onLine").text("当前用户ID："+dates+" 当前在线人数:"+received_msg[0].mess+" 游戏玩家");
	}else{
		$("#onLine").text("当前用户ID："+dates+" 当前在线人数:"+received_msg[0].mess+" 吃瓜群众");
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
		if(y<=4){
			if(y==0)y=9;
			if(y==1)y=8;
			if(y==2)y=7;
			if(y==3)y=6;
			if(y==4)y=5;
		}else{
			if(y==5)y=4;
			if(y==6)y=3;
			if(y==7)y=2;
			if(y==8)y=1;
			if(y==9)y=0;
		}
		play.isPlay=true;
		
	}
	var man=com.mans[key];
	delete play.map[man.y][man.x];
	play.map[y][x] = key;
	com.showPane(man.x ,man.y,x,y);
	man.y = y;
	man.x = x;
	$("#news").append("用户:"+userName+"移动:"+key+"到:"+x+"-"+y+"</br>");
	com.dot.dots = [];
	com.show();
}
//吃子
function eatChess(key,x,y,opponents,eatChess){
	console.info("吃:"+key+"x:"+x+"y:"+y+"对手:"+opponents+"被吃:"+eatChess);
	if(playUser&&opponents==opponent){
		y=parseInt(y);
		if(y<=4){
			if(y==0)y=9;
			if(y==1)y=8;
			if(y==2)y=7;
			if(y==3)y=6;
			if(y==4)y=5;
		}else{
			if(y==5)y=4;
			if(y==6)y=3;
			if(y==7)y=2;
			if(y==8)y=1;
			if(y==9)y=0;
		}
		
		/*
		if(y==9)y=0;
		if(y==8)y=1;
		if(y==7)y=2;
		if(y==6)y=3;
		if(y==5)y=4;
		*/
		play.isPlay=true;
		
		
		key=key.toUpperCase();
		eatChess=eatChess.toLowerCase();
		
		delete play.map[com.mans[key].y][com.mans[key].x];
		var man = com.mans[eatChess];//被吃
		man.isShow = false;
		
		com.mans[key].x=x;
		com.mans[key].y=y;
		
		play.map[y][x] =key;
		com.showPane(com.mans[key].x ,com.mans[key].y,x,y);
		
		com.pane.isShow = false;
		com.dot.dots = [];
		com.show();
		if (eatChess == "j0") play.showWin (-1);
		console.info(play.map);
		console.info(com.mans);
	}
	$("#news").append("用户:"+opponent+"移动:"+key+"到:"+x+"-"+y+"吃了"+eatChess+"</br>");
}