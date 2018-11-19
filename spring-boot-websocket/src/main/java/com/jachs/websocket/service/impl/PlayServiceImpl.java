package com.jachs.websocket.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jachs.websocket.dao.PlayMapper;
import com.jachs.websocket.entity.Play;
import com.jachs.websocket.service.PlayService;

@Service
public class PlayServiceImpl implements PlayService {
	@Autowired
	public PlayMapper playMapper;

	@Override
	public int deleteByPrimaryKey(String ids) {
		return playMapper.deleteByPrimaryKey(ids);
	}

	@Override
	public int insert(Play record) {
		return playMapper.insert(record);
	}

	@Override
	public int insertSelective(Play record) {
		return playMapper.insertSelective(record);
	}

	@Override
	public Play selectByPrimaryKey(String ids) {
		return playMapper.selectByPrimaryKey(ids);
	}

	@Override
	public int updateByPrimaryKeySelective(Play record) {
		return playMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKey(Play record) {
		return playMapper.updateByPrimaryKeySelective(record);
	}
	
}
