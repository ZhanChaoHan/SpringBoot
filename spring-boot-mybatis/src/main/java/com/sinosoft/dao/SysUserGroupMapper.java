package com.sinosoft.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sinosoft.normal.po.SysUserGroup;
import com.sinosoft.normal.po.SysUserGroupKey;

@Mapper
public interface SysUserGroupMapper {
	public int deleteByPrimaryKey(SysUserGroupKey key);

	public int insert(SysUserGroup record);

	public int insertSelective(SysUserGroup record);

	public SysUserGroup selectByPrimaryKey(SysUserGroupKey key);

	public int updateByPrimaryKeySelective(SysUserGroup record);

	public int updateByPrimaryKey(SysUserGroup record);

	/**
	 * 存入系统岗位功能表中的数据集合，新数据插入，旧数据更新
	 * 
	 * @param sysGroupRoleList
	 *            要存入的系统岗位功能表的集合
	 */
	public int saveSysUserGroupAll(List<SysUserGroup> sysUserGrouplist);

	/**
	 * 通过用户代码查出所有关联的岗位列表
	 * 
	 * @param userCode
	 * @param validStatus
	 * @return List<SysUserGroup>
	 */
	public List<SysUserGroup> getSysUserGroupListByUserCode(String userCode, String validStatus);

	/**
	 * 通过机构代码查出所有关联的岗位列表
	 * 
	 * @param userCode
	 * @param validStatus
	 * @return List<SysUserGroup>
	 */
	public List<SysUserGroup> selectByGroupCode(@Param("groupCode") String groupCode);
}