package com.jachs.mybatis.dao;


import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.jachs.mybatis.entity.Tb2;
public interface Tb2Mapper {
	@Delete("delete from tb2  where aa = #{aa,jdbcType=VARCHAR}")
    int deleteByPrimaryKey(String aa);
	@Insert("insert into tb2 (aa, bb, cc) values (#{aa,jdbcType=VARCHAR}, #{bb,jdbcType=VARCHAR}, #{cc,jdbcType=VARCHAR})")
    int insert(Tb2 record);
    int insertSelective(Tb2 record);
    @Select("SELECT * FROM tb2  where aa = #{aa,jdbcType=VARCHAR}")
    Tb2 selectByPrimaryKey(String aa);
    int updateByPrimaryKeySelective(Tb2 record);
    @Update("update tb2 set bb = #{bb,jdbcType=VARCHAR},cc = #{cc,jdbcType=VARCHAR} where aa = #{aa,jdbcType=VARCHAR}")
    int updateByPrimaryKey(Tb2 record);
    @Select("<script>"
    		+"select * from tb2 where "
    		+"<if test='aa!=null'>"
    		+ "aa=#{aa} and" 
    		+"</if>"
    		+ " bb=#{bb} and cc=#{cc}"
    		+"</script>")
    		
	Tb2 select(String aa, String bb, String cc);
    
}