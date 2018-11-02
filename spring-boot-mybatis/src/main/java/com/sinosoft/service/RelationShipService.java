package com.sinosoft.service;

import java.util.ArrayList;

import com.sinosoft.normal.po.SysConfig;

public interface RelationShipService {

	public ArrayList<SysConfig> selectByPrimaryKey(SysConfig config);
    
	public int updateByPrimaryKeySelective(SysConfig config);
	
	/**
	 * 根据configName获取定时刷新时间
	 * @param configName 时间名称
	 * @return
	 */
	public String getTimeByName(String configName);

}