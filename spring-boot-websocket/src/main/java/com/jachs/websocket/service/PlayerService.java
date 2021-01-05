package com.jachs.websocket.service;

import com.jachs.websocket.entity.Player;


/***
 * 
 * @author zhanchaohan
 *
 */
public interface PlayerService {
	int deleteByPrimaryKey(String ids);

	int insert(Player record);

	int insertSelective(Player record);

	Player selectByPrimaryKey(String ids);

	int updateByPrimaryKeySelective(Player record);

	int updateByPrimaryKey(Player record);
}
