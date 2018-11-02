package com.sinosoft.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinosoft.normal.po.SysConfig;
import com.sinosoft.service.RelationShipService;
import com.sinosoft.dao.RelationShipMapper;

@Service
public class RelationShipServiceImpl implements RelationShipService {

	@Autowired
	public RelationShipMapper relationShip;

	@Override
	public ArrayList<SysConfig> selectByPrimaryKey(SysConfig config) {
		return relationShip.selectByPrimaryKey(config);
	}

	@Override
	public int updateByPrimaryKeySelective(SysConfig config) {
		return relationShip.updateByPrimaryKeySelective(config);
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
		return relationShip.getTimeByName(configName);
	}

}
