package com.sinosoft.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sinosoft.normal.po.SysGroupRole;
import com.sinosoft.normal.po.SysGroupRoleKey;

@Mapper
public interface SysGroupRoleMapper {
	public int deleteByPrimaryKey(SysGroupRoleKey key);

	public int insert(SysGroupRole record);

	public int insertSelective(SysGroupRole record);

	public SysGroupRole selectByPrimaryKey(SysGroupRoleKey key);

	public int updateByPrimaryKeySelective(SysGroupRole record);

	public int updateByPrimaryKey(SysGroupRole record);

	/**
	 * 通过 岗位代码查询权限合集
	 * 
	 * @param params
	 *            属性
	 * @return List<SysGroupRole> 当前用户所有职位下的权限合集
	 */
	public List<SysGroupRole> findSysGroupRoleListByGroupCodes(Map<String, Object> params);

	public int saveSysGroupRoleAll(@Param("sysGroupRoleList") List<SysGroupRole> sysGroupRoleList);
	// /**
	// * 存入系统岗位功能表中的数据集合，新数据插入，旧数据更新
	// * @param sysGroupRoleList 要存入的系统岗位功能表的集合
	// */
	// public int saveSysGroupRoleAll(Map<String, Object> map);

	public List<SysGroupRole> selectByGroupCode(@Param("validStatus") String validStatus,
			@Param("groupCode") String groupCode);

}