package com.jachs.websocket.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/***
 * 
 * @author zhanchaohan
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message implements Serializable{
	private static final long serialVersionUID = 1L;
	private String userName;//用户
	private boolean playUser;//游戏玩家
	private Status status;//状态
	private String mess;//信息
	
}
