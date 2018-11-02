package com.sinosoft.service.impl;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinosoft.normal.po.SysArea;
import com.sinosoft.normal.po.SysCode;
import com.sinosoft.service.IndexService;
import com.sinosoft.dao.IndexMapper;

@Service
public class IndexServiceImpl implements IndexService {

	@Autowired
	public IndexMapper envinfo;

	// 默认查询所属的区域
	public ArrayList<SysArea> selectByAreaCode(String area) {
		return envinfo.selectByAreaCode(area);
	}

	@Override
	public ArrayList<SysCode> selectByMsg() {
		return envinfo.selectByMsg();
	}

}
