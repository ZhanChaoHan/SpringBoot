package com.jachs.websocket.dao;

import org.apache.ibatis.annotations.Insert;

import com.jachs.websocket.entity.Play;

/***
 * 
 * @author zhanchaohan
 *
 */
public interface PlayMapper {
    int deleteByPrimaryKey(String ids);
    @Insert("insert into play (ids, gameid, playuser,timeconsuming,statustype,mess) values (#{ids,jdbcType=VARCHAR}, #{gameid,jdbcType=VARCHAR}, #{playuser,jdbcType=VARCHAR}, #{timeconsuming,jdbcType=VARCHAR}, #{statustype,jdbcType=VARCHAR},#{mess,jdbcType=VARCHAR})")
    int insert(Play record);

    int insertSelective(Play record);

    Play selectByPrimaryKey(String ids);

    int updateByPrimaryKeySelective(Play record);

    int updateByPrimaryKey(Play record);
}