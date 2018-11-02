package com.sinosoft.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinosoft.normal.po.SysGroup;
import com.sinosoft.service.SysGroupService;
import com.sinosoft.dao.SysGroupMapper;

@Service
public class SysGroupServiceImpl  implements SysGroupService {

	@Autowired
	private SysGroupMapper mapper;

	@Override
	public int deleteByPrimaryKey(String groupCode) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(groupCode);
	}

	@Override
	public int insert(SysGroup record) {
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(SysGroup record) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(record);
	}

	@Override
	public SysGroup selectByPrimaryKey(String groupCode) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(groupCode);
	}

	@Override
	public int updateByPrimaryKeySelective(SysGroup record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(SysGroup record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(record);
	}

	/** 条件查询 **/
	@Override
	public List<SysGroup> querySysGroup(String groupCode, String groupName, String comLevel, String comType) {
		return mapper.querySysGroup(groupCode, groupName, comLevel, comType);
	}

	@Override
	public List<SysGroup> queryUserSysGroup(String comLevel, String comType) {
		// TODO Auto-generated method stub
		return mapper.queryUserSysGroup(comLevel, comType);
	}

}
