package com.jachs.websocket.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.jachs.websocket.entity.Game;

/***
 * 
 * @author zhanchaohan
 *
 */
public interface GameMapper {
    int deleteByPrimaryKey(String ids);
    @Insert("insert into game (ids, p1, p2,starttime,endtime,winner) values (#{ids,jdbcType=VARCHAR}, #{p1,jdbcType=VARCHAR}, #{p2,jdbcType=VARCHAR}, #{starttime,jdbcType=TIMESTAMP}, #{endtime,jdbcType=TIMESTAMP},#{winner,jdbcType=VARCHAR})")
    int insert(Game record);

    int insertSelective(Game record);
    @Select("SELECT * FROM game  where ids = #{ids,jdbcType=VARCHAR}")
    Game selectByPrimaryKey(String ids);

    int updateByPrimaryKeySelective(Game record);

    int updateByPrimaryKey(Game record);
}