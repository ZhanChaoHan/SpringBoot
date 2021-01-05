package com.jachs.websocket.service;

import com.jachs.websocket.entity.Play;


/***
 * 
 * @author zhanchaohan
 *
 */
public interface PlayService {
	int deleteByPrimaryKey(String ids);

	int insert(Play record);

	int insertSelective(Play record);

	Play selectByPrimaryKey(String ids);

	int updateByPrimaryKeySelective(Play record);

	int updateByPrimaryKey(Play record);
}
