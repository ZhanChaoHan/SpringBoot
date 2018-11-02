package com.sinosoft.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sinosoft.normal.po.SysRole;

@Mapper
public interface SysRoleMapper {
	public int deleteByPrimaryKey(String roleCode);

	public int insert(SysRole record);

	public int insertSelective(SysRole record);

	public SysRole selectByPrimaryKey(String roleCode);

	public int updateByPrimaryKeySelective(SysRole record);

	public int updateByPrimaryKey(SysRole record);

	/**
	 * 通过 权限代码合集查询权限合集
	 * 
	 * @param params
	 *            属性
	 * @return List<SysRole> 当前用户所有职位下的权限合集
	 */
	public List<SysRole> findSysRoleListByRoleCodes(Map<String, Object> params);

	/**
	 * 通过用户编码查权限
	 * 
	 * @param params
	 * @return
	 */
	public List<SysRole> findSysRoleListByUserCode(Map<String, Object> params);

	/**
	 * 查询roleType=01所有有权限模块
	 * 
	 * @param params
	 * @return
	 */
	public List<SysRole> querySysRole(@Param("roleType") String roleType);

	/**
	 * 查询所有有权限模块
	 * 
	 * @param params
	 * @return
	 */
	public List<SysRole> querySysRoleAll();
}