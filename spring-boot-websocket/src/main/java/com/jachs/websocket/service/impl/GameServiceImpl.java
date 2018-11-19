package com.jachs.websocket.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jachs.websocket.dao.GameMapper;
import com.jachs.websocket.entity.Game;
import com.jachs.websocket.service.GameService;

@Service
public class GameServiceImpl implements GameService {
	@Autowired
	public GameMapper gameMapper;

	@Override
	public int deleteByPrimaryKey(String ids) {
		return gameMapper.deleteByPrimaryKey(ids);
	}

	@Override
	public int insert(Game record) {
		return gameMapper.insert(record);
	}

	@Override
	public int insertSelective(Game record) {
		return gameMapper.insertSelective(record);
	}

	@Override
	public Game selectByPrimaryKey(String ids) {
		return gameMapper.selectByPrimaryKey(ids);
	}

	@Override
	public int updateByPrimaryKeySelective(Game record) {
		return gameMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Game record) {
		return gameMapper.updateByPrimaryKey(record);
	}
}
