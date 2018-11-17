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
//獲取遊戲玩家
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
	play1=p1;
	play2=p2;
}
//checkUser
function checkUser(received_msg){
	received_msg=$.parseJSON(received_msg);
	getPlays(received_msg);
	console.info(play1+":"+play2);
	if(dates!=play1&&dates!=play2){//当前用户为吃瓜群众
		playUser=false;
	}else{
		playUser=true;
		if(opponent!=play1&&opponent!=play2){//對手跑了
			console.info("對手跑了");
		}
		if(dates==play1){//對手
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
}