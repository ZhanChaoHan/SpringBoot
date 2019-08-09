package com.jachs.mybatis.service;

import com.jachs.mybatis.entity.Tb2;

public interface Tb2Service {
	 int deleteByPrimaryKey(String aa);

	    int insert(Tb2 record);

	    int insertSelective(Tb2 record);

	    Tb2 selectByPrimaryKey(String aa);
	    Tb2 select(String aa,String bb,String cc);

	    int updateByPrimaryKeySelective(Tb2 record);

	    int updateByPrimaryKey(Tb2 record);
}
