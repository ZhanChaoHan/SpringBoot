package com.sinosoft.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sinosoft.normal.po.SysGroup;
import com.sinosoft.normal.po.SysGroupRole;

@Mapper
public interface SysGroupMapper {
	public int deleteByPrimaryKey(String groupCode);

	public int insert(SysGroup record);

	public int insertSelective(SysGroup record);

	public SysGroup selectByPrimaryKey(String groupCode);

	public int updateByPrimaryKeySelective(SysGroup record);

	public int updateByPrimaryKey(SysGroup record);

	/**
	 * 通过 岗位代码查询权限合集
	 * 
	 * @param groupCodes
	 *            岗位合集
	 * @return List<SysGroupRole> 当前用户所有职位下的权限合集
	 */
	public List<SysGroupRole> findSysGroupRoleListByGroupCodes(List<String> groupCodes);

	/** 条件查询 **/
	public List<SysGroup> querySysGroup(@Param("groupCode") String groupCode, @Param("groupName") String groupName,
			@Param("comLevel") String comLevel, @Param("comType") String comType);

	/** 用户查询岗位 **/
	public List<SysGroup> queryUserSysGroup(@Param("comLevel") String comLevel, @Param("comType") String comType);
}