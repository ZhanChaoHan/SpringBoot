package com.sinosoft.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.sinosoft.normal.po.SysConfig;

@Mapper
public interface SysConfigMapper {
	public int deleteByPrimaryKey(String configName);

	public int insert(SysConfig record);

	public int insertSelective(SysConfig record);

	public ArrayList<SysConfig> selectByPrimaryKey(SysConfig config);

	public int updateByPrimaryKeySelective(SysConfig record);

	public int updateByPrimaryKey(SysConfig record);

	/**
	 * 根据configName获取定时刷新时间
	 * 
	 * @param configName
	 *            时间名称
	 * @return
	 */
	public String getTimeByName(String configName);
}