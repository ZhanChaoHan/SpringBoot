package com.jachs.websocket.dao;

import org.apache.ibatis.annotations.Insert;

import com.jachs.websocket.entity.Player;

/***
 * 
 * @author zhanchaohan
 *
 */
public interface PlayerMapper {
    int deleteByPrimaryKey(String ids);
    @Insert("insert into play (ids, gameid, playuser,timeconsuming,statustype,mess) values (#{ids,jdbcType=VARCHAR}, #{gameid,jdbcType=VARCHAR}, #{playuser,jdbcType=VARCHAR}, #{timeconsuming,jdbcType=VARCHAR}, #{statustype,jdbcType=VARCHAR},#{mess,jdbcType=VARCHAR})")
    int insert(Player record);

    int insertSelective(Player record);

    Player selectByPrimaryKey(String ids);

    int updateByPrimaryKeySelective(Player record);

    int updateByPrimaryKey(Player record);
}