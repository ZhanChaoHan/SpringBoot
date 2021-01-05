package com.jachs.websocket.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import com.jachs.websocket.action.WebSocket;
import com.jachs.websocket.service.GameService;
import com.jachs.websocket.service.PlayService;

/***
 * 
 * @author zhanchaohan
 *
 */
@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
    
    @Autowired
    public void setGameService(GameService messageService) {
    	WebSocket.gameService = messageService;
    }
    @Autowired
    public void setPlayService(PlayService messageService) {
    	WebSocket.playService = messageService;
    }
}
