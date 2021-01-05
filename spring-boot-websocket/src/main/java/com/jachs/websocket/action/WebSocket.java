package com.jachs.websocket.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.jachs.websocket.entity.Game;
import com.jachs.websocket.entity.Message;
import com.jachs.websocket.entity.Play;
import com.jachs.websocket.entity.Status;
import com.jachs.websocket.service.GameService;
import com.jachs.websocket.service.PlayService;
import com.jachs.websocket.vo.MessageVo;

/***
 * 
 * @author zhanchaohan
 *
 */
@Component
@ServerEndpoint(value = "/websocket", encoders = { MessageVo.class })
public class WebSocket {
	private static int onlineCount = 0;
	// concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
	private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<WebSocket>();
	// 与某个客户端的连接会话，需要通过它来给客户端发送数据
	private Session session;
	//玩家1
	private static String p1;
	private static boolean p1Ready;
	//玩家2
	private static String p2;
	private static boolean p2Ready;
	
	private static Gson gson=new Gson();
	private static String gameID;
	public static GameService gameService;
	public static PlayService playService;
	

	/**
	 * 连接建立成功调用的方法
	 * @throws EncodeException 
	 */
	@OnOpen
	public void onOpen(Session session) throws EncodeException {
		this.session = session;
		webSocketSet.add(this); // 加入set中
		addOnlineCount(); // 在线数加1
		if(getOnlineCount()<=2){
			if(StringUtils.isBlank(p1)){
				p1=session.getQueryString();
			}else{
				p2=session.getQueryString();
			}
		}
		System.out.println(session.getQueryString()+"有新连接加入！当前在线人数为" + getOnlineCount());
		try {
			String data=new Gson().toJson(getOnLineUser(false));
			
			Message message=new Message(null,false,Status.CHECKUSER,data);
			sendInfo(gson.toJson(message));
		} catch (IOException e) {
			System.out.println("IO异常");
		}
	}

	/**
	 * 连接关闭调用的方法
	 * @throws EncodeException 
	 * @throws IOException 
	 */
	@OnClose
	public void onClose() throws IOException, EncodeException {
		subOnlineCount(); // 在线数减1
		System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
		boolean playLeave=false;
		if(this.session.getQueryString().equals(p1)){
			p1=null;
			playLeave=true;
		}
		if(this.session.getQueryString().equals(p2)){
			p2=null;
			playLeave=true;
		}
		if(playLeave){
			sendInfo(new Message(this.session.getQueryString(), true, Status.LEAVE, "LEAVEOUT"));
		}
		webSocketSet.remove(this); // 从set中删除
	}
	
	/****
	 * 获取所有玩家,或游戏玩家
	 * @return
	 */
	private List<Message> getOnLineUser(boolean all){
		List<Message>UserInfo=new ArrayList<>();
		
		if(all){
			int index=0;
			for (WebSocket webSocket : webSocketSet) {
				if(index<2){
					UserInfo.add(new Message(webSocket.session.getQueryString(), true, Status.CHECKUSER, getOnlineCount()+""));
				}else{
					UserInfo.add(new Message(webSocket.session.getQueryString(), false, Status.CHECKUSER, getOnlineCount()+""));
				}
				index++;
			}
		}else{
			for (WebSocket webSocket : webSocketSet) {
				if(webSocket.session.getQueryString().equals(p1)){
					UserInfo.add(new Message(webSocket.session.getQueryString(), true, Status.CHECKUSER, getOnlineCount()+""));
				}
				if(webSocket.session.getQueryString().equals(p2)){
					UserInfo.add(new Message(webSocket.session.getQueryString(), true, Status.CHECKUSER, getOnlineCount()+""));
				}
			}
		}
		return UserInfo;
	}
	
	/**
	 * 收到客户端消息后调用的方法
	 *
	 * @param message
	 *            客户端发送过来的消息
	 */
	@OnMessage
	public void onMessage(String message, Session session) {
		Message messages=gson.fromJson(message, Message.class);
		System.out.println("来自客户端的消息:"+messages.getMess());
		Date time=new Date();
		Play play=new Play();
		switch (messages.getStatus()) {
			case MOVER:
				play.setGameid(gameID);
				play.setIds(time.getTime()+"");
				play.setMess(messages.getMess());
				play.setPlayuser(messages.getUserName());
				play.setStatustype(messages.getStatus().name());
				play.setTimeconsuming(null);
				
				playService.insert(play);
				break;
			case EATCHESS:
				play.setGameid(gameID);
				play.setIds(time.getTime()+"");
				play.setMess(messages.getMess());
				play.setPlayuser(messages.getUserName());
				play.setStatustype(messages.getStatus().name());
				play.setTimeconsuming(null);
				
				playService.insert(play);
				break;
			case STARTGAME:
				if(messages.getUserName().equals(p1))p1Ready=true;
				if(messages.getUserName().equals(p2))p2Ready=true;
				if(p1Ready&&p2Ready){//都准备好了进库
					Game games=new Game();
					gameID=time.getTime()+"";
					games.setIds(gameID);
					games.setP1(p1);
					games.setP2(p2);
					games.setStarttime(time);
					gameService.insert(games);
				}
				break;
			default:
				break;
		}
		// 群发消息
		for (WebSocket item : webSocketSet) {
			try {
				item.sendMessage(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 群发自定义消息
	 * @throws EncodeException 
	 */
	public static void sendInfo(Object message) throws IOException, EncodeException {
		for (WebSocket item : webSocketSet) {
			if(item.session.isOpen()){
				try {
					item.sendMessage(message);
				} catch (IOException e) {
					continue;
				}
			}
		}
	}
	/**
	 * 群发自定义消息
	 * @throws EncodeException 
	 */
	public static void sendInfo(String message) throws IOException, EncodeException {
		for (WebSocket item : webSocketSet) {
			if(item.session.isOpen()){
				try {
					item.sendMessage(message);
				} catch (IOException e) {
					continue;
				}
			}
		}
	}
	/**
	 * 发生错误时调用
	 */
	@OnError
	public void onError(Session session, Throwable error) {
		System.out.println("发生错误");
		error.printStackTrace();
	}

	public void sendMessage(String message) throws IOException {
		this.session.getBasicRemote().sendText(message);
	}
	public void sendMessage(Object message) throws IOException, EncodeException {
		this.session.getBasicRemote().sendObject(message);
	}
	public static synchronized int getOnlineCount() {
		return onlineCount;
	}
	public static synchronized void addOnlineCount() {
		WebSocket.onlineCount++;
	}
	public static synchronized void subOnlineCount() {
		WebSocket.onlineCount--;
	}
}