package com.sinosoft.service;

import java.util.List;
import java.util.Map;

import com.sinosoft.normal.po.SysRole;

public interface SysRoleService {
	public int deleteByPrimaryKey(String roleCode);

	public int insert(SysRole record);

	public int insertSelective(SysRole record);

	public SysRole selectByPrimaryKey(String roleCode);

	public int updateByPrimaryKeySelective(SysRole record);

	public int updateByPrimaryKey(SysRole record);
	
	public  List<SysRole> querySysRole(String roleType);
	
	/**
	 * 通过 权限代码合集查询权限合集
	 * @param params  属性
	 * @return   List<SysRole> 当前用户所有职位下的权限合集
	 */
	public List<SysRole> findSysRoleListByRoleCodes( Map<String, Object> params);
	
	
	/**
	 * 通过用户编码查权限
	 * @param params
	 * @return
	 */
	public List<SysRole> findSysRoleListByUserCode(Map<String,Object> params);

	public List<SysRole> querySysRoleAll();
}