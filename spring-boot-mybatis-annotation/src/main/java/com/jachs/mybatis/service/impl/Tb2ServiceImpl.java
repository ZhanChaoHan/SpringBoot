package com.jachs.mybatis.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jachs.mybatis.dao.Tb2Mapper;
import com.jachs.mybatis.entity.Tb2;
import com.jachs.mybatis.service.Tb2Service;

@Service
public class Tb2ServiceImpl implements Tb2Service{
	@Autowired
	public Tb2Mapper tb2Mapper;

	@Override
	public int deleteByPrimaryKey(String aa) {
		// TODO Auto-generated method stub
		return tb2Mapper.deleteByPrimaryKey(aa);
	}

	@Override
	public int insert(Tb2 record) {
		// TODO Auto-generated method stub
		return tb2Mapper.insert(record);
	}

	@Override
	public int insertSelective(Tb2 record) {
		// TODO Auto-generated method stub
		return tb2Mapper.insertSelective(record);
	}

	@Override
	public Tb2 selectByPrimaryKey(String aa) {
		// TODO Auto-generated method stub
		return tb2Mapper.selectByPrimaryKey(aa);
	}

	@Override
	public int updateByPrimaryKeySelective(Tb2 record) {
		// TODO Auto-generated method stub
		return tb2Mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Tb2 record) {
		return tb2Mapper.updateByPrimaryKey(record);
	}

	@Override
	public Tb2 select(String aa, String bb, String cc) {
		// TODO Auto-generated method stub
		return tb2Mapper.select(aa,bb,cc);
	}
	

}
