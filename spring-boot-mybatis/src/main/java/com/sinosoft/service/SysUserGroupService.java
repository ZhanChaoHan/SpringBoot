package com.sinosoft.service;

import java.util.List;

import com.sinosoft.normal.po.SysUserGroup;
import com.sinosoft.normal.po.SysUserGroupKey;


public interface SysUserGroupService {
	public int deleteByPrimaryKey(SysUserGroupKey key);

	public int insert(SysUserGroup record);

	public int insertSelective(SysUserGroup record);

	public SysUserGroup selectByPrimaryKey(SysUserGroupKey key);

	public int updateByPrimaryKeySelective(SysUserGroup record);

	public int updateByPrimaryKey(SysUserGroup record);
	
	/**
	 * 通过用户代码查出所有关联的岗位列表
	 * @param userCode
	 * @param validStatus
	 * @return List<SysUserGroup>
	 */
	public List<SysUserGroup> getSysUserGroupListByUserCode(String userCode,String validStatus);
	
	/**
	 * 存入系统岗位功能表中的数据集合，新数据插入，旧数据更新
	 * @param sysGroupRoleList 要存入的系统岗位功能表的集合
	 */												  
	public int saveSysUserGroupAll(List<SysUserGroup> sysUserGroupList);
	
	/**
	 * 查询人员岗位权限
	 * @param sysGroupRoleList 查询到的结果集
	 */
	public List<SysUserGroup> selectByGroupCode(String groupCode);
}