package com.sinosoft.service;

import java.util.List;
import java.util.Map;

import com.sinosoft.normal.po.SysGroupRole;
import com.sinosoft.normal.po.SysGroupRoleKey;

public interface SysGroupRoleService {
	public int deleteByPrimaryKey(SysGroupRoleKey key);

	public int insert(SysGroupRole record);

	public int insertSelective(SysGroupRole record);

	public SysGroupRole selectByPrimaryKey(SysGroupRoleKey key);

	public int updateByPrimaryKeySelective(SysGroupRole record);

	public int updateByPrimaryKey(SysGroupRole record);
	
	/**
	 * 通过 岗位代码查询权限合集
	* @param params  属性
	 * @return   List<SysGroupRole> 当前用户所有职位下的权限合集
	 */
	public List<SysGroupRole> findSysGroupRoleListByGroupCodes(Map<String, Object> params);
	
	/**
	 * 存入系统岗位功能表中的数据集合，新数据插入，旧数据更新
	 * @param sysGroupRoleList 要存入的系统岗位功能表的集合
	 */
	public int saveSysGroupRoleAll(List<SysGroupRole> sysGroupRoleList);

	/**
	 * 查询机构权限
	 * @param sysGroupRoleList 查询到的结果集
	 */
	public List<SysGroupRole> selectByGroupCode(String validStatus,String groupCode);

	
}