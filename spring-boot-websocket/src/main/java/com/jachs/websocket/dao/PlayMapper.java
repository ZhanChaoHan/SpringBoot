package com.jachs.websocket.dao;

import com.jachs.websocket.entity.Play;

public interface PlayMapper {
    int deleteByPrimaryKey(String ids);

    int insert(Play record);

    int insertSelective(Play record);

    Play selectByPrimaryKey(String ids);

    int updateByPrimaryKeySelective(Play record);

    int updateByPrimaryKey(Play record);
}