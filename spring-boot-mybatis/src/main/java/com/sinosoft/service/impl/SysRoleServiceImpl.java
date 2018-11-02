package com.sinosoft.service.impl;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinosoft.normal.po.SysRole;
import com.sinosoft.service.SysRoleService;
import com.sinosoft.dao.SysRoleMapper;

@Service
public class SysRoleServiceImpl implements SysRoleService {
	@Autowired
	private SysRoleMapper mapper;

	@Override
	public int deleteByPrimaryKey(String roleCode) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(roleCode);
	}

	@Override
	public int insert(SysRole record) {
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(SysRole record) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(record);
	}

	@Override
	public SysRole selectByPrimaryKey(String roleCode) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(roleCode);
	}

	@Override
	public int updateByPrimaryKeySelective(SysRole record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(SysRole record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public List<SysRole> findSysRoleListByRoleCodes(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return mapper.findSysRoleListByRoleCodes(params);
	}

	@Override
	public List<SysRole> findSysRoleListByUserCode(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return mapper.findSysRoleListByUserCode(params);
	}

	@Override
	public List<SysRole> querySysRole(String roleType) {
		return mapper.querySysRole(roleType);
	}

	@Override
	public List<SysRole> querySysRoleAll() {
		return mapper.querySysRoleAll();
	}

}
