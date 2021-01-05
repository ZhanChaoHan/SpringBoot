package com.jachs.websocket.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jachs.websocket.dao.PlayerMapper;
import com.jachs.websocket.entity.Player;
import com.jachs.websocket.service.PlayerService;

/***
 * 
 * @author zhanchaohan
 *
 */
@Service
public class PlayServiceImpl implements PlayerService {
	@Autowired
	public PlayerMapper playMapper;

	@Override
	public int deleteByPrimaryKey(String ids) {
		return playMapper.deleteByPrimaryKey(ids);
	}

	@Override
	public int insert(Player record) {
		return playMapper.insert(record);
	}

	@Override
	public int insertSelective(Player record) {
		return playMapper.insertSelective(record);
	}

	@Override
	public Player selectByPrimaryKey(String ids) {
		return playMapper.selectByPrimaryKey(ids);
	}

	@Override
	public int updateByPrimaryKeySelective(Player record) {
		return playMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKey(Player record) {
		return playMapper.updateByPrimaryKeySelective(record);
	}
	
}
