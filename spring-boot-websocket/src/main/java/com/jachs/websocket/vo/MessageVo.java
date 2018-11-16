package com.jachs.websocket.vo;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jachs.websocket.entity.Message;

public class MessageVo implements Encoder.Text<Message>{

	@Override
	public void destroy() {
		
	}

	@Override
	public void init(EndpointConfig arg0) {
		
	}

	@Override
	public String encode(Message arg0) throws EncodeException {
		ObjectMapper    mapMapper= new ObjectMapper();
		String json = null;
		try {
			json= mapMapper.writeValueAsString(arg0);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}

}
