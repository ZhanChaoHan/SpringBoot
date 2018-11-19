package com.jachs.websocket.dao;

import com.jachs.websocket.entity.Game;

public interface GameMapper {
    int deleteByPrimaryKey(String ids);

    int insert(Game record);

    int insertSelective(Game record);

    Game selectByPrimaryKey(String ids);

    int updateByPrimaryKeySelective(Game record);

    int updateByPrimaryKey(Game record);
}