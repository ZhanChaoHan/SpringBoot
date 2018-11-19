package com.jachs.websocket.action;

import java.io.IOException;
import java.util.ArrayList;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.jachs.websocket.entity.Message;
import com.jachs.websocket.entity.Status;
import com.jachs.websocket.service.GameService;
import com.jachs.websocket.service.PlayService;
import com.jachs.websocket.vo.MessageVo;


@ServerEndpoint(value = "/websocket", encoders = { MessageVo.class })
@Component
public class WebSocket {
	// 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
	private static int onlineCount = 0;
	// concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
	private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<WebSocket>();
	// 与某个客户端的连接会话，需要通过它来给客户端发送数据
	private Session session;
	//玩家1
	private String p1;
	//玩家2
	private String p2;
	
	@Autowired
	private GameService gameService;
	@Autowired
	private PlayService playService;
	
	/**
	 * 连接建立成功调用的方法
	 * @throws EncodeException 
	 */
	@OnOpen
	public void onOpen(Session session) throws EncodeException {
		this.session = session;
		webSocketSet.add(this); // 加入set中
		addOnlineCount(); // 在线数加1
		System.out.println(session.getQueryString()+"有新连接加入！当前在线人数为" + getOnlineCount());
		try {
			String data=new Gson().toJson(getOnLineUser());
			
			Message message=new Message(null,false,Status.CHECKUSER,data);
			sendInfo(new Gson().toJson(message));
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
		webSocketSet.remove(this); // 从set中删除
		subOnlineCount(); // 在线数减1
		System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
	}
	
	private List<Message> getOnLineUser(){
		List<Message>UserInfo=new ArrayList<>();
		int index=0;
			
		for (WebSocket webSocket : webSocketSet) {
			if(index<2){
				UserInfo.add(new Message(webSocket.session.getQueryString(), true, Status.CHECKUSER, ""));
			}else{
				UserInfo.add(new Message(webSocket.session.getQueryString(), false, Status.CHECKUSER, ""));
			}
			index++;
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
		System.out.println("来自客户端的消息:" + message);
		
		if(StringUtils.isBlank(p1)){
			
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