package com.sinosoft.service;


import java.util.List;

import com.sinosoft.normal.po.SysGroup;

public interface SysGroupService {
	public int deleteByPrimaryKey(String groupCode);

	public int insert(SysGroup record);

	public int insertSelective(SysGroup record);

	public SysGroup selectByPrimaryKey(String groupCode);

	public int updateByPrimaryKeySelective(SysGroup record);

	public int updateByPrimaryKey(SysGroup record);
	
	public  List<SysGroup> querySysGroup(String groupCode,String groupName,String comLevel,String comType);

	public List<SysGroup> queryUserSysGroup(String comLevel,String comType);
	
}