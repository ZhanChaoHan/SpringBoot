package com.sinosoft.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinosoft.normal.po.SysGroupRole;
import com.sinosoft.normal.po.SysGroupRoleKey;
import com.sinosoft.service.SysGroupRoleService;
import com.sinosoft.dao.SysGroupRoleMapper;

@Service
public class SysGroupRoleServiceImpl implements SysGroupRoleService {
	@Autowired
	public SysGroupRoleMapper mapper;

	@Override
	public int deleteByPrimaryKey(SysGroupRoleKey key) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(key);
	}

	@Override
	public int insert(SysGroupRole record) {
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(SysGroupRole record) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(record);
	}

	@Override
	public SysGroupRole selectByPrimaryKey(SysGroupRoleKey key) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(key);
	}

	@Override
	public int updateByPrimaryKeySelective(SysGroupRole record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(SysGroupRole record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public List<SysGroupRole> findSysGroupRoleListByGroupCodes(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return mapper.findSysGroupRoleListByGroupCodes(params);
	}

	/**
	 * 存入系统岗位功能表中的数据集合，新数据插入，旧数据更新
	 * 
	 * @param sysGroupRoleList
	 *            要存入的系统岗位功能表的集合
	 */
	public int saveSysGroupRoleAll(List<SysGroupRole> sysGroupRoleList) {
		return mapper.saveSysGroupRoleAll(sysGroupRoleList);

	}

	@Override
	public List<SysGroupRole> selectByGroupCode(String validStatus, String groupCode) {
		return mapper.selectByGroupCode(validStatus, groupCode);
	}

	// @Override
	// public int saveSysGroupRoleAll(Map<String, Object> map) {
	// // TODO Auto-generated method stub
	// return mapper.saveSysGroupRoleAll(map);
	// }

}
