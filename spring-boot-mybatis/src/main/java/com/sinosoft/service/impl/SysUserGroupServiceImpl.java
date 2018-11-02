package com.sinosoft.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinosoft.normal.po.SysUserGroup;
import com.sinosoft.normal.po.SysUserGroupKey;
import com.sinosoft.service.SysUserGroupService;
import com.sinosoft.dao.SysUserGroupMapper;

@Service
public class SysUserGroupServiceImpl implements SysUserGroupService {

	@Autowired
	private SysUserGroupMapper mapper;

	@Override
	public int deleteByPrimaryKey(SysUserGroupKey key) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(key);
	}

	@Override
	public int insert(SysUserGroup record) {
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(SysUserGroup record) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(record);
	}

	@Override
	public SysUserGroup selectByPrimaryKey(SysUserGroupKey key) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(key);
	}

	@Override
	public int updateByPrimaryKeySelective(SysUserGroup record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(SysUserGroup record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public List<SysUserGroup> getSysUserGroupListByUserCode(String userCode, String validStatus) {
		// TODO Auto-generated method stub
		return mapper.getSysUserGroupListByUserCode(userCode, validStatus);
	}

	@Override
	public int saveSysUserGroupAll(List<SysUserGroup> sysUserGrouplist) {
		// TODO Auto-generated method stub
		return mapper.saveSysUserGroupAll(sysUserGrouplist);
	}

	@Override
	public List<SysUserGroup> selectByGroupCode(String groupCode) {
		// TODO Auto-generated method stub
		return mapper.selectByGroupCode(groupCode);
	}

}
