package com.sinosoft.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinosoft.normal.po.SysConfig;
import com.sinosoft.service.SysConfigService;
import com.sinosoft.dao.SysConfigMapper;

@Service
public class SysConfigServiceImpl implements SysConfigService {
	@Autowired
	public SysConfigMapper sysConfig;

	@Override
	public ArrayList<SysConfig> selectByPrimaryKey(SysConfig config) {
		return sysConfig.selectByPrimaryKey(config);
	}

	@Override
	public int updateByPrimaryKeySelective(SysConfig config) {
		return sysConfig.updateByPrimaryKeySelective(config);
	}

	/**
	 * 根据configName获取定时刷新时间
	 * 
	 * @param configName
	 *            时间名称
	 * @return
	 */
	@Override
	public String getTimeByName(String configName) {
		return sysConfig.getTimeByName(configName);
	}

}
